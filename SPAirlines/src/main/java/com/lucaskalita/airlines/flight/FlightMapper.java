package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.AirportMapper;
import com.lucaskalita.airlines.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightMapper implements Mapper <Flight, FlightDTO> {
    private final AirportMapper airportMapper;
    @Override
    public FlightDTO fromEntityToDto(Flight entity) {
    return FlightDTO.builder()
            .flightNumber(entity.getFlightNumber())
            .departureAirport(airportMapper.fromEntityToDto(entity.getDepartureAirport()))
            .arrivalAirport(airportMapper.fromEntityToDto(entity.getArrivalAirport()))
            .departureTime(entity.getDepartureTime())
            .arrivalTime(entity.getArrivalTime())
            .planeID(entity.getPlaneId())
            .build();
    }

    @Override
    public Flight fromDtoToEntity(FlightDTO dto) {
        return Flight.builder()
                .flightNumber(dto.flightNumber())
                .departureAirport(airportMapper.fromDtoToEntity(dto.departureAirport()))
                .arrivalAirport(airportMapper.fromDtoToEntity(dto.arrivalAirport()))
                .departureTime(dto.departureTime())
                .arrivalTime(dto.arrivalTime())
                .planeId(dto.planeID())
                .build();
    }
}
