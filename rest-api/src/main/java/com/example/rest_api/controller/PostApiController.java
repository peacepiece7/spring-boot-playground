package com.example.rest_api.controller;

import com.example.rest_api.model.BookRequest;
import com.example.rest_api.model.UserRequest;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostApiController {

    /**
     * POST <a href="http://localhost:8080/api/post"></a>
     */
    @PostMapping("/post")
    public void post(
        @RequestBody BookRequest bookRequest
    ){
        System.out.println("book request: "+bookRequest);  // book request: BookRequest(name=Spring boot, number=100, category=JAVA)
    }

    /**
     * POST <a href="http://localhost:8080/api/user"></a>
     * {
     *      "userName" : "스프링 부트",
     *      "userAge" : 10,
     *      "email": "user@foo.com"
     * }
     */
    @PostMapping("/user")
    public UserRequest user(
        @RequestBody UserRequest user
        ){
        System.out.println("User request: "+user.toString());
        return user;
    }
}
