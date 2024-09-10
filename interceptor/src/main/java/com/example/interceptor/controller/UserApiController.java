package com.example.interceptor.controller;


import com.example.interceptor.interceptor.OpenApi;
import com.example.interceptor.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
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
         return userRequest;
    }

    @PostMapping("/test")
    public void registerTest(
            HttpEntity<UserRequest> http // 이렇게하면 자세한 로그 출력가능 but 귀찮음
    ) {
        log.info("user: {}", http.getBody());
    }


}
