package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findAllByFlightDepartureAirport(Airport depAirport);

    public List<Ticket> findAllByFlightArrivalAirport(Airport arrAirport);
    public List<Ticket> findAllByFlightFlightNumberAndUser(String flightNumber, User user);
    public List<Ticket> findAllByFlightFlightNumber(String flightNumber);

}