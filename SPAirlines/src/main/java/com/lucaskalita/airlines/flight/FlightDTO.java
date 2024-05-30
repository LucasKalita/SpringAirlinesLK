package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.airport.AirportDTO;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
@Builder
public record FlightDTO(

         String flightNumber,
         AirportDTO departureAirport,
         AirportDTO arrivalAirport,
         LocalDateTime departureTime,
         LocalDateTime arrivalTime,
         Long planeID
) {}
