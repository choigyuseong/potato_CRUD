package com.example.potato_tuto.dto.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
}
