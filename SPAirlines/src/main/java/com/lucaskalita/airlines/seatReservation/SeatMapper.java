package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper implements Mapper<Seat, SeatDTO> {
    @Override
    public SeatDTO fromEntityToDto(Seat entity) {
        return SeatDTO.builder()
                .isReserved(entity.isReserved())
                .isPremium(entity.isPremium())
                .seatNumber(entity.getSeatNumber())
                .build();
    }

    @Override
    public Seat fromDtoToEntity(SeatDTO dto) {
        return Seat.builder()
                .isPremium(dto.isPremium())
                .isReserved(dto.isPremium())
                .seatNumber(dto.seatNumber())
                .build();
    }
}
