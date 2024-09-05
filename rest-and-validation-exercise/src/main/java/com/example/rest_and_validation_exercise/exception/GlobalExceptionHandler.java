package com.example.rest_and_validation_exercise.exception;

import com.example.rest_and_validation_exercise.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @apiNote globalException
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> handleRestException(
            Exception e
    ) {
        log.error("@ restException error: {}", e.getMessage());
        var body = Api.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .data(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
