package com.example.potato_tuto.service.user;

import com.example.potato_tuto.dto.User.response.ResponseDto;
import com.example.potato_tuto.entity.User;
import com.example.potato_tuto.exception.requestError.UserListEmptyException;
import com.example.potato_tuto.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllService {

    private final UserRepository userRepository;

    public GetAllService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<ResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        if (users.isEmpty()) {
            throw new UserListEmptyException("등록된 사용자가 없습니다.");
        }

        return users.stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }
}
