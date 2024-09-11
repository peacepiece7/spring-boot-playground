package com.example.session.controller;

import com.example.session.model.LoginRequest;
import com.example.session.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final UserService userService;

    @PostMapping("/login")
    public void login (
            @RequestBody
            LoginRequest loginRequest,
            HttpSession httpSession // 특별한 어노테이션 없어도 이렇게 추가하면 알아서 세션을 인자로 받아옴
    ) {
        userService.login(loginRequest, httpSession);
    }
}