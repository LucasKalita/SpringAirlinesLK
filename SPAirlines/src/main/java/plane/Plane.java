package plane;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import plane.enums.PlaneBrand;
import plane.enums.PlaneModel;
import seatReservation.Seat;

import java.util.List;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getListOfRegularSeats() {
        return ListOfRegularSeats;
    }

    public void setListOfRegularSeats(List<Seat> listOfRegularSeats) {
        ListOfRegularSeats = listOfRegularSeats;
    }

    public List<Seat> getListOfPremiumSeats() {
        return ListOfPremiumSeats;
    }

    public void setListOfPremiumSeats(List<Seat> listOfPremiumSeats) {
        ListOfPremiumSeats = listOfPremiumSeats;
    }

    public PlaneBrand getPlaneBrand() {
        return planeBrand;
    }

    public void setPlaneBrand(PlaneBrand planeBrand) {
        this.planeBrand = planeBrand;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }
    public int getNumberOfRegularSeats(){return getListOfRegularSeats().size();}
    public int getNumberOfPremiumSeats(){return getListOfPremiumSeats().size();}
    public int getTotalNumberOfSeats(){return getNumberOfPremiumSeats()+getNumberOfRegularSeats();}

    public Plane() {
    }

    public Plane(Long id, List<Seat> listOfRegularSeats, List<Seat> listOfPremiumSeats, PlaneBrand planeBrand, PlaneModel planeModel) {
        this.id = id;
        ListOfRegularSeats = listOfRegularSeats;
        ListOfPremiumSeats = listOfPremiumSeats;
        this.planeBrand = planeBrand;
        this.planeModel = planeModel;
    }

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
