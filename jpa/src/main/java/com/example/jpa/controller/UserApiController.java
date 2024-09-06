package com.example.jpa.controller;

import com.example.jpa.db.UserEntity;
import com.example.jpa.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/save")
    public void save(
            @RequestParam
            String name,
            @RequestParam
            String email,
            @RequestParam
            Integer age

    ) {
        var user = UserEntity.builder()
                .name(name)
                .email(Optional.ofNullable(email).orElse(""))
                .age(Optional.ofNullable(age).orElse(0))
                .build();
        userRepository.save(user);
    }
}

