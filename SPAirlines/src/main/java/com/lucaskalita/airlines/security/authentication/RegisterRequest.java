package com.lucaskalita.airlines.security.authentication;

import com.lucaskalita.airlines.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String email;
    private Address address;
    private String password;
    private String username;
    private String socialSecurityNumber;
    private LocalDate birthDate;
}
