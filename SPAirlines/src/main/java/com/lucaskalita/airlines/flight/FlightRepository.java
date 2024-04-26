package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.messages.Message;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
//Airport
    public List<Flight> findAllByDepartureAirport(Airport depAirport);
    public List<Flight> findAllByArrivalAirport(Airport arrAirport);
    ///
    public List<Flight> findAllByFlightNumber(String flightNumber);
    ///departure time
    public List<Flight> findAllByDepartureTimeBefore(LocalDateTime date);
    public List<Flight> findAllByDepartureTimeAfter(LocalDateTime date);
    public List<Flight> findAllByDepartureTimeBetween(LocalDateTime date, LocalDateTime date2);
    ///arrival time
    public List<Flight> findAllByArrivalTimeBefore(LocalDateTime date);
    public List<Flight> findAllByArrivalTimeAfter(LocalDateTime date);
    public List<Flight> findAllByArrivalTimeBetween(LocalDateTime date, LocalDateTime date2);
    public List<Flight> findAllByDepartureAirportAndArrivalAirport(Airport DepartureAirport, Airport ArrivalAirport);

}
