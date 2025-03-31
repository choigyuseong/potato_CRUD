package com.example.potato_tuto.service.delete;

import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserDeleteByIdService {

    private final UserRepository userRepository;

    @Autowired
    public UserDeleteByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String deleteUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 id는 존재하지 않습니다."));
        userRepository.delete(user);
        return "id 기준으로 탈퇴에 성공했습니다.";

    }
}
