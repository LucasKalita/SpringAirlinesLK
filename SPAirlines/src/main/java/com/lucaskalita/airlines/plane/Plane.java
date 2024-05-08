package com.lucaskalita.airlines.plane;

import jakarta.persistence.*;
import lombok.*;
import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import com.lucaskalita.airlines.seatReservation.Seat;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
public class Plane {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @OneToMany(mappedBy ="plane")
     private List<Seat> listOfRegularSeats;
     @OneToMany(mappedBy ="plane")
     private List<Seat> listOfPremiumSeats;
     @Enumerated(EnumType.STRING)
     private PlaneBrand planeBrand;
     @Enumerated(EnumType.STRING)
     private PlaneModel planeModel;
     private int totalSeatsSize ;


    public List<Seat> getListOfRegularSeats() {
        return List.copyOf(listOfRegularSeats);
    }

    public List<Seat> getListOfPremiumSeats() {
        return List.copyOf(listOfPremiumSeats);
    }

    public Plane() {
    }

    public Plane(Long id, List<Seat> listOfRegularSeats, List<Seat> listOfPremiumSeats, PlaneBrand planeBrand, PlaneModel planeModel, int totalSeatsSize) {
        this.id = id;
        this.listOfRegularSeats = listOfRegularSeats;
        this.listOfPremiumSeats = listOfPremiumSeats;
        this.planeBrand = planeModel.getBrand();
        this.planeModel = planeModel;
        this.totalSeatsSize = listOfPremiumSeats.size()+listOfRegularSeats.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane plane)) return false;

        if (!Objects.equals(id, plane.id)) return false;
        if (!Objects.equals(listOfRegularSeats, plane.listOfRegularSeats))
            return false;
        if (!Objects.equals(listOfPremiumSeats, plane.listOfPremiumSeats))
            return false;
        if (planeBrand != plane.planeBrand) return false;
        return planeModel == plane.planeModel;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (listOfRegularSeats != null ? listOfRegularSeats.hashCode() : 0);
        result = 31 * result + (listOfPremiumSeats != null ? listOfPremiumSeats.hashCode() : 0);
        result = 31 * result + (planeBrand != null ? planeBrand.hashCode() : 0);
        result = 31 * result + (planeModel != null ? planeModel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", ListOfRegularSeats=" + listOfRegularSeats +
                ", ListOfPremiumSeats=" + listOfPremiumSeats +
                ", planeBrand=" + planeModel.getBrand() +
                ", planeModel=" + planeModel +
                '}';
    }
}
