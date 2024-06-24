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
@RequestMapping("/flights")
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

    @GetMapping("/betweenCodes/{airportCode1}_{airportCode2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenAirportsByCode(@PathVariable String airportCode1, @PathVariable String airportCode2) {
        return flightService.findFlightsBetweenCodes(airportCode1, airportCode2);
    }
    @GetMapping("/betweenCountries/{country1}_{country2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenCountries(@PathVariable Country country1, @PathVariable Country country2){
        return flightService.findFlightsBetweenCountries(country1, country2);
    }
    @GetMapping("/betweenCities/{city1}_{city2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsBetweenCities(@PathVariable String city1, @PathVariable String city2){
        return flightService.findFlightsBetweenCities(city1, city2);
    }
    @GetMapping("/cityToCountry/{city}_{country}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromCityTOCountry(String city, Country country){
        return flightService.findFlightsFromCityToCountry(city, country);
    }
    @GetMapping("/countryToCity/{country}_{city}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsFromCountryToCity(String city, Country country){
        return flightService.findFlightsCountryToCity(country, city);
    }
    @GetMapping("/AirportToAirport")
    @ResponseStatus(HttpStatus.FOUND)
    public List<FlightDTO> findFlightsAirportToAirport(@RequestBody AirportDTO airportDTO,@RequestBody AirportDTO airportDTO2){
        return flightService.findFlightsBetweenAirports(airportDTO, airportDTO2);
    }

}
