package com.example.exceptionTest.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path="")
    public void hello() {
        throw new RuntimeException("run time exception called");
    }


    @GetMapping(path="outOfIndex")
    public void outOfIndex(){
        List<String> list = List.of("foo");
        var el = list.get(10);
        throw new RuntimeException("run time exception called");
    }

    @GetMapping(path={"/bye"})
    public void bye() {
        throw new NumberFormatException("Number format exception!");
    }

    @ExceptionHandler(value = { NumberFormatException.class})
    public ResponseEntity<Void> numberFormatException(
        NumberFormatException e
    ){
        log.error("NumberFormatException In RestApiController: ", e);
        return ResponseEntity.ok().build();
    }
}



