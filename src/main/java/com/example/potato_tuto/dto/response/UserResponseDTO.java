package com.example.potato_tuto.dto.response;

import com.example.potato_tuto.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
@Getter


public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;


    // User 엔티티를 받아서 DTO를 생성하는 생성자
    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

}