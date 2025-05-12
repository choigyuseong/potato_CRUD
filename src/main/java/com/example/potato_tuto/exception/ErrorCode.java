package com.example.potato_tuto.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    DUPLICATE_USER_EXCEPTION(HttpStatus.CONFLICT, "1000", "User already exists"),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "1001", "User not found"),
    USER_LIST_EMPTY_EXCEPTION(HttpStatus.BAD_REQUEST, "1002", "user list is empty"),;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
