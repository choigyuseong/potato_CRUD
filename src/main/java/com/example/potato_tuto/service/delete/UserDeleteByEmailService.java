package com.example.potato_tuto.service.delete;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeleteByEmailService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeleteByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String deleteUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return "이메일 기준으로 탈퇴에 성공했습니다.";
        } else {
            return "해당 이메일은 존재하지 않습니다.";
        }
    }
}
