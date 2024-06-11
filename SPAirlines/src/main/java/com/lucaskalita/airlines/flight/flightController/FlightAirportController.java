package com.lucaskalita.airlines.flight.flightController;
import com.lucaskalita.airlines.airport.AirportDTO;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.flightService.FlightAirportService;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightAirportController {

    private final FlightAirportService flightService;


    @GetMapping("/arrivalAirport")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsToAirport(@RequestBody AirportDTO airport) {
        return flightService.findFlightsByArrivalAirport(airport);
    }

    @GetMapping("/departureAirport")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromAirport(@RequestBody AirportDTO airport) {
        return flightService.findFlightsByDepartureAirport(airport);
    }

    @GetMapping("/betweenAirports/{airport1}-{airport2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromAirport1ToAirport2(@PathVariable AirportDTO airport1, @PathVariable AirportDTO airport2) {
        return flightService.findFlightsBetweenAirports(airport1, airport2);
    }
    @GetMapping("/betweenCountries/{country1}-{country2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenCountries(@PathVariable Country country1, @PathVariable Country country2){
        return flightService.findFlightsBetweenCountries(country1, country2);
    }
    @GetMapping("/betweenCities/{city1}-{city2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenCities(@PathVariable String city1, @PathVariable String city2){
        return flightService.findFlightsBetweenCities(city1, city2);
    }
}
