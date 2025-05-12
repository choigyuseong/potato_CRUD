package com.example.potato_tuto.service;

import com.example.potato_tuto.dto.auth.request.LoginRequestDto;
import com.example.potato_tuto.dto.auth.response.LoginResponseDto;
import com.example.potato_tuto.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String accessToken = jwtTokenProvider.createToken(request.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.getEmail());
        return new LoginResponseDto(accessToken, refreshToken);
    }

    public void logout(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException("이미 만료된 토큰입니다.");
        }
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException("Refresh Token이 유효하지 않습니다.");
        }

        if (!jwtTokenProvider.getTokenType(refreshToken).equals("refresh")) {
            throw new InvalidTokenException("Refresh Token이 아닙니다.");
        }

        String email = jwtTokenProvider.getUserEmail(refreshToken);
        String newAccessToken = jwtTokenProvider.createToken(email);

        String newRefreshToken = jwtTokenProvider.createRefreshToken(email);

        return new LoginResponseDto(newAccessToken, newRefreshToken);
    }
}
