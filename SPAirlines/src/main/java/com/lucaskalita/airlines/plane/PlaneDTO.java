package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import com.lucaskalita.airlines.seatReservation.Seat;
import lombok.Builder;

import java.util.List;
@Builder
public record PlaneDTO(

        PlaneBrand planeBrand,
        PlaneModel planeModel,
        int totalSeatsSize) {
}
