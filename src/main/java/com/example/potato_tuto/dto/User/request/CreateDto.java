package com.example.potato_tuto.dto.User.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDto {
    private String name;
    private String email;
    private String password;
}
