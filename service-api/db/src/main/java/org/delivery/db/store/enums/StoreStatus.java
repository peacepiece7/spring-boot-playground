package org.delivery.db.store.enums;

public enum StoreStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지");

    private final String description;

    StoreStatus(String description) {
        this.description = description;
    }

    String getDescription() {
        return this.description;
    }
}
