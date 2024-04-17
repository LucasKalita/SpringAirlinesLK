package com.lucaskalita.airlines.airport;

import lombok.Builder;

@Builder
public record AirportDTO(
        Long id,
        String country,
        String airportCode
) {
}
