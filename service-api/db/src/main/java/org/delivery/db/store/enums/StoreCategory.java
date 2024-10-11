package org.delivery.db.store.enums;

public enum StoreCategory {

    // 중식
    CHINESE_FOOD("중식","중식"),
    // 양식
    WESTERN_FOOD("양식","양식"),
    // 한식
    KOREAN_FOOD("한식","한식"),
    // 일식
    JAPANESE_FOOD("일식","일식"),
    // 치킨
    CHICKEN("치킨","치킨"),
    // 피자
    PIZZA("피자","피자"),
    // 햄버거
    HAMBURGER("햄버거","햄버거"),
    // 커피
    COFFEE_TEA("커피&차","커피&차");

    private final String display;
    private final String description;

    StoreCategory(String display, String description) {
        this.display = display;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplay() {
        return display;
    }
}
