package com.example.potato_tuto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // User 클래스와 매핑된 테이블 자동 생성
@Data // getter, setter 등 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 자동 생성
public class User {

    @Id // JPA가 객체를 효율적으로 다루게 해준다.
    private Long id; // 사용자 고유 ID
    private String name; // 이름
    private String email; // 이메일
    private String password; // 패스워드 (암호화 없이 사용)

}