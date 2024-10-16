package org.delivery.api.domain.userorder.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

    // 주문
    // 특정 사용자가 , 특정 메뉴를 주문
    // 특정 사용자 = 로그인된 세션에 들어있는 사용자
    // 특정 메뉴 id

    @NotNull
    @Schema(description = "주문하는 스토어 아이디")
    private Long StoreId;

    @NotNull
    @Schema(description = "주문할 메뉴 ID 목록", example = "[1, 2, 3]")
    private List<Long> storeMenuIdList;

}
