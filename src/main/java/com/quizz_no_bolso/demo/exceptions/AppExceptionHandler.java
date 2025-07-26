package com.quizz_no_bolso.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String errorMessage = ex.getLocalizedMessage() != null ? ex.getLocalizedMessage() : ex.toString();

        return new ResponseEntity<>(
                errorMessage,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    // @ExceptionHandler(Exception.class)
    // public Exception handleAnyException(Exception ex, WebRequest request) {

    //     return ex;
    // }

}
