package com.example.simple_performance.user.service;

import com.example.simple_performance.user.db.UserEntity;
import com.example.simple_performance.user.db.UserRepository;
import com.example.simple_performance.user.model.UserDto;
import com.example.simple_performance.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConvertor userConvertor;

    public UserEntity create(UserRequest userRequest) {
        var userEntity = UserEntity.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .registeredAt(LocalDateTime.now())
                .build();

        return userRepository.save(userEntity);
    }

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userConvertor::toDto)
                .toList();
    }
}
