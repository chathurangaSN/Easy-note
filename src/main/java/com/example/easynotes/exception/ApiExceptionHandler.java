package com.example.easynotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //1.create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiError apiException = new ApiError(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
        );
        //2.Return entity
        return new ResponseEntity<>(apiException,badRequest);
    }
}
