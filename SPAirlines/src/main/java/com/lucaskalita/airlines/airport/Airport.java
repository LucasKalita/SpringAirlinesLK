package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    private Long id;
    @Enumerated
    private Country country;
    @Column(unique = true, columnDefinition = "VARCHAR(3)")
    private String airportCode;
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;
}
