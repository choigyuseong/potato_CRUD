package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.User.request.CreateDto;
import com.example.potato_tuto.dto.User.request.UpdateDto;
import com.example.potato_tuto.dto.User.response.ResponseDto;
import com.example.potato_tuto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateDto request) {
        String result = userService.createUser(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getUserByEmail(@PathVariable String email) {
        ResponseDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/email/{email}")
    public ResponseEntity<String> updateUserByEmail(
            @PathVariable String email,
            @RequestBody UpdateDto request
    ) {
        String result = userService.updateUserByEmail(email, request);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        String result = userService.deleteUserByEmail(email);
        return ResponseEntity.ok(result);
    }
}