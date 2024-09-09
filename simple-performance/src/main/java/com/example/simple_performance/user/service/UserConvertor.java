package com.example.simple_performance.user.service;


import com.example.simple_performance.user.db.UserEntity;
import com.example.simple_performance.user.model.UserDto;
import com.example.simple_performance.user.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConvertor {

    public UserDto toDto(
            UserEntity userEntity
    ) {
        return UserDto.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .registeredAt(userEntity.getRegisteredAt())
                .build();
    }
}
