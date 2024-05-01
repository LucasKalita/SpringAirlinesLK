package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.seatReservation.Seat;
import com.lucaskalita.airlines.users.User;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TicketDTO(

        String seatNumber,
        Seat seat,
        FlightDTO flightDTO,
        User user,
        String name,
        String surname,
        BigDecimal price

) {
}
