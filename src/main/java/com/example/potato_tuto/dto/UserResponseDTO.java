package com.example.potato_tuto.dto;

import com.example.potato_tuto.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
@Data // getter, setter 등 자동 생성


public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;


    // User 엔티티를 받아서 DTO를 생성하는 생성자
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}