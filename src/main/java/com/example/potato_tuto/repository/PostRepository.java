package com.example.potato_tuto.repository;

import com.example.potato_tuto.entity.Post;
import com.example.potato_tuto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
