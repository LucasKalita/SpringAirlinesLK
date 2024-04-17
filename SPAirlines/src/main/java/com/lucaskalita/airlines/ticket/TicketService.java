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
        log.trace("Searching for com.lucaskalita.airlines.ticket with id: {}", id);
        return ticketRepository.findById(id).map(ticket -> {
            log.trace("Found com.lucaskalita.airlines.ticket with this id:{}",ticket);
            return ticketMapper.fromEntityToDto(ticket);
        }).orElseThrow(() -> new WrongTicketIDException("No com.lucaskalita.airlines.ticket with this id: " + id));

    }
    public List<Ticket> findAllUserTickets (User user){
        log.trace("Searching for all user's tickets");
        return ticketRepository.findAll()
                .stream()
                .filter(n->n.getUser().equals(user))
                .collect(Collectors.toList());
    }
    public List<Ticket> findAllTicketsForFlightsByDepartureAirport(Airport airport){
        log.trace("Filtering tickets by {} com.lucaskalita.airlines.airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .collect(Collectors.toList());
    }
    public List<Ticket>findAllTicketsForFlightsByArrivalAirport(Airport airport){
        log.trace("Filtering tickets by {} com.lucaskalita.airlines.airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .collect(Collectors.toList());
    }
    public Optional<Ticket> findTicketByFlightNumber(String flightNumber, User user) {
        log.trace("Searching for com.lucaskalita.airlines.ticket for com.lucaskalita.airlines.flight {} and user {}", flightNumber, user.getUsername());
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .filter(ticket -> ticket.getFlight().getFlightNumber().equals(flightNumber))
                .findFirst();
    }
}
