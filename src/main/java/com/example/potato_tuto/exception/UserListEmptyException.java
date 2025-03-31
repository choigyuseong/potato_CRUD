package com.example.potato_tuto.exception;

public class UserListEmptyException extends RuntimeException {
    // User list 가 비워져있는 경우 예외
    public UserListEmptyException(String message) {
        super(message);
    }
}
