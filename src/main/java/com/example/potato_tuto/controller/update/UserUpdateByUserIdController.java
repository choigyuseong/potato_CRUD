package com.example.potato_tuto.controller.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.service.update.UserUpdateByUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/userid")
public class UserUpdateByUserIdController {

    @Autowired
    private UserUpdateByUserIdService userUpdateByUserIdService;

    @PutMapping("/{userid}")
    public ResponseEntity<String> updateUserByUserId(
            @PathVariable String userid,
            @RequestBody UserUpdateRequestDTO request
    ) {
        String result = userUpdateByUserIdService.updateUserByUserId(userid, request);
        return ResponseEntity.ok(result);
    }
}

