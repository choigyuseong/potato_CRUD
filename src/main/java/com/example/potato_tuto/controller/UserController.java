package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.UserResponseDTO;
import com.example.potato_tuto.model.User;
import com.example.potato_tuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 다른 어노테이션들을 사용할 수 있게 만든다.
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API (POST)
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) { // RequestBody를 통해서 json을 user로 바꾼다.
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
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 ID입니다."); // 존재하지 않는 ID일 경우 메시지 반환
        }
    }

    // 회원 삭제 API (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        String result = userService.updateUser(id, user);
        if ("해당 ID는 존재하지 않습니다.".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result); // 404 반환
        }
        return ResponseEntity.ok(result); // 200 OK 반환
    }
}