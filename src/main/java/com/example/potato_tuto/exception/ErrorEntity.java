package com.example.potato_tuto.exception;

import lombok.Builder;
import lombok.Data;

@Data

public class ErrorEntity {
    private int errorCode;
    private String errorMessage;
    @Builder
    public ErrorEntity(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}