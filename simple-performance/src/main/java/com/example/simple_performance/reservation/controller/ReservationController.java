package com.example.simple_performance.reservation.controller;

import com.example.simple_performance.reservation.db.ReservationEntity;
import com.example.simple_performance.reservation.model.ReservationRequest;
import com.example.simple_performance.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    public ReservationEntity fetch(
            @RequestBody
            @Valid
            ReservationRequest reservationEntity
    ) {
        return reservationService.fetch(reservationEntity);
    }

    @GetMapping("id/{id}")
    public List<ReservationEntity> findAllByUserId(
            @PathVariable
            Long id
    ) {
        return reservationService.findAllByUserId(id);
    }
}
