package com.lucaskalita.airlines.plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PlaneRepository extends JpaRepository<Plane, Long> {
    List<Plane> findAllByPlaneBrand(String brand);
    List<Plane> findAllByPlaneModel(String model);
    Optional<Plane> findByFlightNumber(String flightNumber);
    List<Plane> findAllByRegularSeatsAmountGreaterThan(int amount);
    List<Plane> findAllByRegularSeatsAmountLessThan(int amount);
    List<Plane> findAllByPremiumSeatsAmountGreaterThan(int amount);
    List<Plane> findAllByPremiumSeatsAmountLessThan(int amount);
    List<Plane> findAllByTotalSeatsAmountGreaterThan(int value);
    List<Plane> findAllByTotalSeatsAmountLessThan(int value);

}

