package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    public List<Airport> findAllByCountry (Country country);

}
