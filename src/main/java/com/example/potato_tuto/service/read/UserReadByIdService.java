package com.example.potato_tuto.service.read;

import com.example.potato_tuto.dto.response.UserResponseDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserReadByIdService {

    private final UserRepository userRepository;

    public UserReadByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO getUserById(String userid) {
        User user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new UserNotFoundException("해당 ID의 사용자를 찾을 수 없습니다."));
        return new UserResponseDTO(user);
    }
}
