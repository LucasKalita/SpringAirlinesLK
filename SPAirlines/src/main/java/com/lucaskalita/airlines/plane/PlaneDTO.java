package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneModel;
import com.lucaskalita.airlines.seatReservation.Seat;
import lombok.Builder;

import java.util.List;
@Builder
public record PlaneDTO(

         PlaneModel planeModel,
         List<Seat> listOfRegularSeats,
         List<Seat> listOfPremiumSeats
        ) {
}
