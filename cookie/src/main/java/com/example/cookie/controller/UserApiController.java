package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public void me(
            HttpServletRequest httpServletRequest
    )    {
        var cookies = httpServletRequest.getCookies();

        if(cookies != null) {
            for(Cookie cookie : cookies) {
                log.info("key: {}, value: {}", cookie.getName(), cookie.getValue());
            }
        }
    }


    @GetMapping("/me/cookie")
    public UserDto cookieAnnotation
            (
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "authorization-cookie",required = false)
            String authorizationCookie
    )    {
        log.info("authorizationCookie: {}", authorizationCookie);
        return userRepository.findById(authorizationCookie).get();
    }
}
