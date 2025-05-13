package com.example.potato_tuto.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ErrorCode {

    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "1000", "User not found"),
    INVALID_PASSWORD_EXCEPTION(HttpStatus.UNAUTHORIZED, "1001", "Invalid password"),
    ACCOUNT_LOCKED_EXCEPTION(HttpStatus.FORBIDDEN, "1002", "Account is locked"),
    ACCOUNT_DISABLED_EXCEPTION(HttpStatus.FORBIDDEN, "1003", "Account is disabled"),

    DUPLICATE_USER_EXCEPTION(HttpStatus.CONFLICT, "2000", "User already exists"),
    INVALID_EMAIL_FORMAT_EXCEPTION(HttpStatus.BAD_REQUEST, "2001", "Invalid email format"),
    WEAK_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "2002", "Password is too weak"),
    USER_LIST_EMPTY_EXCEPTION(HttpStatus.BAD_REQUEST, "2003", "User list is empty"),
    UNAUTHORIZED_USER_ACCESS_EXCEPTION(HttpStatus.FORBIDDEN, "2004", "You don't have access to this user"),

    INVALID_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "3000", "Invalid or malformed token"),
    EXPIRED_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "3001", "Token has expired"),
    NOT_REFRESH_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "3002", "Token is not a refresh token"),
    UNSUPPORTED_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, "3003", "Unsupported JWT token"),
    MISSING_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "3004", "Authorization header is missing"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
