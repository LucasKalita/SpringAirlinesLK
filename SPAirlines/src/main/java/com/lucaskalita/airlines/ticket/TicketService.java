package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.exceptions.WrongUserIDException;
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

    public TicketDTO findTicketByID(Long id) {
        log.trace("Searching for ticket with id: {}", id);
        return ticketRepository.findById(id).map(ticket -> {
            log.trace("Found ticket with this id:{}", ticket);
            return ticketMapper.fromEntityToDto(ticket);
        }).orElseThrow(() -> new WrongUserIDException("No ticket with this id: " + id));

    }

    public List<TicketDTO> findAllTicketsForFlightsByDepartureAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findTicketsByDepartureAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findAllTicketsForFlightsByArrivalAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findTicketsByArrivalAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findUserTicketsByFlightNumber(String flightNumber, User user) {
        log.trace("Searching for ticket for flight {}", flightNumber);
        return ticketRepository.findTicketByFlightNumberAndUser(flightNumber, user)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findTicketByFlightNumber(String flightNumber) {
        log.trace("Searching for ticket for flight {}", flightNumber);
        return ticketRepository.findTicketByFlightNumber(flightNumber)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }
}