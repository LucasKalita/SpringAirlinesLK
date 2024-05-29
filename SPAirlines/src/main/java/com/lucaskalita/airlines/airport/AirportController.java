package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AirportDTO getAirportById(@PathVariable Long id) {
        return airportService.getAirportById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addAirport(@RequestBody AirportDTO airportDTO) {
       return airportService.createAirport(airportDTO);
    }

    @GetMapping("/country/{country}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AirportDTO> findAllAirportsInCountry(@PathVariable Country country) {
        return airportService.findAllAirportsInCountry(country);
    }

    @GetMapping("/code/{code}")
    @ResponseStatus(HttpStatus.FOUND)
    public AirportDTO findAirportByAirportCode(@PathVariable String code) {
        return airportService.findAirportByAirportCode(code);
    }
    @GetMapping("/city/{city}")
    @ResponseStatus(HttpStatus.FOUND)
    public AirportDTO findAirportByCity(@PathVariable String city){
        return airportService.findAirportByCity(city);
    }
}