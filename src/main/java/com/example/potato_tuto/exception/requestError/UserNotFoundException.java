package com.example.potato_tuto.exception.requestError;

import com.example.potato_tuto.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
