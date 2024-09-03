package com.example.rest_api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy; // deprecated
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter setter
@AllArgsConstructor // all 생성자
@NoArgsConstructor // 기본 생성자
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // snake_case 변경
public class UserRequest {
    private String userName;
    private int userAge;
    private String email;
    private Boolean is_korean;
}
