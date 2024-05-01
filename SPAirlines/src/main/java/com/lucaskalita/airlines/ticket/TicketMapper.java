package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class TicketMapper implements Mapper<Ticket, TicketDTO> {
    private final FlightMapper flightMapper;
    @Override
    public TicketDTO fromEntityToDto(Ticket entity) {

        return TicketDTO.builder()

                .seatNumber(entity.getSeatNumber())
                .seat(entity.getSeat())
                .user(entity.getUser())
                .flightDTO(flightMapper.fromEntityToDto(entity.getFlight()))
                .name(entity.getName())
                .surname(entity.getSurname())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Ticket fromDtoToEntity(TicketDTO dto) {

        return Ticket.builder()
                .seatNumber(dto.seatNumber())
                .seat(dto.seat())
                .flight(flightMapper.fromDtoToEntity(dto.flightDTO()))
                .user(dto.user())
                .name(dto.name())
                .surname(dto.surname())
                .price(dto.price())
                .build();
    }
}
