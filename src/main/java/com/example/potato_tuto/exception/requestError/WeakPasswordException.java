package com.example.potato_tuto.exception.requestError;

import com.example.potato_tuto.exception.ErrorCode;

public class WeakPasswordException extends BusinessException {
    public WeakPasswordException(String message) { super(message, ErrorCode.USER_LIST_EMPTY_EXCEPTION);}

}
