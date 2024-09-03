package com.example.rest_api.controller;

import com.example.rest_api.model.BookQueryParam;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(path = "/hello")
    public String hello() {
        var html = "<html> <body> <h1>";
        html += "Hello Spring boot";
        html += " </h1> </body> </html>";
        return html;
    }

    /**
     * @apiNote url path 변수로 받기
     * @implNote <a href="http://localhost:8080/api/echo/steve/age/20/is-man/true">url</a>
     */
    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable(name = "message") String msg,
            @PathVariable int age,
            @PathVariable boolean isMan
    ) {
        System.out.println("echo message :"+msg);
        System.out.println("echo age :"+age);
        System.out.println("echo isMan :"+isMan);

        return msg.toUpperCase();
    };

    /**
     * @apiNote 쿼리 스트링
     * @implNote <a href="http://localhost:8080/api/book?category=foo&issuedYear=2022&issued-month=07&issued_day=30">url</a>
     */
    @GetMapping(path = "/book")
    public String queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name = "issued-month") String issuedMonth, // issued-month 로 받고 변수는 issuedMonth
            @RequestParam String issued_day
    ) {
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issued_day);
        // camelCase 나 kebab-case 를 추천

        return category + " " + issuedYear + " " + issuedMonth + " " + issued_day;
    }


    @GetMapping(path = "/book2")
    public String queryParamDTO(BookQueryParam bookQueryParam) {
        System.out.println(bookQueryParam);
        AtomicReference<String> response = new AtomicReference<>(Arrays.stream(bookQueryParam.getClass().getDeclaredFields())
                .map(field -> getFieldValue(field, bookQueryParam)).reduce("", (acc, value) -> acc + value));
        return response.get();
    }

    private String getFieldValue(Field field, BookQueryParam bookQueryParam) {
        field.setAccessible(true); // private 필드에도 접근할 수 있도록 설정
        try {
            Object value = field.get(bookQueryParam);
            return value != null ? value.toString() : "";
        } catch (IllegalAccessException e) {
            return "";
        }
    }
    
    // 사칙연산 하나 만들어보기
}
