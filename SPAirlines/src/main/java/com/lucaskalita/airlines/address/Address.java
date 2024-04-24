package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private Country country;
    private String state;
    private String city;
    private String postalCode;
    private String street;
    private String parcelNumber;
    @OneToMany(mappedBy = "address")
    private List<User> users;
}
