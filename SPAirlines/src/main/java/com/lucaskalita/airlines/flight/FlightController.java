package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        flightService.createNewFlight(flightDTO);
        return flightDTO;
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

    @GetMapping("/{planeId}")
    @ResponseStatus(HttpStatus.FOUND)
    public FlightDTO findByPlaneId(@PathVariable Long planeId) {
        return flightService.findFlightByPlaneId(planeId);
    }

    @GetMapping("/seats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String checkForEmptySeats(@PathVariable Long id) {
        return flightService.checkForEmptySeats(id);
    }

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

    @GetMapping("/timeBetweenArrival/{arrTime1}-{arrTime2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenArrival(@PathVariable LocalDateTime arrTime1, @PathVariable LocalDateTime arrTime2) {
        return flightService.findFlightsBetweenArrivalDates(arrTime1, arrTime2);
    }

    @GetMapping("/timeBetweenArrival/{depTime1}-{depTime2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenDeparture(@PathVariable LocalDateTime depTime1, @PathVariable LocalDateTime depTime2) {
        return flightService.findFlightsByDepartureDateBetween(depTime1, depTime2);
    }

    @GetMapping("/arrivalAirport/{airport}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsToAirport(@PathVariable Airport airport) {
        return flightService.findFlightsByArrivalAirport(airport);
    }

    @GetMapping("/departureAirport/{airport}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromAirport(@PathVariable Airport airport) {
        return flightService.findFlightsByDepartureAirport(airport);
    }

    @GetMapping("/betweenAirports/{airport1}-{airport2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromAirport1ToAirport2(@PathVariable Airport airport1, @PathVariable Airport airport2) {
        return flightService.findFlightsBetweenAirports(airport1, airport2);
    }
}


