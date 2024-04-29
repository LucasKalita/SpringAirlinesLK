package com.lucaskalita.airlines.seatReservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id) {
        SeatDTO seatDTO = seatService.findSeatById(id);
        if (seatDTO != null) {
            return ResponseEntity.ok(seatDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/unreserved")
    public ResponseEntity<List<?>> getAllUnReservedSeats() {
        List<?> unreservedSeats = seatService.findAllUnReservedSeats();
        return ResponseEntity.ok(unreservedSeats);
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<?>> getAllReservedSeats() {
        List<?> reservedSeats = seatService.findAllReservedSeats();
        return ResponseEntity.ok(reservedSeats);
    }

    @GetMapping("/regular")
    public ResponseEntity<List<?>> getAllRegularSeats() {
        List<?> regularSeats = seatService.findAllRegularSeats();
        return ResponseEntity.ok(regularSeats);
    }

    @GetMapping("/premium")
    public ResponseEntity<List<?>> getAllPremiumSeats() {
        List<?> premiumSeats = seatService.findAllPremiumSeats();
        return ResponseEntity.ok(premiumSeats);
    }
    }