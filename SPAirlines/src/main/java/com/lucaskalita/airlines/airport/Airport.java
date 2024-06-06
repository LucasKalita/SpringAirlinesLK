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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(unique = true, columnDefinition = "VARCHAR(3)")
    private String airportCode;
    private String city;
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;

        if (getCountry() != airport.getCountry()) return false;
        if (!getAirportCode().equals(airport.getAirportCode())) return false;
        return getCity().equals(airport.getCity());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getAirportCode().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }
}
