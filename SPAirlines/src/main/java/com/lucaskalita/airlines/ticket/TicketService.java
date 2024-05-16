package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import com.lucaskalita.airlines.users.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;

   private final TicketMapper ticketMapper;

   public Long createTicket (TicketDTO ticketDTO){
        log.trace("Creating new Ticket");
        Ticket ticket = ticketMapper.fromDtoToEntity(ticketDTO);
        ticketRepository.save(ticket);
        return ticket.getId();
   }
   public void deleteTicketById(Long id){
       log.trace("Deleting ticket by id {}", id);
       ticketRepository.deleteById(id);
   }
    public TicketDTO findTicketByID(Long id) {
        log.trace("Searching for ticket with id: {}", id);
        return ticketRepository.findById(id)
                .map(ticketMapper::fromEntityToDto)
                .orElseThrow(()->new WrongObjectIdException("No ticket with this id: "+ id));
    }
    public void changeTicketSeat(Long id, TicketDTO ticketDTO){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        ticketOptional.ifPresentOrElse(
                ticket -> {
                    log.trace("Updating ticket");
                    ticket.setSeatNumber(ticketDTO.seatNumber());
                    ticketRepository.save(ticket);
                },
                () -> {
                    throw new WrongObjectIdException("No object with this ID: " + id);
                }
        );
    }
    public void changeTicketUser(Long id, TicketDTO ticketDTO){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        ticketOptional.ifPresentOrElse(
                ticket -> {
                    log.trace("Updating ticket");
                    ticket.setUsername(ticketDTO.seatNumber());
                    ticket.setName(ticketDTO.name());
                    ticket.setSurname(ticketDTO.surname());
                    ticketRepository.save(ticket);
                },
                () -> {
                    throw new WrongObjectIdException("No object with this ID: " + id);
                }
        );
    }
    public void changeTicketPrice(Long id, TicketDTO ticketDTO){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        ticketOptional.ifPresentOrElse(
                ticket -> {
                    log.trace("Updating ticket");
                    ticket.setPrice(ticketDTO.price());
                    ticketRepository.save(ticket);
                },
                () -> {
                    throw new WrongObjectIdException("No object with this ID: " + id);
                }
        );
    }

    public List<TicketDTO> findAllTicketsForFlightsByDepartureAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findAllByFlightDepartureAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findAllTicketsByArrivalAirport(Airport airport) {
        log.trace("Filtering tickets by {} airport", airport);
        return ticketRepository.findAllByFlightArrivalAirport(airport)
                .stream()
                .map(ticketMapper::fromEntityToDto)
                .toList();
    }

    public List<TicketDTO> findUserTicketsByFlightNumber(String flightNumber, String username) {
        log.trace("Searching for ticket for flight {}", flightNumber);
        return ticketRepository.findAllByFlightFlightNumberAndUser(flightNumber, username)
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