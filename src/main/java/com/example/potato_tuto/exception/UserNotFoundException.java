package com.example.potato_tuto.exception;

public class UserNotFoundException extends RuntimeException {
    // 찾으려는 User 가 없는 경우 예외
    public UserNotFoundException(String message) {
        super(message);
    }
}
