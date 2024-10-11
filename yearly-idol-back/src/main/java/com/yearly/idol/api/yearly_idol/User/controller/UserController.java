package com.yearly.idol.api.yearly_idol.User.controller;

import com.yearly.idol.api.yearly_idol.Common.model.Api;
import com.yearly.idol.api.yearly_idol.User.model.UserLocalSignUpDto;
import com.yearly.idol.api.yearly_idol.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Api<Object>> signUp(
            @Valid
            @RequestBody
            UserLocalSignUpDto userLocalLoginDto
    ) {
        var response = userService.signUp(userLocalLoginDto);
        var body = Api.builder()
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PostMapping("/login")
    public void login() {

    }

    @PostMapping("/logout")
    public void logout() {

    }
}