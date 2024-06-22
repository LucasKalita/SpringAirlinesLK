package com.lucaskalita.airlines.shop;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.flight.FlightRepository;
import com.lucaskalita.airlines.globalExceptions.*;
import com.lucaskalita.airlines.plane.PlaneService;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.ticket.TicketMapper;
import com.lucaskalita.airlines.ticket.TicketRepository;
import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.users.UserRepository;
import com.lucaskalita.airlines.users.userService.UserAccountService;
import com.lucaskalita.airlines.users.userService.UserBasicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final UserRepository userRepository;
    private final UserAccountService userAccountService;
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightRepository flightRepository;
    private final PlaneService planeService;

    @Transactional
    public TicketDTO buyTicket(String username, Long flightId, SeatDetailsDTO seatDetailsDTO) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new WrongObjectIdException("No flight by this id: " + flightId));

        User user = userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + username));

        if (checkForEmptySpaceByType(flight, seatDetailsDTO.isPremium)) {
            if (walletCheck(user, seatDetailsDTO.money)) {
                userAccountService.withdrawMoneyFromAccount(seatDetailsDTO.money, username);

                Ticket ticket = assignDetailsToTicket(seatDetailsDTO, flight, user);

                flight.getTicketList().add(ticket);
                flightRepository.save(flight);

                user.getActiveTickets().add(ticket);
                userRepository.save(user);


                return ticketMapper.fromEntityToDto(ticket);
            } else throw new InsufficientFundsException("Not enough funds");

        } else {
            throw new NoEmptySeatsException("No available space for this type");
        }

    }

    public void changeTicketDetails(String ticketNumber, TicketUpdateDTO ticketUpdateDTO) {
        Optional<Ticket> ticketOptional = ticketRepository.findByTicketNumber(ticketNumber);
        ticketOptional.ifPresentOrElse(ticket -> {
            log.trace("Updating ticket");

            if (ticketUpdateDTO.isPremium() != null && ticketUpdateDTO.isPremium() != ticket.isPremium()){
                ticket.setPremium(ticketUpdateDTO.isPremium());
                String number = assignSeatNumber(ticket.getFlight().getTicketList(), ticketUpdateDTO.isPremium());

                if (!checkIfTicketAlreadyExists(number)) {
                    ticket.setSeatNumber(number);
                }else {throw new SeatOccupiedException("Seat occupied");}

                ticket.setTicketNumber(generateUniqueTicketNumber(ticket.getFlight()) + number);
            }
            if(ticketUpdateDTO.name() != null){ticket.setName(ticketUpdateDTO.name());}
            if(ticketUpdateDTO.surname() != null){ticket.setSurname(ticketUpdateDTO.surname());}

            ticketRepository.save(ticket);
        }, () -> {
            throw new ObjectNotFoundException("No object by this parameter: " + ticketNumber);
        });
    }

    @Transactional
    public void refundTicket(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(
                        () -> new ObjectNotFoundException("No ticket found with number: " + ticketNumber));

        User ticketUser = ticket.getUser();
        log.trace("refunding ticket for {}", ticketUser.getUsername());
        removeTicketFromSet(ticket, ticketUser);
        ticketUser.setAccountBalance(ticketUser.getAccountBalance().add(ticket.getPrice()));

        userRepository.save(ticketUser);
    }

    public void upgradeToPremium(String ticketNumber) {
        ticketRepository.findByTicketNumber(ticketNumber).ifPresentOrElse(ticket -> {
            ticket.setPremium(true);
            ticketRepository.save(ticket);
        }, () -> {
            throw new ObjectNotFoundException("No ticket found with the number: " + ticketNumber);
        });
    }

    private boolean checkForEmptySpaceByType(Flight flight, boolean isPremium) {
        int spaceTaken;
        int fullSpace;
        if (isPremium) {
            spaceTaken = flight.getTicketList().stream().filter(Ticket::isPremium).toList().size();
            fullSpace = planeService.findPlaneById(flight.getPlaneId()).planeModel().getPremiumSeats();
        } else {
            spaceTaken = flight.getTicketList().stream().filter(ticket -> !ticket.isPremium()).toList().size();
            fullSpace = planeService.findPlaneById(flight.getPlaneId()).planeModel().getRegularSeats();
        }
        return fullSpace > spaceTaken;
    }

    private boolean walletCheck(User user, BigDecimal money) {
        return user.getAccountBalance().compareTo(money) >= 0;
    }

    private boolean checkIfTicketAlreadyExists(String seatNumber) {
        log.trace("Checking for: " + seatNumber);
        return ticketRepository.findByTicketNumber(seatNumber).isPresent();
    }

    private void removeTicketFromSet(Ticket ticket, User user) {
        Set<Ticket> UserTicketList = user.getActiveTickets();
        UserTicketList.remove(ticket);
        user.setActiveTickets(UserTicketList);
    }

    private String assignSeatNumber(List<Ticket> ticketList, boolean isPremium) {
        long count;

        if (isPremium) {
            count = ticketList.stream().filter(Ticket::isPremium).count();
            return count + 1 + "P";
        } else {
            count = ticketList.stream().filter(ticket -> !ticket.isPremium()).count();
            return count + 1 + "R";
        }
    }

    private Ticket assignDetailsToTicket(SeatDetailsDTO seatDetailsDTO, Flight flight, User user) {
        return Ticket.builder()
                .user(user)
                .name(seatDetailsDTO.name)
                .surname(seatDetailsDTO.surname)
                .flight(flight).isPremium(seatDetailsDTO.isPremium)
                .seatNumber(assignSeatNumber(flight.getTicketList(), seatDetailsDTO.isPremium))
                .ticketNumber(generateUniqueTicketNumber(flight) + assignSeatNumber(flight.getTicketList(), seatDetailsDTO.isPremium))
                .price(seatDetailsDTO.money)
                .build();
    }

    private String generateUniqueTicketNumber(Flight flight) {
        return flight.getDepartureAirport().getAirportCode()
                + flight.getArrivalAirport().getAirportCode()
                + flight.getDepartureTime().getYear()
                + flight.getDepartureTime().getMonth()
                + flight.getDepartureTime().getDayOfMonth();
    }
}