package com.example.potato_tuto.service.create;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

    private final UserRepository userRepository;

    @Autowired
    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return "이미 존재하는 ID입니다.";
        }
        userRepository.save(user);
        return "회원 가입 성공: " + user.getName();
    }
}

