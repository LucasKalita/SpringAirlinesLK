package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    public List<Plane> findPlanesByBrand (PlaneBrand brand);
    public List<Plane> findPlanesByModel (PlaneModel model);
    @Query(" SELECT * FROM planes WHERE listOfRegularSeats.size > ?amount")
    public List<Plane> findPlanesByListOfRegularSeatsWithMoreSeatsThan(int amount);
    @Query(" SELECT * FROM planes WHERE listOfRegularSeats.size < ?amount")
    public List<Plane> findPlanesByListOfRegularSeatsWithLessSeatsThan(int amount);
    @Query(" SELECT * FROM planes WHERE listOfPremiumSeats.size > ?amount")
    public List<Plane> findPlanesByListOfPremiumSeatsWithMoreSeatsThan(int amount);
    @Query(" SELECT * FROM planes WHERE listOfPremiumSeats.size < ?amount")
    public List<Plane> findPlanesByListOfPremiumSeatsWithLessSeatsThan(int amount);
    @Query("SELECT * FROM planes o WHERE  listOfPremiumSeats.size + listOfRegularSeats.size > :value")
    List<Plane> findPlanesByTotalNumberOfSeatsMoreThan(int value);
    @Query("SELECT * FROM planes o WHERE  listOfPremiumSeats.size + listOfRegularSeats.size < :value")
    List<Plane> findPlanesByTotalNumberOfSeatsLessThan(int value);
}

