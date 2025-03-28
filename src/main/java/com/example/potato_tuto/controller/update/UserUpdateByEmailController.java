package com.example.potato_tuto.controller.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.service.update.UserUpdateByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/email")
public class UserUpdateByEmailController {

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
}
