package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findAllByCountry(Country country);
    Optional<Airport>findByCity(String city);
    Optional <Airport> findByAirportCode(String airportCode);
}
