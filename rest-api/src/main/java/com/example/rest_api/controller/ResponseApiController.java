package com.example.rest_api.controller;


import com.example.rest_api.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
// @RestController
@RequestMapping("/api/v1")
public class ResponseApiController {
    // @RequestMapping(path ="", method = RequestMethod.GET) 이거 보다는 명시적으로 GetMapping
    @GetMapping("")
    @ResponseBody // ResponseEntity 없이 객체를 바로 내려주려면 @RequestBody 붙여 줘야 함
    public UserRequest user(){
        var user = new UserRequest();
        user.setUserName("홍길동");
//        user.setUserAge(20);
        user.setEmail("foo@bar.com");
        user.setIs_korean(true);

        log.info("user: {}", user);

        var response =  ResponseEntity // 로직을 처리하다가 예외 발생 시 사용
                .status(HttpStatus.CREATED) 
                .header("x-custom", "hi")
                .body(user);

        return user;
    }
}
