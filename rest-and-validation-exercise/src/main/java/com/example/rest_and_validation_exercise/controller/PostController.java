package com.example.rest_and_validation_exercise.controller;

import com.example.rest_and_validation_exercise.enums.MessageType;
import com.example.rest_and_validation_exercise.model.Api;
import com.example.rest_and_validation_exercise.model.CustomerVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/post")
public class PostController {

    @PostMapping(path = "/customer")
    public ResponseEntity<Api<CustomerVO>> addCustomer(
            @Valid
            @RequestBody
            CustomerVO customer
            // BindingResult bindingResult // controller 에서 에러를 처리할 때 사용
    ) {
        log.info("@@ post customer {}", customer);
        var body = Api.<CustomerVO>builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.getReasonPhrase())
                .data(customer)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
