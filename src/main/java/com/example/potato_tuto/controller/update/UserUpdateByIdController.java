package com.example.potato_tuto.controller.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.service.update.UserUpdateByIdService;
import com.example.potato_tuto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserUpdateByIdController {

    @Autowired
    private UserUpdateByIdService userUpdateService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDTO request) {
        User user = new User(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        String result = userUpdateService.updateUserById(id, user);
        return ResponseEntity.ok(result);
    }
}
