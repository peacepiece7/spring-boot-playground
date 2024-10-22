package org.delivery.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.common.api.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
@Slf4j
public class UserOpenApiController {

    private final UserBusiness userBusiness;
    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping("/register")
    public Api<UserResponse> register(
            @RequestBody
            Api<UserRegisterRequest> request
    ) {
        var res = userBusiness.register(request.getBody());
        return Api.OK(res);
    }

    // 로그인
    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ) {
        var res = userBusiness.login(request.getBody());
        return Api.OK(res);
    }
}
