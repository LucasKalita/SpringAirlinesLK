package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.Aircraft;
import lombok.Builder;

@Builder
public record PlaneDTO(

        Aircraft planeModel,
        String planeSerialNumber
        //TODO zmienic z flightnuber na planeNumber
) {
}
