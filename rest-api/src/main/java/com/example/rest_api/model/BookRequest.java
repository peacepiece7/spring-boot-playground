package com.example.rest_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 파라메터를 전부 받는 생성자
@NoArgsConstructor // 하나도 안받는 생성자
public class BookRequest {
    private String name;
    private String number;
    private String category;
}
