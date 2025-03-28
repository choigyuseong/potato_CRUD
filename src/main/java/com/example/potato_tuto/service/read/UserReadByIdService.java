package com.example.potato_tuto.service.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserReadByIdService {

    private final UserRepository userRepository;

    public UserReadByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserResponseDTO::new).orElse(null);
    }
}
