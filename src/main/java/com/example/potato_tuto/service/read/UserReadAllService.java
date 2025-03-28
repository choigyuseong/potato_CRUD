package com.example.potato_tuto.service.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserReadAllService {

    private final UserRepository userRepository;

    public UserReadAllService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }
}
