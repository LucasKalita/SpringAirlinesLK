package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.seatReservation.Seat;
import com.lucaskalita.airlines.users.User;
import lombok.Builder;

@Builder
public record TicketDTO(
        Long id,
        String seatNumber,
        Seat seat,
        Flight flight,
        User user,
        String name,
        String surname
) {
}
