package org.delivery.api.domain.userorder.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
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

    private final UserOrderMenuService userOrderMenuService;
    private final UserOrderMenuConverter userOrderMenuConverter;

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public UserOrderResponse userOrder(
            User user,
            UserOrderRequest request
    ) {
        var menuIds = request.getStoreMenuIdList();
        var storeMenuEntityList = storeMenuService.getStoreMenuListByStoreIds(menuIds);

        // 주문
        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> userOrderMenuConverter.toEntity(newUserOrderEntity, it))
                .toList();

        // 주문내역 기록 남기기
        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        return userOrderConverter.toResponse(newUserOrderEntity);
    }

    public List<UserOrderDetailResponse> current(User user) {

        var userOrderEntityList = userOrderService.current(user.getId());

        return userOrderEntityList.stream().map(it -> {

            var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenuList(it.getId());

            var storeMenuEntityList = userOrderMenuEntityList.stream()
                    .map(userOrder -> storeMenuService.getStoreMenuWithThrow(userOrder.getStoreMenuId()))
                    .toList();

            var storeEntity = storeService.getStoreWithThrow(
                    storeMenuEntityList.stream().findFirst().get().getStoreId());

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(it))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).toList();
    }

    public List<UserOrderDetailResponse> history(User user) {

        var userOrderEntityList = userOrderService.history(user.getId());

        return userOrderEntityList.stream().map(it -> {

            var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenuList(it.getId());

            var storeMenuEntityList = userOrderMenuEntityList.stream()
                    .map(it2 -> storeMenuService.getStoreMenuWithThrow(it2.getStoreMenuId()))
                    .toList();

            var storeEntity = storeService.getStoreWithThrow(
                    storeMenuEntityList.stream().findFirst().get().getStoreId());

            return UserOrderDetailResponse.builder()
                    .userOrderResponse(userOrderConverter.toResponse(it))
                    .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
                    .storeResponse(storeConverter.toResponse(storeEntity))
                    .build();
        }).toList();
    }

    public UserOrderDetailResponse read(User user, Long orderId) {
        var userOrderEntity = userOrderService.read(orderId, user.getId());

        var userOrderMenuEntityList = userOrderMenuService.getUserOrderMenuList(userOrderEntity.getUserId());

        var storeMenuList = userOrderMenuEntityList.stream()
                .map(it -> storeMenuService.getStoreMenuByStoreId(it.getStoreMenuId())).toList();

        var storeEntity = storeService.getStoreWithThrow(storeMenuList.stream().findFirst().get().getStoreId());

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuList))
                .storeResponse(storeConverter.toResponse(storeEntity))
                .build();
    }
}
