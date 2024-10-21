package org.delivery.api.domain.userordermenu.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderMenuResponse {
    private Long id;
    private Long userOrderId;
    private Long storeMenuId;
    private UserOrderMenuStatus status;
}
