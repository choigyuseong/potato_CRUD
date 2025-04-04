package com.example.potato_tuto.service.update;

import com.example.potato_tuto.dto.User.request.UpdateDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserUpdateByEmailService {

    private final UserRepository userRepository;

    @Autowired
    public UserUpdateByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String updateUserByEmail(String email, UpdateDTO request) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 Email은 존재하지 않습니다."));

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(updatedUser);
        return "회원 정보가 수정되었습니다.";
    }
}
