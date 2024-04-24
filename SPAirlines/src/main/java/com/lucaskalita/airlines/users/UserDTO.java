package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.ticket.Ticket;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
public record UserDTO(
        Long id,
        String username,
        String name,
        String surname,
        Address address,
        LocalDate dateOfBirth,
        String socialSecurityNumber,
        String password,
        String email,
        BigDecimal accountBalance,
        Set<Ticket> userListOfActiveTicketsIds,
        Set<Ticket> userListOfArchiveTicketsIds,
        AccountType accountType
) {

}
