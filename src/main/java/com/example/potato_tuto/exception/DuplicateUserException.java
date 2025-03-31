package com.example.potato_tuto.exception;

public class DuplicateUserException extends RuntimeException {
    // 중복이 안되는 Id, Email 필드가 이미 동일한 값이 존재하는 경우 예외
    public DuplicateUserException(String message) {
        super(message);
    }
}
