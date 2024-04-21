package com.lucaskalita.airlines.users;


import com.lucaskalita.airlines.address.Address;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.HashSet;

@Entity
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    @ManyToOne
    private Address address;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String socialSecurityNumber;
    private String password;
    @Column(unique = true)
    private String email;
    private BigDecimal accountBalance;
    @ManyToOne
    private HashSet<Long> userListOfActiveTicketsIds;
    @ManyToOne
    private HashSet<Long> userListOfArchiveTicketsIds;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

}