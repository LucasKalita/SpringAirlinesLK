package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    public List<Plane> findAllByPlaneBrand(PlaneBrand brand);
    public List<Plane> findAllByPlaneModel(PlaneModel model);
    @Query("SELECT p FROM Plane p WHERE" +
            " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = false) > :amount")
    public List<Plane> findByListOfRegularSeatsSizeGreaterThan(int amount);
    @Query("SELECT p FROM Plane p WHERE" +
            " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = false) < :amount")
    public List<Plane> findByListOfRegularSeatsSizeLessThan(int amount);
    @Query("SELECT p FROM Plane p WHERE" +
            " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = true) > :amount")
    public List<Plane> findByListOfPremiumSeatsSizeGreaterThan(int amount);
    @Query("SELECT p FROM Plane p WHERE" +
            " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = true) < :amount")
    public List<Plane> findByListOfPremiumSeatsSizeLessThan(int amount);
    List<Plane> findByTotalSeatsSizeGreaterThan(int value);
    List<Plane> findByTotalSeatsSizeLessThan(int value);
}

