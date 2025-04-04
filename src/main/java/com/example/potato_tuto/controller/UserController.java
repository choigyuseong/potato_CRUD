package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.User.request.CreateDTO;
import com.example.potato_tuto.dto.User.request.UpdateDTO;
import com.example.potato_tuto.dto.User.response.ResponseDTO;
import com.example.potato_tuto.service.create.UserCreateService;
import com.example.potato_tuto.service.delete.UserDeleteByEmailService;
import com.example.potato_tuto.service.read.UserReadAllService;
import com.example.potato_tuto.service.read.UserReadByEmailService;
import com.example.potato_tuto.service.update.UserUpdateByEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserCreateService userCreateService;
    private final UserReadAllService userReadAllService;
    private final UserReadByEmailService userReadByEmailService;
    private final UserUpdateByEmailService userUpdateByEmailService;
    private final UserDeleteByEmailService userDeleteByEmailService;

    public UserController(UserCreateService userCreateService,
                          UserReadAllService userReadAllService,
                          UserReadByEmailService userReadByEmailService,
                          UserUpdateByEmailService userUpdateByEmailService,
                          UserDeleteByEmailService userDeleteByEmailService) {
        this.userCreateService = userCreateService;
        this.userReadAllService = userReadAllService;
        this.userReadByEmailService = userReadByEmailService;
        this.userUpdateByEmailService = userUpdateByEmailService;
        this.userDeleteByEmailService = userDeleteByEmailService;
    }



    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateDTO request) {
        String result = userCreateService.createUser(request);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/all_users")
    public List<ResponseDTO> getAllUsers() {
        return userReadAllService.getAllUsers();
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmail(@PathVariable String email) {
        ResponseDTO user = userReadByEmailService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/email/{email}")
    public ResponseEntity<String> updateUserByEmail(
            @PathVariable String email,
            @RequestBody UpdateDTO request
    ) {
        String result = userUpdateByEmailService.updateUserByEmail(email, request);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
        String result = userDeleteByEmailService.deleteUserByEmail(email);
        return ResponseEntity.ok(result);
    }

    // 에러 코드 body 가 아닌 header 로 넘기기
    // post entity 만들어서 erd cloud 짜오기, 댓글 기능 짜오기 (블로그 느낌)
}