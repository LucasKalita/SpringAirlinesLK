package com.lucaskalita.airlines.plane;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String flightNumber;
     private String planeBrand;
     private String planeModel;
     private int totalSeats;
     private int regularSeats;
     private int premiumSeats;

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", planeBrand='" + planeBrand + '\'' +
                ", planeModel='" + planeModel + '\'' +
                ", totalSeatsAmount=" + totalSeats +
                ", regularSeatsAmount=" + regularSeats +
                ", premiumSeatsAmount=" + premiumSeats +
                '}';
    }
}
