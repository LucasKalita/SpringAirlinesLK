package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.utilities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByDepartureAirport(Airport depertureAirport);

    List<Flight> findAllByArrivalAirport(Airport arrivalAirport);

    List<Flight> findAllByDepartureTimeBefore(LocalDateTime departureTime);

    List<Flight> findAllByDepartureTimeAfter(LocalDateTime departureTime);

    List<Flight> findAllByDepartureTimeBetween(LocalDateTime date, LocalDateTime date2);

    List<Flight> findAllByArrivalTimeBefore(LocalDateTime arrivalTime);

    List<Flight> findAllByArrivalTimeAfter(LocalDateTime arrivalTime);

    List<Flight> findAllByArrivalTimeBetween(LocalDateTime date, LocalDateTime date2);

    List<Flight> findAllByDepartureAirportAndArrivalAirport(Airport DepartureAirport, Airport ArrivalAirport);

    List<Flight> findAllByDepartureAirportCountryAndArrivalAirportCountry(Country departureAirportCountry, Country arrivalAirportCountry);

}
