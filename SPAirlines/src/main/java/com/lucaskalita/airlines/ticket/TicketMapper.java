package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements Mapper<Ticket, TicketDTO> {
    @Override
    public TicketDTO fromEntityToDto(Ticket entity) {

        return TicketDTO.builder()
                .id(entity.getId())
                .seatNumber(entity.getSeatNumber())
                .seat(entity.getSeat())
                .user(entity.getUser())
                .flight(entity.getFlight())
                .name(entity.getName())
                .surname(entity.getSurname())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Ticket fromDtoToEntity(TicketDTO dto) {

        return Ticket.builder()
                .id(dto.id())
                .seatNumber(dto.seatNumber())
                .seat(dto.seat())
                .flight(dto.flight())
                .user(dto.user())
                .name(dto.name())
                .surname(dto.surname())
                .price(dto.price())
                .build();
    }
}
