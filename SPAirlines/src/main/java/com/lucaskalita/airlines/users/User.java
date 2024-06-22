package com.lucaskalita.airlines.users;


import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.messages.Message;
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
@Data
@ToString
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
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Ticket> activeTickets;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Ticket> archiveTickets;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST)
    private Set<Message> outbox;
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.PERSIST)
    private Set<Message> inbox;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getAddress() != null ? !getAddress().equals(user.getAddress()) : user.getAddress() != null) return false;
        if (getDateOfBirth() != null ? !getDateOfBirth().equals(user.getDateOfBirth()) : user.getDateOfBirth() != null)
            return false;
        if (getSocialSecurityNumber() != null ? !getSocialSecurityNumber().equals(user.getSocialSecurityNumber()) : user.getSocialSecurityNumber() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getAccountType() == user.getAccountType();
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        result = 31 * result + (getSocialSecurityNumber() != null ? getSocialSecurityNumber().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAccountType() != null ? getAccountType().hashCode() : 0);
        return result;
    }
}