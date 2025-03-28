package com.example.potato_tuto.controller.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.service.read.UserReadByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/email")
public class UserReadByEmailController {

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
}
