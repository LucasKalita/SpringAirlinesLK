package com.lucaskalita.airlines.users;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import com.lucaskalita.airlines.ticket.Ticket;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String Address;
    private LocalDateTime dateOfBirth;
    private String PESEL;
    private String password;
    private String email;
    private BigDecimal accountBalance;
    private HashSet<Long> userListOfActiveTicketsIds;
    private HashSet<Long> userListOfArchiveTicketsIds;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


}