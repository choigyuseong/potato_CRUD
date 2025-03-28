package com.example.potato_tuto.service.delete;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeleteByIdService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeleteByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "탈퇴에 성공했습니다.";
        } else {
            return "해당 사용자를 찾을 수 없습니다.";
        }
    }
}
