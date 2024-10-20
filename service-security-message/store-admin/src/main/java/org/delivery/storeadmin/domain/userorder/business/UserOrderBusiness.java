package org.delivery.storeadmin.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.storeadmin.domain.sse.connection.model.SseConnectionPool;
import org.delivery.storeadmin.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.storeadmin.domain.storemenu.service.StoreMenuService;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.storeadmin.domain.userorder.converter.UserOrderConverter;
import org.delivery.storeadmin.domain.userorder.service.UserOrderService;
import org.delivery.storeadmin.domain.userordermenu.service.UserOrderMenuService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;

    private final UserOrderMenuService userUserOrderMenuService;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final SseConnectionPool sseConnectionPool;

    /**
     * 주문
     * 주문 내역 찾기
     * 스토어 메뉴 찾기
     * 연결된 세션 찾아서
     * 메시지 전송
     */
    /**
     * @implNote 1. 주문 정보를 찾는다(userOrder)
     * 2. 주문 내역을 찾는다 (userOrderMenu)
     * 3. 스토어 메뉴를 찾는다 (storeMenu)
     * 4. 연결되어 있는 세션을 찾는다.
     * 5. 주문 내역, 주문 매뉴가 담긴 메시지를 전송한다.
     */
    public void pushUserOrder(UserOrderMessage userOrderMessage) {

        var userOrderEntity = userOrderService.getUserOrder(userOrderMessage.getUserOrderId())
                .orElseThrow(() -> new RuntimeException("주문 정보가 없습니다."));

        var userOrderId = userOrderEntity.getId(); // userOrderMessage 에 있는 userOrderId랑 동일

        // 주문내역 찾기
        var userOrderMenuList = userUserOrderMenuService.getUserOrderMenuList(userOrderId);

        // 스토어 메뉴 찾기
        var storeMenuResponseList = userOrderMenuList.stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it.getStoreMenuId()))
                .map(storeMenuConverter::toResponse)
                .toList();

        // @TODO: queue 에서 꺼낸 데이터 전송 실패시, 전송에 실패한 데이터만 모아두는 queue 가 따로 있어야 할 것 같음..!
        var userOrderResponse = userOrderConverter.toResponse(userOrderEntity);

        var userOrderDetailMessage = UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderResponse)
                .storeMenuResponseList(storeMenuResponseList)
                .build();

        // 연결된 세션 찾기
        log.info("CONNECTED SESSION: {}", sseConnectionPool.getAllSessions());
        var userConnection = sseConnectionPool.getSession(userOrderEntity.getStoreId().toString());

        if(userConnection == null) {
            log.info("userOrderEntity: {}", userOrderEntity);
            throw new RuntimeException("연결된 세션정보가 없습니다.");
        }

        // 메시지 전송
        userConnection.sendMessage(userOrderDetailMessage);
    }


    private StoreMenuEntity getStoreMenuList(UserOrderMenuEntity it) {
        return storeMenuService.getStoreMenuWithThrow(it.getStoreMenuId());
    }
}
