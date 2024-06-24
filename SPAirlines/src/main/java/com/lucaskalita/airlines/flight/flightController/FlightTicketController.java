package com.lucaskalita.airlines.flight.flightController;

import com.lucaskalita.airlines.flight.flightService.FlightTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightTicketController {

    private final FlightTicketService flightService;

    @GetMapping("/seats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String checkForEmptySeats(@PathVariable Long id) {
        return flightService.checkForEmptySeats(id);
    }
}