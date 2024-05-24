package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.FlightDTO;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TicketDTO(

        String seatNumber,
        FlightDTO flightDTO,
        String username,
        String name,
        String surname,
        BigDecimal price,
        boolean isPremium,
        String ticketNumber

) {
}
