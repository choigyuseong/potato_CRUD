package com.example.potato_tuto.exception.requestError;

import com.example.potato_tuto.exception.ErrorCode;

public class UnAuthorizedUserAccessException extends BusinessException {
    public UnAuthorizedUserAccessException(String message) { super(message, ErrorCode.USER_LIST_EMPTY_EXCEPTION);}

}
