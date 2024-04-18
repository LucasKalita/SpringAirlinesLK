package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements Mapper<Ticket, TicketDTO> {
    @Override
    public TicketDTO fromEntityToDto(Ticket entity) {
        return null;
    }

    @Override
    public Ticket fromDtoToEntity(TicketDTO dto) {
        return null;
    }
}
