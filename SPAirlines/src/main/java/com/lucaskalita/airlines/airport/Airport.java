package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    Long id;
    Country country;
    @Column(unique = true)
    String airportCode;


}
