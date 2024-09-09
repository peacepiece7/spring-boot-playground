package com.example.simple_performance.performance.service;

import com.example.simple_performance.performance.db.PerformanceEntity;
import com.example.simple_performance.performance.model.PerformanceDto;
import org.springframework.stereotype.Service;

@Service
public class PerformanceConvertor {

    public PerformanceDto toDto(PerformanceEntity performanceEntity) {
        return PerformanceDto.builder()
                .title(performanceEntity.getTitle())
                .genre(performanceEntity.getGenre())
                .description(performanceEntity.getDescription())
                .startDate(performanceEntity.getStartDate())
                .endDate(performanceEntity.getEndDate())
                .price(performanceEntity.getPrice())
                .build();
    }
}
