package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    public List<PlaneDTO> findPlanesByBrand (PlaneBrand brand);
    public List<PlaneDTO> findPlanesByModel (PlaneModel model);
    @Query(" SELECT * FROM planes WHERE listOfRegularSeats.size > ?amount")
    public List<PlaneDTO> findPlanesByListOfRegularSeatsWithMoreSeatsThan(int amount);
    @Query(" SELECT * FROM planes WHERE listOfRegularSeats.size > ?amount")
    public List<PlaneDTO> findPlanesByListOfRegularSeatsWithLessSeatsThan(int amount);
}
