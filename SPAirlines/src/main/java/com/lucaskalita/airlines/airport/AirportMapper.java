package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements Mapper<Airport, AirportDTO> {
    @Override
    public AirportDTO fromEntityToDto(Airport entity) {

        return AirportDTO.builder()
                .country(entity.getCountry())
                .airportCode(entity.getAirportCode())
                .build();
    }

    @Override
    public Airport fromDtoToEntity(AirportDTO dto) {

        return Airport.builder()
                .country(dto.country())
                .airportCode(dto.airportCode())
                .build();
    }
}
