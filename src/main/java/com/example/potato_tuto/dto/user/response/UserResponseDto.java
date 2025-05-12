package com.example.potato_tuto.dto.user.response;

import com.example.potato_tuto.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter


public class UserResponseDto {
    private Long id;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

}