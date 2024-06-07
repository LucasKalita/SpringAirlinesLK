package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.plane.enums.PlaneStatus;
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
     @Column(unique = true)
     private String planeSerialNumber;
     private String planeBrand;
     private String planeModel;
     private int totalSeats;
     private int regularSeats;
     private int premiumSeats;
     @Enumerated(EnumType.STRING)
     private PlaneStatus planeStatus;
     @ManyToOne
     private Airport hangarAirport;


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
