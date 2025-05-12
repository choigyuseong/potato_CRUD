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

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto dto) {
        UserResponseDto response = userService.createUser(dto);
        URI location = URI.create("/users/" + response.getEmail());
        return ResponseEntity.created(location).body(response);
    }
    /* location 헤더
    * 응답이 성공했을 때 (201 Created) 새로 만들어진 리소스의 URI를 location 헤더에 담아서 클라이언트에 전달 ( 리소스를 생성했을 때만 사용 )
    * HTTP/1.1 스펙 권고사항 이라서 사용, 클라이언트에게 흐름을 보여줌 */

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


    @DeleteMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}