package com.example.potato_tuto.controller.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.service.read.UserReadByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserReadByIdController {

    @Autowired
    private UserReadByIdService userReadByIdService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userReadByIdService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("존재하지 않는 ID입니다.");
        }
    }
}
