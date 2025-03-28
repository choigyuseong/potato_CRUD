package com.example.potato_tuto.service.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserReadByEmailService {

    private final UserRepository userRepository;

    public UserReadByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(UserResponseDTO::new).orElse(null);
    }
}
