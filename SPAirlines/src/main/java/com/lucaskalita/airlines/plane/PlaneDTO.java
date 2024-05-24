package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.Aircraft;
import lombok.Builder;

@Builder
public record PlaneDTO(

        Aircraft planeModel,
        int regularSeats,
        int premiumSeats,
        int totalSeats,
        String flightNumber
) {
}
