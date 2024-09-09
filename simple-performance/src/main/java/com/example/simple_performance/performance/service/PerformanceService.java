package com.example.simple_performance.performance.service;

import com.example.simple_performance.performance.db.PerformanceEntity;
import com.example.simple_performance.performance.db.PerformanceRepository;
import com.example.simple_performance.performance.model.PerformanceDto;
import com.example.simple_performance.performance.model.PerformanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PerformanceConvertor performanceConvertor;

    public PerformanceEntity create(
            PerformanceRequest performanceRequest
    ) {
        var performanceEntity = PerformanceEntity.builder()
                .title(performanceRequest.getTitle())
                .genre(performanceRequest.getGenre())
                .description(performanceRequest.getDescription())
                .startDate(performanceRequest.getStartDate())
                .endDate(performanceRequest.getEndDate())
                .price(performanceRequest.getPrice())
                .build();

        return performanceRepository.save(performanceEntity);
    }

    public List<PerformanceDto> findAll() {
        return performanceRepository
                .findAll()
                .stream()
                .map(performanceConvertor::toDto)
                .toList();
    }

    public List<PerformanceDto> findBySearchParameter(String title, String genre, LocalDateTime startDate, LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return performanceRepository
                .findByTitleAndGenreAndDates(
                        title,
                        genre,
                        startDate,
                        endDate
                )
                .stream()
                .map(performanceConvertor::toDto)
                .toList();
    }
}
