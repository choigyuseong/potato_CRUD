package com.example.potato_tuto.dto.User.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDto {
    private String name;
    private String password;
}
