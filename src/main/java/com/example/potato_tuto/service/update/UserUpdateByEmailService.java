package com.example.potato_tuto.service.update;

import com.example.potato_tuto.dto.request.UserUpdateRequestDTO;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUpdateByEmailService {

    private final UserRepository userRepository;

    @Autowired
    public UserUpdateByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String updateUserByEmail(String email, UserUpdateRequestDTO request) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get(); // 기존 사용자
            User updatedUser = User.builder()
                    .id(existingUser.getId()) // 기존 ID 유지, 이거 안하면 기존 사용자 데이터에 덮어씌우는게 아니라 새로운 사용자를 추가할수도 있음
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build();

            userRepository.save(updatedUser);
            return "이메일 기준으로 회원 정보가 수정되었습니다.";
        } else {
            return "해당 이메일은 존재하지 않습니다.";
        }
    }
}
