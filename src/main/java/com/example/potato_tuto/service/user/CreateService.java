package com.example.potato_tuto.service.user;

import com.example.potato_tuto.dto.User.request.CreateDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.DuplicateUserException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateService {

    private final UserRepository userRepository;

    @Autowired
    public CreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(CreateDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("이미 등록된 이메일입니다: " + request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
        return "회원 가입에 성공했습니다.";
    }

}

