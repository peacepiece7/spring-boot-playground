package com.yearly.idol.api.yearly_idol.User.service;


import com.yearly.idol.api.yearly_idol.User.db.UserEntity;
import com.yearly.idol.api.yearly_idol.User.model.UserDetailDto;
import com.yearly.idol.api.yearly_idol.User.model.UserLocalSignUpDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserConverter {
    public UserEntity toEntityByUserLocalSignUpDto(
            UserLocalSignUpDto userLocalSignUpDto
    ) {
      return UserEntity.builder()
              .email(userLocalSignUpDto.getEmail())
              .password(userLocalSignUpDto.getPassword())
              .userName(userLocalSignUpDto.getUserName())
              .profileImage("no_profile_image.png")
              .createdAt(LocalDateTime.now())
              .updatedAt(LocalDateTime.now())
              .build();
    }

    public UserLocalSignUpDto toUserLocalSignUpDto(
            UserEntity userEntity
    ) {
        return UserLocalSignUpDto.builder()
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .build();
    }

    public UserDetailDto toUserDetailDto(
        UserEntity userEntity
    ) {
        return UserDetailDto.builder()
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .profileImage(userEntity.getProfileImage())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }
}
