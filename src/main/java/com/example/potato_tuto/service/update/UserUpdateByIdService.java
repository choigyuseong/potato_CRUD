package com.example.potato_tuto.service.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUpdateByIdService {

    private final UserRepository userRepository;

    @Autowired
    public UserUpdateByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String updateUserById(Long id, UserUpdateRequestDTO request) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return "해당 ID는 존재하지 않습니다.";
        }

        User user = User.builder()
                .id(id)
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
        return "회원 정보가 수정되었습니다.";
    }

}
