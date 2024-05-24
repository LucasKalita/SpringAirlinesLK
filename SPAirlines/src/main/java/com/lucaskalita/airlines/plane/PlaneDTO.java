package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneModel;
import lombok.Builder;

@Builder
public record PlaneDTO(

        PlaneModel planeModel,
        int regularSeatsAmount,
        int premiumSeatsAmount,
        int totalSeatsAmount,
        String flightNumber
) {
}
