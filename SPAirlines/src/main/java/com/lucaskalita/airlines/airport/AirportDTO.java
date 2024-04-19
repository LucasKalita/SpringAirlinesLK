package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.address.Country;
import lombok.Builder;

@Builder
public record AirportDTO(
        Long id,
        Country country,
        String airportCode
) {
}
