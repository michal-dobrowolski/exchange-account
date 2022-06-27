package com.example.task;

import com.mongodb.MongoWriteException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class, DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Error handleException(Exception exception) {
        if (exception instanceof DuplicateKeyException) {
            return new Error("Error", "Account already exist with provided pesel number.");
        }
        return new Error("Error", exception.getMessage());
    }

}