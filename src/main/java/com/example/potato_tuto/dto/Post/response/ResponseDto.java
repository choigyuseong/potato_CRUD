package com.example.potato_tuto.dto.Post.response;

import com.example.potato_tuto.entity.Post;

import java.time.LocalDateTime;

public class ResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorEmail;

    public ResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.authorEmail = post.getUser().getEmail();
    }
}
