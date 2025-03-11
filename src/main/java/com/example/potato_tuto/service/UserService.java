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

    // 특정 사용자 조회 (ID 이용)
    public UserResponseDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id); // 특정 id를 가진 엔티티 user 객체에 저장, optional 사용하는 이유는 null을 안전하게 다루는 방법이라서
        if (user.isEmpty()) { //isPresent 보다 isEmpty가 가독성이 더 좋다.
            return null;
        }
        return new UserResponseDTO(user.get());
    }

    // 전체 사용자 조회
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll(); // 모든 엔티티를  users List 에 저장
        return users.stream() // java 에서 List를 보다 간편하게 다루기 위해 stream 사용
                .map(UserResponseDTO::new) // User 객체를 새로운 UserResponseDTO 객체로 변환
                .collect(Collectors.toList()); // 결과를 List 로 변환
    }

    // 회원 삭제 기능 (Delete)
    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return "해당 사용자를 찾을 수 없습니다."; // 엔티티가 없으면 삭제 실패
        }
        userRepository.delete(user.get()); // 값이 있을때 엔티티 삭제
        return "탈퇴에 성공했습니다.";
    }

    // 사용자 업데이트 (Update)
    public String updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return "해당 ID는 존재하지 않습니다."; // 사용자 없을 경우 메시지 반환
        }
        User updatedUser = existingUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userRepository.save(updatedUser);
        return "회원정보가 업데이트되었습니다.";
    }
}