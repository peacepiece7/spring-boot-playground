package com.example.aop.controller;


import com.example.aop.interceptor.OpenApi;
import com.example.aop.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @OpenApi
    @GetMapping("/hi")
    public void hi() {
        log.info("@ HI!");
    }

    @GetMapping("/hello")
    public void hello() {
        log.info("@ HELLO!");
    }

    @PostMapping("")
    public UserRequest register(
            @RequestBody
            UserRequest userRequest
    ) {
         log.info("userRequest: {}", userRequest);
         return userRequest;
    }

    @PostMapping("/test")
    public void registerTest(
            HttpEntity<UserRequest> http // 이렇게하면 자세한 로그 출력가능 but 귀찮음
    ) {
        log.info("user: {}", http.getBody());
    }
}
