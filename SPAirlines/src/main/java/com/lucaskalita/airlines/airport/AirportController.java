package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable Long id) {
        AirportDTO airportDTO = airportService.getAirportById(id);
        return ResponseEntity.ok(airportDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AirportDTO> addAirport(@RequestBody AirportDTO airportDTO) {
        AirportDTO savedAirport = airportService.addAirport(airportDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<AirportDTO>> findAllAirportsInCountry(@PathVariable Country country) {
        List<AirportDTO> airports = airportService.findAllAirportsInCountry(country);
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<AirportDTO> findAirportByAirportCode(@PathVariable String code) {
        AirportDTO airportDTO = airportService.findAirportByAirportCode(code);
        return ResponseEntity.ok(airportDTO);
    }
}