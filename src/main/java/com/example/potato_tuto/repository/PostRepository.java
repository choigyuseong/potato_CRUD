package com.example.potato_tuto.repository;

import com.example.potato_tuto.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 특정 작성자의 게시글 목록 조회
    List<Post> findByUserId(Long userId);

    // 제목으로 게시글 검색
    List<Post> findByTitleContaining(String keyword);
}
