package com.example.trelloteamproject.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    //커스텀
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> duplicatedException(DuplicatedException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrorCode()), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> internalServerException(InternalServerException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrorCode()), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> invalidInputException(InvalidInputException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrorCode()), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrorCode()), e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> noAuthorizedException(NoAuthorizedException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(e.getErrorCode()), e.getErrorCode().getHttpStatus());
    }

    //자바
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> constrainViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(new ExceptionResponseDto(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
    }
}
