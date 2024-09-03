package com.example.rest_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 갲쩌네!
@Data // getter, setter
@AllArgsConstructor // 파라메터 전부 받는 생성자
@NoArgsConstructor // 파라메터 안받는 기본 생성자
public class BookQueryParam {
    private String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
