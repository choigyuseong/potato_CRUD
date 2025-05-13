package com.example.potato_tuto.security.jwt;

import com.example.potato_tuto.exception.requestError.ExpiredTokenException;
import com.example.potato_tuto.exception.requestError.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-ms}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshTokenExpirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 인증필터, 로그인 권한 설정 위해서 두가지 방식 모두 구현
    public String createToken(Authentication auth) {
        return createToken(auth.getName());
    }

    public String createToken(String email) {
        Date now = new Date();
        Date expiry  = new Date(now.getTime() + accessTokenExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명 기반 알고리즘
                .compact();
    }
    // AES => 대칭키 암호화 방식 ( 나중에 도전 )

    public String createRefreshToken(Authentication auth) {
        return createRefreshToken(auth.getName());
    }

    public String createRefreshToken(String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenExpirationMs);

        return Jwts.builder()
                .setSubject(email)
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); // 기간 검증
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException("토큰이 만료되었습니다.");
        } catch (JwtException e) {
            throw new InvalidTokenException("유효하지 않은 토큰입니다.");
        } catch (Exception e) {
            throw new InvalidTokenException("토큰 검증 중 알 수 없는 오류가 발생했습니다.");
        }
    }

    public String getUserEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getTokenType(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("type", String.class);
    }

}