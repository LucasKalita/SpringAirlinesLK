package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.Aircraft;
import com.lucaskalita.airlines.plane.enums.PlaneStatus;
import lombok.Builder;

@Builder
public record PlaneDTO(

        Aircraft planeModel,
        String planeSerialNumber,
        PlaneStatus planeStatus

) {
}
