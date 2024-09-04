package com.example.rest_api.controller;

import com.example.rest_api.model.MockCategoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RestApiTestController {

    @GetMapping(path = "/mock")
    public ResponseEntity<MockCategoryRequest> category(
            @RequestParam String id,
            @RequestParam List<String> category
    ){
        log.info("mock id: {}", id);
        log.info("mock category: {}", category);
        var mockCategory = new MockCategoryRequest(id, category);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mockCategory);
    }
}
