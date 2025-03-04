package com.example.potato_tuto.repository;

import com.example.potato_tuto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 필요에 따라 커스텀 쿼리 메소드 작성 가능
    // 예시: 이메일로 사용자 찾기
    User findByEmail(String email);
}