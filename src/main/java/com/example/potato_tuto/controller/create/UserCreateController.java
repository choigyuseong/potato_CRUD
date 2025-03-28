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
        User user = new User(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        String result = userCreateService.createUser(user);
        return ResponseEntity.ok(result);
    }
}
