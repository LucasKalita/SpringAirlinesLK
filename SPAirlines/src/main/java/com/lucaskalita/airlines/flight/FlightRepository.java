package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByDepartureAirport(Airport depAirport);

    List<Flight> findAllByArrivalAirport(Airport arrAirport);
    Optional<Flight> findByPlaneId(Long PlaneId);
    List<Flight> findAllByDepartureTimeBefore(LocalDateTime date);

    List<Flight> findAllByDepartureTimeAfter(LocalDateTime date);

    List<Flight> findAllByDepartureTimeBetween(LocalDateTime date, LocalDateTime date2);

    List<Flight> findAllByArrivalTimeBefore(LocalDateTime date);

    List<Flight> findAllByArrivalTimeAfter(LocalDateTime date);

    List<Flight> findAllByArrivalTimeBetween(LocalDateTime date, LocalDateTime date2);

    List<Flight> findAllByDepartureAirportAndArrivalAirport(Airport DepartureAirport, Airport ArrivalAirport);

}
