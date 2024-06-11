package com.lucaskalita.airlines.flight.flightController;

import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.flightService.FlightBasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightBasicController {

    private final FlightBasicService flightService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createNewFlight(flightDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public FlightDTO getFlightById(@PathVariable Long id) {

        return flightService.findFlightById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlightById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDTO updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }

}
