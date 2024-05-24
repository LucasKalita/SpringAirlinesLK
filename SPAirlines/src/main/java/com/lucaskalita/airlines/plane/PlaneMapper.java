package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneModel;
import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper implements Mapper<Plane, PlaneDTO> {
    @Override
    public PlaneDTO fromEntityToDto(Plane entity) {
        return PlaneDTO.builder()

                .planeModel(PlaneModel.getPlaneModel(entity.getPlaneBrand(), entity.getPlaneModel()))
                .premiumSeatsAmount(entity.getPremiumSeatsAmount())
                .regularSeatsAmount(entity.getRegularSeatsAmount())
                .totalSeatsAmount(entity.getTotalSeatsAmount())
                .planeId(entity.getFlightId())
                .build();
    }

    @Override
    public Plane fromDtoToEntity(PlaneDTO dto) {

        return Plane.builder()
                .planeModel(dto.planeModel().getPlaneModel())
                .planeBrand(dto.planeModel().getBrand())
                .premiumSeatsAmount(dto.premiumSeatsAmount())
                .regularSeatsAmount(dto.regularSeatsAmount())
                .totalSeatsAmount(dto.totalSeatsAmount())
                .flightId(dto.planeId())
                .build();
    }
}
