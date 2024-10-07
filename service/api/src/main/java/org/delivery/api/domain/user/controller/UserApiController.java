package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me() {

        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());

        // 스레드 로컬이 유지되는 동안 requestContext 에서 동일한 정보를 보장,
        // SCOPE_REQUEST: 하나의 Http 요청동안 유지, SCOPE_SESSION: 세션이 만료되기 전 까지 여러 HTTP 요청간에 데이터 공유
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userResponse = userBusiness.me(Long.parseLong(userId.toString()));
        return Api.OK(userResponse);
    }
}
