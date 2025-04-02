package com.example.potato_tuto.service.create;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.DuplicateUserException;
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

        if (userRepository.existsByUserid(user.getUserid())) {
            throw new DuplicateUserException("이미 존재하는 ID입니다: " + user.getUserid());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("이미 등록된 이메일입니다: " + user.getEmail());
        }

        userRepository.save(user);
        return "회원가입에 성공했습니다.";
    }
}

