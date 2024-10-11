package org.delivery.db.userordermenu.enums;

import lombok.Getter;

@Getter
public enum UserOrderMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지");

    UserOrderMenuStatus(String description) {
        this.description = description;
    }

    private final String description;

}
