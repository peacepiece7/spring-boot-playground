package com.example.rest_and_validation_exercise.exception;


import com.example.rest_and_validation_exercise.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.UnexpectedException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
@Order(value = 2)
public class RestApiExceptionHandler {

    /**
     * @apiNote Unexpected Exception
     */
    @ExceptionHandler(value = {UnexpectedException.class})
    public ResponseEntity<Api<Object>> handleUnExceptedTypeException(
            RuntimeException e
    ) {
        log.error("@@ UnExceptedTypeException Exception error: {}", e.getMessage());
        var body = Api.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(body);
    }


    /**
     * @apiNote Runtime Exception
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Api<Object>> handleRuntimeException(
            RuntimeException e
    ) {
        log.error("@@ runtime Exception error: {}", e.getMessage());
        var body = Api.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(body);
    }

    /**
     * @apiNote 404 not found exception
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api<Object>> handleNoSuchElementException(
            NoSuchElementException e
    ) {
        log.error("@ no such exception error: {}", e.getMessage());
        var body = Api.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
