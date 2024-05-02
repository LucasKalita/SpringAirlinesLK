package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.AddressDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record UserDTO(

        String username,
        String name,
        String surname,
        AddressDTO addressDTO,
        LocalDate dateOfBirth,
        String socialSecurityNumber,
        String email,
        AccountType accountType

) {

}
