package com.example.exceptionTest.controller;

import com.example.exceptionTest.model.Api;
import com.example.exceptionTest.model.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private static List<UserResponse> userList = List.of(
        UserResponse.builder().id("1").age(20).name("foo").build(),
        UserResponse.builder().id("2").age(30).name("bar").build()
    );

    @GetMapping("/id/{userId}")
    public Api<UserResponse> getUser(
        @PathVariable String userId
    ) {

        if(userId.equals("3")) {
            throw new RuntimeException("something error occurred!!");
        }
        
        var user = userList.stream()
                .filter(it -> it.getId().equals(userId))
                .findFirst()
                .get();

        return Api.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.name())
                .data(user)
                .build();
    }
}
