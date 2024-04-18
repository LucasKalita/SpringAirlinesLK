package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record FlightDTO(
        Long id,
         String flightNumber,
         Airport departureAirport,
         Airport arrivalAirport,
         LocalDateTime departureTime,
         LocalDateTime arrivalTime,
         int availableTickets,
         Long planeID
) {}
