package com.example.potato_tuto.service.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserUpdateByIdService {

    private final UserRepository userRepository;

    @Autowired
    public UserUpdateByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String updateUserById(String id, UserUpdateRequestDTO request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 ID는 존재하지 않습니다."));

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(updatedUser);
        return "ID 기준으로 회원 정보가 수정되었습니다.";
    }

}
