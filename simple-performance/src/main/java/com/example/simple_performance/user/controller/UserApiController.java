package com.example.simple_performance.user.controller;

import com.example.simple_performance.user.db.UserEntity;
import com.example.simple_performance.user.model.UserDto;
import com.example.simple_performance.user.model.UserRequest;
import com.example.simple_performance.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserEntity> create(
            @Valid
            @RequestBody
            UserRequest userRequest
    ){
        var userEntity = userService.create(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        var userDto = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
}
