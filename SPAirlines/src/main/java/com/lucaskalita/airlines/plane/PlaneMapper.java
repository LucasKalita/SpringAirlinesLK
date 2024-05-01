package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper implements Mapper<Plane, PlaneDTO> {
    @Override
    public PlaneDTO fromEntityToDto(Plane entity) {
        return PlaneDTO.builder()

                .planeBrand(entity.getPlaneBrand())
                .planeModel(entity.getPlaneModel())
                .totalSeatsSize(entity.getTotalSeatsSize())
                .build();
    }

    @Override
    public Plane fromDtoToEntity(PlaneDTO dto) {
        return Plane.builder()

                .planeBrand(dto.planeBrand())
                .planeModel(dto.planeModel())
                .totalSeatsSize(dto.totalSeatsSize())
                .build();
    }
}
