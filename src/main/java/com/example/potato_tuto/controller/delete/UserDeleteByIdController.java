package com.example.potato_tuto.controller.delete;

import com.example.potato_tuto.service.delete.UserDeleteByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserDeleteByIdController {

    @Autowired
    private UserDeleteByIdService userDeleteByIdService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String result = userDeleteByIdService.deleteUserById(id);
        return ResponseEntity.ok(result);
    }
}
