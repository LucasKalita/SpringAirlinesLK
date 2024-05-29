package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.OneToMany;
import lombok.Builder;

import java.util.List;

@Builder
public record AirportDTO(

        Country country,
        String city,
        String airportCode
) {
}
