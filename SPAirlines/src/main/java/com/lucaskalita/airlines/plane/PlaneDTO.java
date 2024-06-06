package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.Aircraft;
import lombok.Builder;

@Builder
public record PlaneDTO(

        Aircraft planeModel,
        String flightNumber
        //TODO zmienic z flightnuber na planeNumber
) {
}
