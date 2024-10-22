package org.delivery.api.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.common.annotation.Business;
import org.delivery.db.user.UserRepository;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자 가업 차리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save entity -> response
     * 4. response return
     */
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        var convertedUserEntity = userConverter.toEntity(userRegisterRequest);
        var registeredUserEntity = userService.register(convertedUserEntity);
        return userConverter.toResponse(registeredUserEntity);
    }

    /**
     * 로그인 로직
     * 1. email, password 로 사용자 체크
     * 2. userEntity 로그인 확인
     * 3. token 생성
     * 4. token response
     */
    public TokenResponse login(UserLoginRequest body) {
        var userEntity = userService.login(body.getEmail(), body.getPassword());
        return tokenBusiness.issueToken(userEntity);
    }

    public UserResponse me(Long userId) {
        var userEntity = userService.getUserWithThrow(userId);
        return userConverter.toResponse(userEntity);
    }
}