package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
     List<Ticket> findAllByFlightDepartureAirport(Airport depAirport);
     List<Ticket> findAllByFlightArrivalAirport(Airport arrAirport);
     List<Ticket> findAllByFlightFlightNumberAndUsername(String flightNumber, String username);
     List<Ticket> findAllByFlightFlightNumber(String flightNumber);
     Optional<Ticket> findByTicketNumber(String ticketNumber);

}