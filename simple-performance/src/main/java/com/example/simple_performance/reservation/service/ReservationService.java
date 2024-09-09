package com.example.simple_performance.reservation.service;

import com.example.simple_performance.reservation.db.ReservationEntity;
import com.example.simple_performance.reservation.db.ReservationRepository;
import com.example.simple_performance.reservation.model.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    // @Transactional
    public ReservationEntity fetch(
            ReservationRequest reservationRequest
    ) {
        var entity = ReservationEntity.builder()
                .userId(reservationRequest.getUserId())
                .performanceId(reservationRequest.getPerformanceId())
                .reservationDate(reservationRequest.getReservationDate())
                .paymentStatus("Unpaid")
                .build();

         return reservationRepository.save(entity);
    }

    public List<ReservationEntity> findAllByUserId(Long id) {
        return reservationRepository.findAllByUserId(id);
    }
}
