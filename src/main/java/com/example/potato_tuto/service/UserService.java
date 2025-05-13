package com.example.potato_tuto.service;

import com.example.potato_tuto.dto.user.request.UserCreateDto;
import com.example.potato_tuto.dto.user.request.UserUpdateDto;
import com.example.potato_tuto.dto.user.response.UserResponseDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.DuplicateUserException;
import com.example.potato_tuto.exception.requestError.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createUser(UserCreateDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException("이미 등록된 이메일입니다.");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User saved = userRepository.save(user);
        return new UserResponseDto(saved);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        userRepository.delete(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUserByEmail(String email, UserUpdateDto request) {
        User existing = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        existing.updateUser(request.getName(),
                passwordEncoder.encode(request.getPassword()));
        User updated = userRepository.save(existing);
        return new UserResponseDto(updated);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
    }
}
