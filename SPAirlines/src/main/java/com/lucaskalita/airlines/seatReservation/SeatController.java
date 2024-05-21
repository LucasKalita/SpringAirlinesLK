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
    @ResponseStatus(HttpStatus.FOUND)
    public List<SeatDTO> getAllUnReservedSeats() {
        return seatService.findAllUnReservedSeats();
    }

    @GetMapping("/reserved")
    @ResponseStatus(HttpStatus.FOUND)
    public List<SeatDTO> getAllReservedSeats() {
        return seatService.findAllReservedSeats();
    }

    @GetMapping("/regular")
    @ResponseStatus(HttpStatus.OK)
    public List<SeatDTO> getAllRegularSeats() {
        return seatService.findAllRegularSeats();
    }

    @GetMapping("/premium")
    public List<SeatDTO> getAllPremiumSeats() {
        return seatService.findAllPremiumSeats();
    }
    }