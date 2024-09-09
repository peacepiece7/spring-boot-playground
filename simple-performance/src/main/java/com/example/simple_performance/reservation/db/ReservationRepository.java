package com.example.simple_performance.reservation.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository  extends JpaRepository<ReservationEntity, Long> {
    public List<ReservationEntity> findAllByUserId(Long userId);
}
