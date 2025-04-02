package com.example.potato_tuto.controller.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.service.read.UserReadByUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/userid")
public class UserReadByUserIdController {

    @Autowired
    private UserReadByUserIdService userReadByUserIdService;

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable String userid) {
        UserResponseDTO user = userReadByUserIdService.getUserByUserId(userid);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("존재하지 않는 ID입니다.");
        }
    }
}
