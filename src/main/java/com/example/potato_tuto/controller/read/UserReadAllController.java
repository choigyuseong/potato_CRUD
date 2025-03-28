package com.example.potato_tuto.controller.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.service.read.UserReadAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserReadAllController {

    @Autowired
    private UserReadAllService userReadAllService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userReadAllService.getAllUsers();
    }
}
