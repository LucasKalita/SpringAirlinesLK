package com.lucaskalita.airlines.seatReservation;

import lombok.Builder;

@Builder
public record SeatDTO(

        boolean isReserved,
        boolean isPremium

) {
}
