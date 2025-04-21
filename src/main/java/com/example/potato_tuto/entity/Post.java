package com.example.potato_tuto.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title; // 글 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 글 내용

    @Column(nullable = false)
    private LocalDateTime createdAt; // 작성 시간

    @Column
    private LocalDateTime updatedAt; // 수정 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
