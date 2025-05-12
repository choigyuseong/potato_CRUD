package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.auth.request.LoginRequestDto;
import com.example.potato_tuto.dto.auth.request.RefreshRequestDto;
import com.example.potato_tuto.dto.auth.response.LoginResponseDto;
import com.example.potato_tuto.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshRequestDto request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok("로그아웃 되었습니다. 토큰을 삭제해주세요.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(@RequestBody RefreshRequestDto request) {
        LoginResponseDto newTokens = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(newTokens);
    }
}
