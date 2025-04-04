package com.example.potato_tuto.service.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.UserNotFoundException;
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


    public String updateUserByEmail(String email, UserUpdateRequestDTO request) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 Email은 존재하지 않습니다."));

        User updatedUser = User.builder() // @Id 로 선언된 id 만 빼고 다른 필드는 update 가능하다.
                .id(existingUser.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(updatedUser);
        return "Email 기준으로 회원 정보가 수정되었습니다.";
    }
}
