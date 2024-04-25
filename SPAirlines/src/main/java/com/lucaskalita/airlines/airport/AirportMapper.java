package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements Mapper<Airport, AirportDTO> {
    @Override
    public AirportDTO fromEntityToDto(Airport entity) {

        return AirportDTO.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .airportCode(entity.getAirportCode())
                .arrivalFlights(entity.getArrivalFlights())
                .departureFlights(entity.getDepartureFlights())
                .build();
    }

    @Override
    public Airport fromDtoToEntity(AirportDTO dto) {

        return Airport.builder()
                .id(dto.id())
                .country(dto.country())
                .airportCode(dto.airportCode())
                .departureFlights(dto.departureFlights())
                .arrivalFlights(dto.arrivalFlights())
                .build();
    }
}
