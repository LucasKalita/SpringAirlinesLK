package com.lucaskalita.airlines.plane;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import com.lucaskalita.airlines.seatReservation.Seat;

import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Plane {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private List<Seat> ListOfRegularSeats;
     private List<Seat> ListOfPremiumSeats;
     @Enumerated
     PlaneBrand planeBrand;
     @Enumerated
     PlaneModel planeModel;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane plane)) return false;

        if (!Objects.equals(id, plane.id)) return false;
        if (!Objects.equals(ListOfRegularSeats, plane.ListOfRegularSeats))
            return false;
        if (!Objects.equals(ListOfPremiumSeats, plane.ListOfPremiumSeats))
            return false;
        if (planeBrand != plane.planeBrand) return false;
        return planeModel == plane.planeModel;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ListOfRegularSeats != null ? ListOfRegularSeats.hashCode() : 0);
        result = 31 * result + (ListOfPremiumSeats != null ? ListOfPremiumSeats.hashCode() : 0);
        result = 31 * result + (planeBrand != null ? planeBrand.hashCode() : 0);
        result = 31 * result + (planeModel != null ? planeModel.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", ListOfRegularSeats=" + ListOfRegularSeats +
                ", ListOfPremiumSeats=" + ListOfPremiumSeats +
                ", planeBrand=" + planeBrand +
                ", planeModel=" + planeModel +
                '}';
    }
}
