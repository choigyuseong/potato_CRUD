package com.example.potato_tuto.service;

import com.example.potato_tuto.dto.auth.request.LoginRequestDto;
import com.example.potato_tuto.dto.auth.response.LoginResponseDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.InvalidTokenException;
import com.example.potato_tuto.exception.requestError.NotRefreshTokenException;
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
    private final UserService userService;

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String email = authentication.getName();
        String accessToken = jwtTokenProvider.createToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        return new LoginResponseDto(accessToken, refreshToken);
    }


    public void logout(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException("이미 만료된 토큰입니다.");
        }
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        jwtTokenProvider.validateToken(refreshToken);

        String tokenType = jwtTokenProvider.getTokenType(refreshToken);
        if (!"refresh".equals(tokenType)) {
            throw new NotRefreshTokenException("Refresh Token이 아닙니다.");
        }

        String email = jwtTokenProvider.getUserEmailFromToken(refreshToken);
        User user = userService.findUserByEmail(email);
        // Token 을 통해 가져온 Email 을 실제로 사용하는 user 가 존재하는지 확인

        String newAccessToken = jwtTokenProvider.createToken(user.getEmail());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());

        return new LoginResponseDto(newAccessToken, newRefreshToken);
    }
}
