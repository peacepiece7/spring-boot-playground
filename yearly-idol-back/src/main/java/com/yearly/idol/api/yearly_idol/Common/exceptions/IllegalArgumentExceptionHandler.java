package com.yearly.idol.api.yearly_idol.Common.exceptions;

import com.yearly.idol.api.yearly_idol.Common.model.Api;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Order(value = 1)
public class IllegalArgumentExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Api<Object>> handleIllegalArgumentException(
            IllegalArgumentException illegalArgumentException
    ) {
        var error = Api.Error.builder()
                .errorMessage(List.of(illegalArgumentException.getMessage()))
                .build();

        var body = Api.builder()
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .errorMessageForClient(error)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
