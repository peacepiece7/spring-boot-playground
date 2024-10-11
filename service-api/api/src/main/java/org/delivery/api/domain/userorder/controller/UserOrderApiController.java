package org.delivery.api.domain.userorder.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.api.Result;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 주문 (User Order)", description = "사용자 주문 관련 API (담당자: 정태욱)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-order")
public class UserOrderApiController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserOrderResponse 반환",
                    content = @Content(schema = @Schema(implementation = UserOrderResponse.class))),
            @ApiResponse(responseCode = "40x", description = "Result 에러 객체 반환",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "50x", description = "Result 에러 객체 반환",
                    content = @Content(schema = @Schema(implementation = Result.class)))
    })
    // @Parameters({@Parameter(name = "request", description = "예시") })
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
    public Api<List<UserOrderDetailResponse>> current(
            @Parameter(hidden = true) // 스웨거에 안나오게 하기
            @UserSession
            User user
    ) {
        var response = userOrderBusiness.current(user);
        return Api.OK(response);
    }

    // 과거 주문 내역
    @GetMapping("/history")
    public Api<List<UserOrderDetailResponse>> history(
            @Parameter(hidden = true)
            @UserSession
            User user
    ) {
        var response = userOrderBusiness.history(user);

        return Api.OK(response);
    }

    // 주문 1건에 대한 내역
    @GetMapping("get/{orderId}")
    public Api<UserOrderDetailResponse> read(
            @Parameter(hidden = true)
            @UserSession
            User user,
            @PathVariable
            Long orderId
    ) {
        var response = userOrderBusiness.read(user, orderId);
        return Api.OK(response);
    }
}
