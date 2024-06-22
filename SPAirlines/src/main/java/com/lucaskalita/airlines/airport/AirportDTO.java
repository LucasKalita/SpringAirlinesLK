package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import lombok.Builder;

import java.util.List;

@Builder
public record AirportDTO(

        Country country,
        String city,
        String airportCode
) {
}
