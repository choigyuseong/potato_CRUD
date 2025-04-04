package com.example.potato_tuto.controller.create;

import com.example.potato_tuto.dto.request.UserCreateRequestDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.service.create.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserCreateController {

    @Autowired
    private UserCreateService userCreateService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequestDTO request) {
        User user = User.builder()
                .userid(request.getUserid())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        String result = userCreateService.createUser(user);
        return ResponseEntity.ok(result);
    }
    
    // UserController 합치기
    // builder service 로 옮기기
    //post entity 만들어서 erd cloud 짜오기, 댓글 기능 짜오기 (블로그 느낌)
}
