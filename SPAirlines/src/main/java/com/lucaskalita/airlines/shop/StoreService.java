package com.lucaskalita.airlines.shop;

import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.ticket.TicketMapper;
import com.lucaskalita.airlines.ticket.TicketRepository;
import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.users.UserRepository;
import com.lucaskalita.airlines.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class StoreService {
    private UserRepository userRepository;
    private UserService userService;
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;

    @Transactional
    public void buyTicket(TicketDTO ticketDTO, String username) {
        log.trace(" buying ticket for {}", ticketDTO.price());
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresentOrElse(
                user -> {
                    log.trace(" buying ticket for {}", ticketDTO.price());
                    userService.subtractMoney(user, ticketDTO.price());
                    Ticket ticket = ticketMapper.fromDtoToEntity(ticketDTO);
                    addTicketToSet(ticket, user);
                },
                () -> {
                    throw new ObjectNotFoundException("Object by this parameter  (" + username + ") not found");
                });
    }
    @Transactional
    public void refundTicket(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(() -> new ObjectNotFoundException("No ticket found with number: " + ticketNumber));
        User ticketUser = ticket.getUser();
        log.trace("refunding ticket for {}", ticketUser.getUsername());
        removeTicketFromSet(ticket, ticketUser);
        ticketUser.setAccountBalance(ticketUser.getAccountBalance().add(ticket.getPrice()));

        userRepository.save(ticketUser);
    }
    private void removeTicketFromSet(Ticket ticket, User user){
        Set<Ticket> modifiableSet = user.getUserListOfActiveTicketsIds();
        modifiableSet.remove(ticket);
        user.setUserListOfActiveTicketsIds(modifiableSet);
    }
    private void addTicketToSet(Ticket ticket, User user){
        Set<Ticket> modifiableSet = user.getUserListOfActiveTicketsIds();
        modifiableSet.add(ticket);
        user.setUserListOfActiveTicketsIds(modifiableSet);
    }



}