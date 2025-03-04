package com.example.potato_tuto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDTO {
    private long id;
    private String name;
    private String email;
    private String password;
}