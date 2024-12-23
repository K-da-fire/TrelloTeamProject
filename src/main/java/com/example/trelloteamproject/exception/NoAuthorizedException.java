package com.example.trelloteamproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoAuthorizedException extends RuntimeException {
    private final ErrorCode errorCode;
}
