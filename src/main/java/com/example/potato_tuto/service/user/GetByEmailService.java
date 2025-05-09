package com.example.potato_tuto.service.user;

import com.example.potato_tuto.dto.User.response.ResponseDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.UserNotFoundException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class GetByEmailService {

    private final UserRepository userRepository;

    public GetByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자는 존재하지 않습니다."));
        return new ResponseDto(user);
    }
}
