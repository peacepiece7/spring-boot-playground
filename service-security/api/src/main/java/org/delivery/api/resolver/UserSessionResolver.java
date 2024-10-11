package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 위치: 스프링 MVC 컨트롤러 레벨 스프링의 컨트롤러 메서드의 파라미터를 자동으로 바인딩해주는 기능을 제공
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 1. 어노테이션 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);

        // 2. 파라미터 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        log.info("@ annotation && parameterType : {}", annotation && parameterType);
        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        log.info("@ userId : {}", userId.toString());

        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));
        log.info("@ userEntity : {}", userEntity.toString());

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLogin())
                .build();
    }
}
