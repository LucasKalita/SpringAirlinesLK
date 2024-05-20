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
                .username(entity.getUsername())
                .flightDTO(flightMapper.fromEntityToDto(entity.getFlight()))
                .name(entity.getName())
                .surname(entity.getSurname())
                .price(entity.getPrice())
                .ticketNumber(entity.getTicketNumber())
                .build();
    }

    @Override
    public Ticket fromDtoToEntity(TicketDTO dto) {

        return Ticket.builder()
                .seatNumber(dto.seatNumber())
                .flight(flightMapper.fromDtoToEntity(dto.flightDTO()))
                .username(dto.username())
                .name(dto.name())
                .surname(dto.surname())
                .price(dto.price())
                .ticketNumber(dto.ticketNumber())
                .build();
    }
}
