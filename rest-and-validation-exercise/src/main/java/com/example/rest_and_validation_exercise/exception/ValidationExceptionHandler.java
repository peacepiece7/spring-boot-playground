package com.example.rest_and_validation_exercise.exception;

import com.example.rest_and_validation_exercise.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@Slf4j
@RestControllerAdvice
@Order(value = 1)
public class ValidationExceptionHandler  {

    @ExceptionHandler (value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Api<? extends Object>> validationException(
        MethodArgumentNotValidException exception
    ) {
        var errorMessageList = exception.getFieldErrors().stream()
                .map(it -> {
                    var format = "%s : { %s } 은 %s";
                    return String.format(format, it.getField(), it.getRejectedValue(), it.getDefaultMessage());
                }).toList();

        var error = Api.Error
                .builder()
                .errorMessage(errorMessageList)
                .build();

        var errorResponse = Api.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(String.valueOf(HttpStatus.BAD_REQUEST.getReasonPhrase()))
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(errorResponse);
    }
}
