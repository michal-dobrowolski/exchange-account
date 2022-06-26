package com.example.task;

import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class, MongoWriteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error handleException(Exception exception) {
        return new Error("Error", exception.getMessage());
    }

}