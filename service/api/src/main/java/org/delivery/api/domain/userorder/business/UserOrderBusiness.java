package org.delivery.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;

    //  controller -> 사용자가 매뉴 아이디를 여러개 담아서 요청(UserOrderRequest)
    //  business ->
    //  파라매터로 사용자 정보(세션), 매뉴 아이디 리스트 (UserOrderRequest) 받음
    //  - 매뉴 아이디 리스트로 주문 매뉴 가져오기
    //  - 사용자 정보로 UserOrder 생성 하기
    //  주문 매뉴 리스트 + UserOrder id를 조합해서 userOrderMenu 테이블에 기록하기
    //
    public UserOrderResponse userOrder(
            User user,
            UserOrderRequest request
    ) {
        // var storeMenuEntityList = request.getStoreMenuIdList().stream()
        //         .map(storeMenuService::getStoreMenuWithThrow)
        //         .toList();

        var menuIds = request.getStoreMenuIdList();
        var storeMenuEntityList = storeMenuService.getStoreMenuListByIdIn(menuIds);

        // 주문
        var newUserOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        // 맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> userOrderMenuConverter.toEntity(newUserOrderEntity, it))
                .toList();

        // 주문내역 기록 남기기
        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}
