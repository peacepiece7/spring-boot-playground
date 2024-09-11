package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void login(
            LoginRequest loginRequest,
            HttpSession httpSession
    ) {

        log.info("LOGIN: {}", loginRequest);
        var id = loginRequest.getId();
        var pwd = loginRequest.getPassword();

        Optional<UserDto> user = userRepository.findByName(id);

        if(user.isEmpty()) throw new RuntimeException("User not Found");

        UserDto userDto = user.get();

        if(!userDto.getPassword().equals(pwd)) throw new RuntimeException("password not matched");

        /**
         * setAttribute 를 하면 servlet 에서 userDto 를 해시코드로 바꿔서 가지고 있음
         * 나중애 getAttribute("USER") 만 호출하면 해당 request 의 header 에 있는 세션 해시코드로 userDto 를 반환해줌
         */
        httpSession.setAttribute("USER", userDto);
    }
}
