package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.Address;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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
        HashSet<Long> userListOfActiveTicketsIds,
        HashSet<Long> userListOfArchiveTicketsIds,
        AccountType accountType
) {

}
