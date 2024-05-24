package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
@Builder
public record FlightDTO(

         String flightNumber,
         Airport departureAirport,
         Airport arrivalAirport,
         LocalDateTime departureTime,
         LocalDateTime arrivalTime,
         Duration flightTime,
         Long planeID
) {}
