package com.example.trelloteamproject.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionResponseDto {

    private static final ObjectMapper objectmapper = new ObjectMapper();

    private HttpStatus httpStatus;

    private String message;

    public ExceptionResponseDto(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public String convertToJson() throws JsonProcessingException {
        return objectmapper.writeValueAsString(this);
    }

}
