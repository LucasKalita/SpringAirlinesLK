package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter

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
}
