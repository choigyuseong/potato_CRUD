package com.example.potato_tuto.service.delete;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserDeleteByEmailService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeleteByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 이메일은 존재하지 않습니다."));
        userRepository.delete(user);
        return "이메일 기준으로 탈퇴에 성공했습니다.";

    }

}
