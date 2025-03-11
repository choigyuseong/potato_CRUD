package com.example.potato_tuto.repository;

import com.example.potato_tuto.model.User;
import org.springframework.data.jpa.repository.JpaRepository; // 기본적인 CRUD기능을 탑재한 인터페이스

public interface UserRepository extends JpaRepository<User, Long> {
}