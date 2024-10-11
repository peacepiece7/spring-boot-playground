package com.yearly.idol.api.yearly_idol.User.service;

import com.yearly.idol.api.yearly_idol.User.db.UserRepository;
import com.yearly.idol.api.yearly_idol.User.model.UserLocalSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserLocalSignUpDto signUp(
            UserLocalSignUpDto userLocalLoginRequestDto
    ) {
        var userEntity = userConverter.toEntityByUserLocalSignUpDto(userLocalLoginRequestDto);
        var user = userRepository.findByEmail(userEntity.getEmail());
        if(user.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }

        userRepository.save(userEntity);

        return userConverter.toUserLocalSignUpDto(userEntity);
    }
}
