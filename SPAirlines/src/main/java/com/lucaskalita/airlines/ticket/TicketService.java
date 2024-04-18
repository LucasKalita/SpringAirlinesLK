package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.exceptions.WrongTicketIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lucaskalita.airlines.users.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketMapper ticketMapper;
    public TicketDTO findTicketByID (Long id){
        log.trace("Searching for ticket with id: {}", id);
        return ticketRepository.findById(id).map(ticket -> {
            log.trace("Found ticket with this id:{}",ticket);
            return ticketMapper.fromEntityToDto(ticket);
        }).orElseThrow(() -> new WrongTicketIDException("No ticket with this id: " + id));

    }
    public List<TicketDTO> findAllUserTickets (User user){
        log.trace("Searching for all user's tickets");
        return ticketRepository.findAll()
                .stream()
                .filter(n->n.getUser().equals(user))
                .map(ticketMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<TicketDTO> findAllTicketsForFlightsByDepartureAirport(Airport airport){
        log.trace("Filtering tickets by {} airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .map(ticketMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<TicketDTO>findAllTicketsForFlightsByArrivalAirport(Airport airport){
        log.trace("Filtering tickets by {} airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .map(ticketMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public Optional<TicketDTO> findTicketByFlightNumber(String flightNumber, User user) {
        log.trace("Searching for ticket for flight {} and user {}", flightNumber, user.getUsername());
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .filter(ticket -> ticket.getFlight().getFlightNumber().equals(flightNumber))
                .map(ticketMapper::fromEntityToDto)
                .findFirst();
    }
}
