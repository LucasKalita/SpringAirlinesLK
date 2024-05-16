package com.lucaskalita.airlines.seatReservation;

import lombok.Builder;

@Builder
public record SeatDTO(
        String seatNumber,
        boolean isReserved,
        boolean isPremium

) {
}
