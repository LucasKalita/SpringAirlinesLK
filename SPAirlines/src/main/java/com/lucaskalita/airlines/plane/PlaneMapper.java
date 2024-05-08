package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper implements Mapper<Plane, PlaneDTO> {
    @Override
    public PlaneDTO fromEntityToDto(Plane entity) {
        return PlaneDTO.builder()

                .planeModel(entity.getPlaneModel())
                .listOfRegularSeats(entity.getListOfRegularSeats())
                .listOfPremiumSeats(entity.getListOfPremiumSeats())
                .build();
    }

    @Override
    public Plane fromDtoToEntity(PlaneDTO dto) {

        return Plane.builder()

                .planeModel(dto.planeModel())
                .listOfPremiumSeats(dto.listOfPremiumSeats())
                .listOfRegularSeats(dto.listOfRegularSeats())
                .build();
    }
}
