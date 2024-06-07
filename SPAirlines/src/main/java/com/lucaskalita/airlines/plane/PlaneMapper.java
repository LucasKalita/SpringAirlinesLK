package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.Aircraft;
import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper implements Mapper<Plane, PlaneDTO> {
    @Override
    public PlaneDTO fromEntityToDto(Plane entity) {
        return PlaneDTO.builder()
                .planeModel(Aircraft.getPlaneModel(entity.getPlaneBrand(), entity.getPlaneModel()))
                .planeSerialNumber(entity.getPlaneSerialNumber())
                .planeStatus(entity.getPlaneStatus())
                .build();
    }

    @Override
    public Plane fromDtoToEntity(PlaneDTO dto) {

        return Plane.builder()
                .planeModel(dto.planeModel().getPlaneModel())
                .planeBrand(dto.planeModel().getBrand())
                .premiumSeats(dto.planeModel().getPremiumSeats())
                .regularSeats(dto.planeModel().getRegularSeats())
                .totalSeats(dto.planeModel().getPremiumSeats() + dto.planeModel().getRegularSeats())
                .planeSerialNumber(dto.planeSerialNumber())
                .planeStatus(dto.planeStatus())
                .build();
    }
}
