package com.lucaskalita.airlines.plane;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
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

//TODO Wywalić listy i przypisać zwykłą liczbę miejsc, dodać do enuma opcję z liczbą miejsc premium oraz zwykłych

    public Plane() {
    }

    public Plane(Long id, String flightNumber,  String planeBrand,
                 String planeModel, int totalSeatsAmount, int regularSeatsAmount, int premiumSeatsAmount) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.planeBrand = planeBrand;
        this.planeModel = planeModel;
        this.totalSeats = totalSeatsAmount;
        this.regularSeats = regularSeatsAmount;
        this.premiumSeats = premiumSeatsAmount;
    }

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
