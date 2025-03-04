package com.example.potato_tuto.service;

import com.example.potato_tuto.dto.UserResponseDTO;
import com.example.potato_tuto.model.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 기능 (Create)
    public String createUser(User user) {
        // ID 중복 확인
        if (userRepository.existsById(user.getId())) {
            return "이미 존재하는 ID입니다."; // 예외 대신 문자열 반환
        }

        User savedUser = userRepository.save(user);
        return "회원가입 성공!"; // 성공 시 메시지 반환
    }

    public UserResponseDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new UserResponseDTO(user.get()); // 해당 사용자 DTO 반환
        }
        return null; // 사용자가 없으면 null 반환 (Optional 사용시 유효성 체크 필요)
    }

    // 전체 사용자 조회
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll(); // 모든 사용자 조회
        return users.stream()
                .map(UserResponseDTO::new) // User 객체를 UserResponseDTO로 변환
                .collect(Collectors.toList());
    }

    // 회원 삭제 기능 (Delete)
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "탈퇴에 성공했습니다.";
        }
        return "해당 사용자를 찾을 수 없습니다."; // 사용자가 없으면 삭제 실패
    }
}