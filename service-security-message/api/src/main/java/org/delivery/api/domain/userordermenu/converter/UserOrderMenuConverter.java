package org.delivery.api.domain.userordermenu.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.userordermenu.controller.model.UserOrderMenuResponse;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
            UserOrderEntity userOrderEntity,
            StoreMenuEntity storeMenuEntity
    ) {
        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getStoreId())
                .status(UserOrderMenuStatus.REGISTERED)
                .build();
    }

    public UserOrderMenuResponse toResponse(
            UserOrderMenuEntity userOrderMenuEntity
    ) {
        return UserOrderMenuResponse.builder()
                .id(userOrderMenuEntity.getId())
                .userOrderId(userOrderMenuEntity.getUserOrderId())
                .storeMenuId(userOrderMenuEntity.getStoreMenuId())
                .status(userOrderMenuEntity.getStatus())
                .build();
    }
}