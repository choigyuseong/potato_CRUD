package com.example.potato_tuto.controller;

import com.example.potato_tuto.dto.User.request.CreateDto;
import com.example.potato_tuto.dto.User.request.UpdateDto;
import com.example.potato_tuto.dto.User.response.ResponseDto;
import com.example.potato_tuto.service.user.CreateService;
import com.example.potato_tuto.service.user.DeleteByEmailService;
import com.example.potato_tuto.service.user.GetAllService;
import com.example.potato_tuto.service.user.GetByEmailService;
import com.example.potato_tuto.service.user.UpdateByEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateService userCreateService;
    private final GetAllService userGetAllService;
    private final GetByEmailService userGetByEmailService;
    private final UpdateByEmailService userUpdateByEmailService;
    private final DeleteByEmailService userDeleteByEmailService;

    public UserController(CreateService userCreateService,
                          GetAllService userGetAllService,
                          GetByEmailService userGetByEmailService,
                          UpdateByEmailService userUpdateByEmailService,
                          DeleteByEmailService userDeleteByEmailService) {
        this.userCreateService = userCreateService;
        this.userGetAllService = userGetAllService;
        this.userGetByEmailService = userGetByEmailService;
        this.userUpdateByEmailService = userUpdateByEmailService;
        this.userDeleteByEmailService = userDeleteByEmailService;
    }



    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateDto request) {
        String result = userCreateService.createUser(request);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/all_users")
    public List<ResponseDto> getAllUsers() {
        return userGetAllService.getAllUsers();
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDto> getUserByEmail(@PathVariable String email) {
        ResponseDto user = userGetByEmailService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


    @PutMapping("/email/{email}")
    public ResponseEntity<String> updateUserByEmail(
            @PathVariable String email,
            @RequestBody UpdateDto request
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