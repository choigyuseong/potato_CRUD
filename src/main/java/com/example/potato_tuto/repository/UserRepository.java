package com.example.potato_tuto.repository;

import com.example.potato_tuto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserid(String userid);

    boolean existsByEmail(String email);
    boolean existsByUserid(String userid);

    void deleteByEmail(String email);
    void deleteByUserid(String userid);

}