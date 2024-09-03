package com.example.rest_api.controller;

import com.example.rest_api.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put")
    public void put(
        @RequestBody
        UserRequest userRequest
    ) {
        // System.out -> 이거는 많이 찍으면 성능 문제 생김
        // logback 같은거 쓰면 커스텀 + 버퍼에 콘솔을 넣어서 출력하기 떄문에 성능 문제가 적음 (버퍼 다 차면 같음)
        log.info("REQUEST: {}", userRequest);
    }
}
