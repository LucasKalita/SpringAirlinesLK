package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findAllByFlightDepartureAirport(Airport depAirport);
    public List<Ticket> findAllByFlightArrivalAirport(Airport arrAirport);
    public List<Ticket> findAllByFlightFlightNumberAndUsername(String flightNumber, String username);
    public List<Ticket> findAllByFlightFlightNumber(String flightNumber);
    public Optional<Ticket> findByTicketNumber(String ticketNumber);

}