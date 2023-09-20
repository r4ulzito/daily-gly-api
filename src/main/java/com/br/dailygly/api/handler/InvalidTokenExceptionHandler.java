package com.br.dailygly.api.handler;

import com.br.dailygly.api.exception.InvalidTokenException;
import com.br.dailygly.api.handler.response.DefaultCustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class InvalidTokenExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<DefaultCustomExceptionResponse> invalidJwtToken(InvalidTokenException ex) {

        DefaultCustomExceptionResponse error = new DefaultCustomExceptionResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
