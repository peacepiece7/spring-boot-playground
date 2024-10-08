package org.delivery.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-order")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    // 사용자 주문
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Parameter(hidden = true) // 스웨거에 안나오게 하기
            @UserSession
            User user,
            @Valid
            @RequestBody Api<UserOrderRequest> request
    ) {
        var response = userOrderBusiness.userOrder(user, request.getBody());
        return Api.OK(response);
    }

    // 현재 진행중인 주문 건
    @GetMapping("/current")
    public void current(
            @Parameter(hidden = true) // 스웨거에 안나오게 하기
            @UserSession
            User user
    ) {
        //
    }

    // 과거 주문 내역
    @GetMapping("/history")
    public void history(
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        //
    }

    // 주문 1건에 대한 내역
    @GetMapping("get/{orderId}")
    public void get(
            @Parameter(hidden = true)
            @UserSession
            User user,
            @PathVariable
            Long orderId
    ) {
    }

}
