package com.example.simple_performance.performance.db;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity(name = "performance")
public class PerformanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id", nullable = false)
    private Long id;

    private String title;

    private String genre;

    // @Nullable
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long price;

    // auto-generated
    private LocalDateTime createdAt;
}
