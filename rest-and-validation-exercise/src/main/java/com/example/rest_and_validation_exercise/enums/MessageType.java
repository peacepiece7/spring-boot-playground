package com.example.rest_and_validation_exercise.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    WELCOME_MESSAGE("애완 동물 진료관리 시스템"),
    CHOICE_MESSAGE("원하시는 기능의 번호를 입력해주세요."),
    CHOICE_OPTION_1("1: 신규 고객 등록"),
    CHOICE_OPTION_2("2: 진료 기록 저장"),
    CHOICE_OPTION_3("3: 진료 기록 조회"),
    CHOICE_OPTION_4("4: 진료 기록 삭제"),
    CHOICE_OPTION_5("5: 종료"),
    MISS("잘못된 선택입니다.\n 다음 기능 중 선택해주세요."),
    EXIT("프로세스를 종료합니다."),
    REGISTRATION_INFO("신규 고객 등록정보 입력 화면"),
    REGISTRATION_PLACEHOLDER_NAME("이름: "),
    REGISTRATION_PLACEHOLDER_PHONE("전화번호: "),
    REGISTRATION_PLACEHOLDER_ADDRESS("주소: "),
    REGISTRATION_PLACEHOLDER_ANIMAL_NAME("동물 이름: "),
    REGISTRATION_PLACEHOLDER_KIND("동물 종류: "),
    REGISTRATION_PLACEHOLDER_YEAR_OF_BIRTH("동물의 출생년도: "),
    REGISTRATION_PLACEHOLDER_ERROR_EXISTED_CUSTOMER("이미 존재하는 회원입니다."),
    REGISTRATION_PLACEHOLDER_SUCCESS("신규 고객이 등록되었습니다."),
    SAVE_TREATMENT_INFO("진료 기록 저장 화면"),
    SAVE_TREATMENT_PLACEHOLDER_TREATMENT_DATE("진료일: "),
    SAVE_TREATMENT_PLACEHOLDER_OWNER("소유자: "),
    SAVE_TREATMENT_PLACEHOLDER_ANIMAL_NAME("동물 이름:"),
    SAVE_TREATMENT_PLACEHOLDER_KIND("종류: "),
    SAVE_TREATMENT_PLACEHOLDER_YEAR_OF_BIRTH("동물의 출생년도: "),
    SAVE_TREATMENT_PLACEHOLDER_DETAIL("상세내용: "),
    SAVE_TREATMENT_PLACEHOLDER_ERROR_EXISTED_TREATMENT("이미 존재하는 진료 기록입니다."),
    SAVE_TREATMENT_PLACEHOLDER_SUCCESS("진료 기록이 저장되었습니다.");

    private final String message;
}
