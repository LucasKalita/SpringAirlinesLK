package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.exceptions.WrongUserIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.lucaskalita.airlines.users.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;

   private final TicketMapper ticketMapper;

    public TicketDTO findTicketByID(Long id) {
        log.trace("Searching for ticket with id: {}", id);
        return ticketRepository.findById(id).map(ticket -> {
            log.trace("Found ticket with this id:{}", ticket);
            return ticketMapper.fromEntityToDto(ticket);
        }).orElseThrow(() -> new WrongUserIDException("No ticket with this id: " + id));

    }

    public List<TicketDTO> findAllTicketsForFlightsByDepartureAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findAllByFlightDepartureAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findAllTicketsForFlightsByArrivalAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findAllByFlightArrivalAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findUserTicketsByFlightNumber(String flightNumber, User user) {
        log.trace("Searching for ticket for flight {}", flightNumber);
        return ticketRepository.findAllByFlightFlightNumberAndUser(flightNumber, user)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findTicketByFlightNumber(String flightNumber) {
        log.trace("Searching for ticket for flight {}", flightNumber);
        return ticketRepository.findAllByFlightFlightNumber(flightNumber)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }
}