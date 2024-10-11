package com.yearly.idol.api.yearly_idol.User.controller;


import com.yearly.idol.api.yearly_idol.User.model.UserDetailDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/detail")
public class UserDetailController {

    @GetMapping("")
    public void getUserInformation() {

    }

}
