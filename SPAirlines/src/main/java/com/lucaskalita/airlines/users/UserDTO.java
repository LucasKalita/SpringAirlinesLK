package com.lucaskalita.airlines.users;

import lombok.Builder;
import com.lucaskalita.airlines.ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
@Builder
public record UserDTO(
        Long id,
        String username,
        String name,
        String surname,
        String Address,
        LocalDateTime dateOfBirth,
        String PESEL,
        String password,
        String email,
        BigDecimal accountBalance,
        HashSet<Long> userListOfActiveTicketsIds,
        HashSet<Long> userListOfArchiveTicketsIds,
        AccountType accountType
) {

}
