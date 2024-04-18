package com.lucaskalita.airlines.airport;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    Long id;
    String country;
    @Column(unique = true)
    String airportCode;

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
                ", airportCode='" + airportCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;

        if (!getCountry().equals(airport.getCountry())) return false;
        return getAirportCode().equals(airport.getAirportCode());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getAirportCode().hashCode();
        return result;
    }


}
