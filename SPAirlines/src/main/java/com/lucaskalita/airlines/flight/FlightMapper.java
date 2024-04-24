package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements Mapper <Flight, FlightDTO> {
    @Override
    public FlightDTO fromEntityToDto(Flight entity) {
    return FlightDTO.builder().id(entity.getId())
            .flightNumber(entity.getFlightNumber())
            .departureAirport(entity.getDepartureAirport())
            .arrivalAirport(entity.getArrivalAirport())
            .departureTime(entity.getDepartureTime())
            .arrivalTime(entity.getArrivalTime())
            .planeID(entity.getPlaneID())
            .availableTickets(entity.getAvailableTickets())
            .build();
    }

    @Override
    public Flight fromDtoToEntity(FlightDTO dto) {
        return Flight.builder()
                .id(dto.id())
                .flightNumber(dto.flightNumber())
                .departureAirport(dto.departureAirport())
                .arrivalAirport(dto.arrivalAirport())
                .departureAirport(dto.departureAirport())
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .planeID(dto.planeID())
                .availableTickets(dto.availableTickets())
                .build();
    }
}
