package com.yearly.idol.api.yearly_idol.Common.exceptions;


import com.yearly.idol.api.yearly_idol.Common.model.Api;
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
public class ValidationExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Object>> validationException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        log.error("ValidationExceptionHandler: {}",methodArgumentNotValidException.getMessage());
        var errorMsgList = methodArgumentNotValidException
                .getFieldErrors()
                .stream().
                map(it -> {
                    var format = "%s : { %s } 은 %s";
                    return String.format(
                            format,
                            it.getField().replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase(), // snake_case 로 변경(코드가 camelCase 라서 요런 작업이 필요함..!)
                            it.getRejectedValue(),
                            it.getDefaultMessage());
                })
                .toList();

        var error = Api.Error
                .builder()
                .errorMessage(errorMsgList)
                .build();

        var body = Api.builder()
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errorMessageForClient(error)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
