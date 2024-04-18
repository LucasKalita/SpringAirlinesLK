package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.utilities.Mapper;

public class SeatMapper implements Mapper<Seat, SeatDTO> {
    @Override
    public SeatDTO fromEntityToDto(Seat entity) {
        return SeatDTO.builder()
                .id(entity.getId())
                .isReserved(entity.isReserved())
                .isPremium(entity.isPremium())
                .build();
    }

    @Override
    public Seat fromDtoToEntity(SeatDTO dto) {
        return Seat.builder()
                .id(dto.id())
                .isPremium(dto.isPremium())
                .isReserved(dto.isPremium())
                .build();
    }
}