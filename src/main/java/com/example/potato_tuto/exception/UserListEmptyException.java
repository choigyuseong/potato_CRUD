package com.example.potato_tuto.exception;

public class UserListEmptyException extends RuntimeException {
    public UserListEmptyException(String message) {
        super(message);
    }
}
