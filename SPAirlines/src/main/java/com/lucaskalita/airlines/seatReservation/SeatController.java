package com.lucaskalita.airlines.seatReservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public SeatDTO getSeatById(@PathVariable Long id) {
        return seatService.findSeatById(id);
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createSeat(SeatDTO seatDTO){
        return seatService.createSeat(seatDTO);
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