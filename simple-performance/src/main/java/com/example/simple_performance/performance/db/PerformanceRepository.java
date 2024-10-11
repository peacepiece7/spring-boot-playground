package com.example.simple_performance.performance.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PerformanceRepository extends JpaRepository<PerformanceEntity, Long> {
    @Query("SELECT p FROM performance p WHERE " +
            "(:title IS NULL OR p.title LIKE %:title%) AND " +
            "(:genre IS NULL OR p.genre LIKE %:genre%) AND " +
            "(:startDate IS NULL OR p.startDate >= :startDate) AND " +
            "(:endDate IS NULL OR p.endDate <= :endDate)")
    List<PerformanceEntity> findByTitleAndGenreAndDates(
            @Param("title") String title,
            @Param("genre") String genre,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
