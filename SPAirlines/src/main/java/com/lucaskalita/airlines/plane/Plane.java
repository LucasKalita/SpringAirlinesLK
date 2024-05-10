package com.lucaskalita.airlines.plane;

import jakarta.persistence.*;
import lombok.*;
import com.lucaskalita.airlines.seatReservation.Seat;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
public class Plane {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @OneToMany(mappedBy ="plane")
     private List<Seat> listOfRegularSeats;
     @OneToMany(mappedBy ="plane")
     private List<Seat> listOfPremiumSeats;
     private String planeBrand;
     private String planeModel;
     private int totalSeatsSize ;


    public List<Seat> getListOfRegularSeats() {
        return List.copyOf(listOfRegularSeats);
    }

    public List<Seat> getListOfPremiumSeats() {
        return List.copyOf(listOfPremiumSeats);
    }

    public Plane() {
    }

    public Plane(Long id, List<Seat> listOfRegularSeats, List<Seat> listOfPremiumSeats, String planeBrand, String planeModel, int totalSeatsSize) {
        this.id = id;
        this.listOfRegularSeats = listOfRegularSeats;
        this.listOfPremiumSeats = listOfPremiumSeats;
        this.planeBrand = planeBrand;
        this.planeModel = planeModel;
        this.totalSeatsSize = listOfPremiumSeats.size()+listOfRegularSeats.size();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", ListOfRegularSeats=" + listOfRegularSeats +
                ", ListOfPremiumSeats=" + listOfPremiumSeats +
                ", planeBrand=" + planeBrand +
                ", planeModel=" + planeModel +
                '}';
    }
}
