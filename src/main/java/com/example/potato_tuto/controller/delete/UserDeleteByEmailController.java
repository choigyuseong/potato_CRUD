package com.example.potato_tuto.controller.delete;

import com.example.potato_tuto.service.delete.UserDeleteByEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/email")
public class UserDeleteByEmailController {

    @Autowired
    private UserDeleteByEmailService userDeleteByEmailService;

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        String result = userDeleteByEmailService.deleteUserByEmail(email);
        return ResponseEntity.ok(result);
    }
}
