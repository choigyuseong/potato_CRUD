package com.example.potato_tuto.exception.requestError;

import com.example.potato_tuto.exception.ErrorCode;

public class UserListEmptyException extends BusinessException {

    public UserListEmptyException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}
