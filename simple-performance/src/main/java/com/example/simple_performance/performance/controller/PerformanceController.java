package com.example.simple_performance.performance.controller;

import com.example.simple_performance.performance.db.PerformanceEntity;
import com.example.simple_performance.performance.model.PerformanceDto;
import com.example.simple_performance.performance.model.PerformanceRequest;
import com.example.simple_performance.performance.service.PerformanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/performance")
@RequiredArgsConstructor
public class PerformanceController {
    private final PerformanceService performanceService;


    @PostMapping("")
    public ResponseEntity<PerformanceEntity> create(
            @Valid
            @RequestBody
            PerformanceRequest performanceRequest
    ) {
        var performanceEntity = performanceService.create(performanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(performanceEntity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PerformanceDto>> findAll() {
        var performanceDto = performanceService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(performanceDto);
    }

    @GetMapping("/find")
    public ResponseEntity<List<PerformanceDto>> findBySearchParameter(
            @RequestParam(required = false)
            String title,

            @RequestParam(required = false)
            String genre,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate
    ){
        var performanceDto = performanceService.findBySearchParameter(title, genre, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(performanceDto);
    }
}
