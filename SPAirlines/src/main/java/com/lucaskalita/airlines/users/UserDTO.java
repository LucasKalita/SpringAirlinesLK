package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.address.AddressDTO;
import com.lucaskalita.airlines.ticket.Ticket;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Set;

@Builder
public record UserDTO(

        String username,
        String name,
        String surname,
        AddressDTO addressDTO,
        LocalDate dateOfBirth,
        String socialSecurityNumber,
        String password,
        String email,
        AccountType accountType
) {

}
