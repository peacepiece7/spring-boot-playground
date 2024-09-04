package com.example.exceptionTest.exception;

import com.example.exceptionTest.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
// @RestControllerAdvice // 이거는 글로벌 익셉션 컨트롤러임
// basePackages, class, annotation 지정 가능!
@Order (value = 1)
@RestControllerAdvice(basePackages = "com.example.exceptionTest.controller")
public class RestApiExceptionHandler {
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity<Void> HandleOutOfBoundException(
        IndexOutOfBoundsException e
    ) {
        log.error("@@ IndexOutOfBoundsException: ", e);
        return ResponseEntity.status(200).build();
    }


    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api<Object>> HandleNoSuchElementException(
        NoSuchElementException e
    ) {
        log.error("@@ NoSuchElementException: ", e);
        var response = Api
                .builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.name())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

