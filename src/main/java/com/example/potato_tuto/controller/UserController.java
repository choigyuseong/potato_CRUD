package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.user.request.UserCreateDto;
import com.example.potato_tuto.dto.user.request.UserUpdateDto;
import com.example.potato_tuto.dto.user.response.UserResponseDto;
import com.example.potato_tuto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 201 undocumented
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto dto) {
        UserResponseDto response = userService.createUser(dto);
        URI location = URI.create("/users/" + response.getEmail());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        UserResponseDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> updateUserByEmail(
            @PathVariable String email,
            @RequestBody UserUpdateDto dto) {
        return ResponseEntity.ok(userService.updateUserByEmail(email, dto));
    }

    // 204 Undocumented
    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}