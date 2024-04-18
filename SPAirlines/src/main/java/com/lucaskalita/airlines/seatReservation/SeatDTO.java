package com.lucaskalita.airlines.seatReservation;

import lombok.Builder;

@Builder
public record SeatDTO(
        Long id,
        boolean isReserved,
        boolean isPremium

) {
}
