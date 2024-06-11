package com.lucaskalita.airlines.flight.flightController;

import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.flightService.FlightDayTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightDayTimeController {

    private final FlightDayTimeService flightService;

    @GetMapping("/timeBeforeArrival/{arrTime}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBeforeArrivalDate(@PathVariable LocalDateTime arrTime) {
        return flightService.findFlightsBeforeArrivalDate(arrTime);
    }

    @GetMapping("/timeAfterDeparture/{arrTime}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsAfterArrivalDate(@PathVariable LocalDateTime arrTime) {
        return flightService.findFlightsAfterArrivalDate(arrTime);
    }

    @GetMapping("/timeBeforeArrival/{depTime}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBeforeDepartureDate(@PathVariable LocalDateTime depTime) {
        return flightService.findFlightsByDepartureDateBefore(depTime);
    }

    @GetMapping("/timeAfterDeparture/{depTime}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsAfterDepartureDate(@PathVariable LocalDateTime depTime) {
        return flightService.findFlightsByDepartureDateAfter(depTime);
    }

    @GetMapping("/timeBetweenArrival/{arrTime1}_{arrTime2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenArrival(@PathVariable LocalDateTime arrTime1, @PathVariable LocalDateTime arrTime2) {
        return flightService.findFlightsBetweenArrivalDates(arrTime1, arrTime2);
    }

    @GetMapping("/timeBetweenArrival/{depTime1}_{depTime2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenDeparture(@PathVariable LocalDateTime depTime1, @PathVariable LocalDateTime depTime2) {
        return flightService.findFlightsByDepartureDateBetween(depTime1, depTime2);
    }

    @GetMapping("/timeWindow/{depTime}_{arrTime}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsInTimeWindow(@PathVariable LocalDateTime depTime, @PathVariable LocalDateTime arrTime) {
        return flightService.findFlightsInTimeWindow(depTime, arrTime);
    }
}
