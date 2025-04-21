package com.example.potato_tuto.service.user;

import com.example.potato_tuto.dto.User.request.UpdateDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UpdateByEmailService {

    private final UserRepository userRepository;

    @Autowired
    public UpdateByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String updateUserByEmail(String email, UpdateDto request) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 Email은 존재하지 않습니다."));

        existingUser.updateUser(request.getEmail(), request.getPassword());
        userRepository.save(existingUser);
        return "회원 정보가 수정되었습니다.";
    }
}
