package org.delivery.api.domain.user.controller;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
    ) {

        /*
          HandlerMethodArgumentResolver 사용할 경우)
          userSessionResolver 에서 UserResponse 내려주고 있기 때문에, 바로 리턴 해도 됨

          RequestContextHolder 쓰던지 UserSession 쓰던지 결국 디펜던시를 가지는건 같으니까 쓰고 싶은거 쓰면 되지 않을까?
          controller -> business 이렇게 하던지, resolver -> service or business 이렇게 하던지 같은 느낌
         */
        log.info("@@@@@ GET api/user/me :{}", user.toString());
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());

        /*
          RequestContextHolder 사용할 경우)
          스레드 로컬이 유지되는 동안 requestContext 에서 동일한 정보를 보장,
          SCOPE_REQUEST: 하나의 Http 요청동안 유지, SCOPE_SESSION: 세션이 만료되기 전 까지 여러 HTTP 요청간에 데이터 공유
          ++
            vue3 inject, provide 같은거임 요청 스레드 동안 특정 변수의 값을 저장해놓고, 여기저기서 불러와서 쓰는거임
            AuthorizationInterceptor.java 에서 로그인 체크하고 사용자 아이디를 inject("userId", token.userId) 이런 식으로 inject
          ++
        */
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userResponse = userBusiness.me(Long.parseLong(userId.toString()));
        return Api.OK(userResponse);
    }
}
