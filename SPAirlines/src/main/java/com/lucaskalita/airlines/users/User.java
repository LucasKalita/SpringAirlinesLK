package com.lucaskalita.airlines.users;


import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.ticket.Ticket;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "Account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String socialSecurityNumber;
    private String password;
    @Column(unique = true)
    private String email;
    private BigDecimal accountBalance;
    @OneToMany(mappedBy = "user")
    private Set<Ticket> userListOfActiveTicketsIds;
    @OneToMany(mappedBy = "user")
    private Set<Ticket> userListOfArchiveTicketsIds;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;



    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }


    public String getEmail() {
        return email;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public Set<Ticket> getUserListOfActiveTicketsIds() {
        return Set.copyOf(userListOfActiveTicketsIds);
    }

    public Set<Ticket> getUserListOfArchiveTicketsIds() {
        return Set.copyOf(userListOfArchiveTicketsIds);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", email='" + email + '\'' +
                ", userListOfActiveTicketsIds=" + userListOfActiveTicketsIds.size() +
                ", userListOfArchiveTicketsIds=" + userListOfArchiveTicketsIds.size() +
                ", accountType=" + accountType +
                '}';
    }
}