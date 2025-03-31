package com.example.potato_tuto.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private String id;
    private String name;
    private String email;
    private String password;
}
