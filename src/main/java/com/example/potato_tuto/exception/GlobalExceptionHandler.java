package com.example.potato_tuto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Create
    @ExceptionHandler(DuplicateUserException.class) // user 가 이미 존재할 때
    public ResponseEntity<String> handleDuplicateUser(DuplicateUserException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }



    // Read, Delete
    @ExceptionHandler(UserNotFoundException.class) // user 없을 때
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }



    // Read
    @ExceptionHandler(UserListEmptyException.class) // user list 없을 때
    public ResponseEntity<String> handleUserListEmpty(UserListEmptyException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
