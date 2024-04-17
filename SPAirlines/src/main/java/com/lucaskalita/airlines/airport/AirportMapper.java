package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.Mapper;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper implements Mapper<Airport, AirportDTO> {
    @Override
    public AirportDTO fromEntityToDto(Airport entity) {

        return AirportDTO.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .country(entity.getCountry())
                .airportCode(entity.getAirportCode())
                .build();
    }

    @Override
    public Airport fromDtoToEntity(AirportDTO dto) {

        return Airport.builder()
                .id(dto.id())
                .country(dto.country())
                .airportCode(dto.airportCode())
                .build();
    }
}
