package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements Mapper <Flight, FlightDTO> {
    @Override
    public FlightDTO fromEntityToDto(Flight entity) {
    return FlightDTO.builder()
            .flightNumber(entity.getFlightNumber())
            .departureAirport(entity.getDepartureAirport())
            .arrivalAirport(entity.getArrivalAirport())
            .departureTime(entity.getDepartureTime())
            .arrivalTime(entity.getArrivalTime())
            .flightTime(entity.getFlightTime())
            .planeID(entity.getPlaneId())
            .build();
    }

    @Override
    public Flight fromDtoToEntity(FlightDTO dto) {
        return Flight.builder()
                .flightNumber(dto.flightNumber())
                .departureAirport(dto.departureAirport())
                .arrivalAirport(dto.arrivalAirport())
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .flightTime(dto.flightTime())
                .planeId(dto.planeID())
                .build();
    }
}
