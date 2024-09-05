package com.example.rest_and_validation_exercise.controller;

import com.example.rest_and_validation_exercise.model.CustomerVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api")
public class RestApiController {

    @GetMapping("/hi")
    public String hi() {
        return "hi!";
    }
}
