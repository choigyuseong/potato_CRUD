package com.example.potato_tuto.controller.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.service.update.UserUpdateByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/id")
public class UserUpdateByIdController {

    @Autowired
    private UserUpdateByIdService userUpdateByIdService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(
            @PathVariable String id,
            @RequestBody UserUpdateRequestDTO request
    ) {
        String result = userUpdateByIdService.updateUserById(id, request);
        return ResponseEntity.ok(result);
    }
}

