package com.example.potato_tuto.controller.User;

import com.example.potato_tuto.dto.request.UserCreateRequestDTO;
import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.service.create.UserCreateService;
import com.example.potato_tuto.service.delete.UserDeleteByEmailService;
import com.example.potato_tuto.service.read.UserReadAllService;
import com.example.potato_tuto.service.read.UserReadByEmailService;
import com.example.potato_tuto.service.update.UserUpdateByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Create
    @Autowired
    private UserCreateService userCreateService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequestDTO request) {
        String result = userCreateService.createUser(request);
        return ResponseEntity.ok(result);
    }


    // Read_All
    @Autowired
    private UserReadAllService userReadAllService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userReadAllService.getAllUsers();
    }


    // Read_ByEmail
    @Autowired
    private UserReadByEmailService userReadByEmailService;

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        UserResponseDTO user = userReadByEmailService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("존재하지 않는 이메일입니다.");
        }
    }


    // Update
    @Autowired
    private UserUpdateByEmailService userUpdateByEmailService;

    @PutMapping("/{email}")
    public ResponseEntity<String> updateUserByEmail(
            @PathVariable String email,
            @RequestBody UserUpdateRequestDTO request
    ) {
        String result = userUpdateByEmailService.updateUserByEmail(email, request);
        return ResponseEntity.ok(result);
    }


    // Delete
    @Autowired
    private UserDeleteByEmailService userDeleteByEmailService;

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        String result = userDeleteByEmailService.deleteUserByEmail(email);
        return ResponseEntity.ok(result);
    }

    // 에러 코드 body 가 아닌 header 로 넘기기
    // post entity 만들어서 erd cloud 짜오기, 댓글 기능 짜오기 (블로그 느낌)
}