package com.example.potato_tuto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    private Long id; // 중복 x,

    private String name; // 중복 o
    private String email; // 중복 x
    private String password; // 중복 o
    
    // @Id 지정할 때 하나의 값만 가지고 식별하기 어려운 경우
    // ex) "oo 교수님"의 "xx 과목"에서 "oo 교수님"이라는 값만 가지고는 식별하기 어렵기 때문에
    // 필드를 2개 이상 묶어서 Id 로 지정하는 복합키 사용
    // 일반적으로는 단일 기본키를 사용한다.

}