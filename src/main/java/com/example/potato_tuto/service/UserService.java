package com.example.potato_tuto.service;

import com.example.potato_tuto.dto.User.request.CreateDto;
import com.example.potato_tuto.dto.User.request.UpdateDto;
import com.example.potato_tuto.dto.User.response.ResponseDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.DuplicateUserException;
import com.example.potato_tuto.exception.requestError.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String createUser(CreateDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("이미 등록된 이메일입니다.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userRepository.save(user);
        return "회원 가입에 성공했습니다.";
    }

    @Transactional
    public String deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        userRepository.delete(user);
        return "회원 탈퇴에 성공했습니다.";

    }

    public ResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        return new ResponseDto(user);
    }

    @Transactional
    public String updateUserByEmail(String email, UpdateDto request) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 Email은 존재하지 않습니다."));

        existingUser.updateUser(request.getEmail(), request.getPassword());
        userRepository.save(existingUser);
        return "회원 정보가 수정되었습니다.";
    }
}
