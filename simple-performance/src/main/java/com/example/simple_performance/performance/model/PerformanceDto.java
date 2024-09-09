package com.example.simple_performance.performance.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PerformanceDto {

    private String title;

    private String genre;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long price;
}
