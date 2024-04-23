package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.plane.Plane;
import com.lucaskalita.airlines.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findTicketsByDepartureAirport(Airport depAirport);

    public List<Ticket> findTicketsByArrivalAirport(Airport arrAirport);
    public List<Ticket> findTicketByFlightNumberAndUser(String flightNumber, User user);
    public List<Ticket> findTicketByFlightNumber(String flightNumber);

}