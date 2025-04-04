package com.example.potato_tuto.exception.requestError;

import com.example.potato_tuto.exception.ErrorCode;

public class DuplicateUserException extends BusinessException {

    public DuplicateUserException(String message) {
        super(message, ErrorCode.DUPLICATE_EXCEPTION);
    }
}
