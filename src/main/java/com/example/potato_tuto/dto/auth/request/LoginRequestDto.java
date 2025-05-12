package com.example.potato_tuto.dto.auth.request;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
