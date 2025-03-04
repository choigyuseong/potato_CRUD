package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.UserResponseDTO;
import com.example.potato_tuto.model.User;
import com.example.potato_tuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API (POST)
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String result = userService.createUser(user);
        return ResponseEntity.ok(result); // 성공 / 실패 메시지 그대로 반환
    }

    // 모든 사용자 조회 API (GET)
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // 특정 사용자 조회 API (GET)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build(); // 사용자가 없으면 404 반환
        }
    }

    // 회원 삭제 API (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }
}