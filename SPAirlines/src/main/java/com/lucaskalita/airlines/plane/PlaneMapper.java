package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.utilities.Mapper;

public class PlaneMapper implements Mapper<Plane, PlaneDTO> {
    @Override
    public PlaneDTO fromEntityToDto(Plane entity) {
        return PlaneDTO.builder()
                .id(entity.getId())
                .listOfPremiumSeats(entity.getListOfPremiumSeats())
                .listOfRegularSeats(entity.getListOfRegularSeats())
                .planeBrand(entity.planeBrand)
                .planeModel(entity.planeModel)
                .build();
    }

    @Override
    public Plane fromDtoToEntity(PlaneDTO dto) {
        return Plane.builder()
                .id(dto.id())
                .listOfPremiumSeats(dto.listOfPremiumSeats())
                .listOfRegularSeats(dto.listOfRegularSeats())
                .planeBrand(dto.planeBrand())
                .planeModel(dto.planeModel())
                .build();
    }
}
