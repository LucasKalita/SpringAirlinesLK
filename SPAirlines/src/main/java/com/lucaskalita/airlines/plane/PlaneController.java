package com.lucaskalita.airlines.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaneById(@PathVariable Long id) {
        PlaneDTO planeDTO = planeService.findPlanetById(id);
        if (planeDTO != null) {
            return ResponseEntity.ok(planeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPlane(@RequestBody PlaneDTO planeDTO) {

        return planeService.createPlane(planeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaneById(@PathVariable Long id) {
        planeService.deletePlaneById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlane(@PathVariable Long id, @RequestBody PlaneDTO planeDTO) {
        PlaneDTO updatedPlane = planeService.updatePlane(id, planeDTO);
        return ResponseEntity.ok(updatedPlane);
    }

    @GetMapping("/brand/{planeBrand}")
    public List<PlaneDTO> getPlanesByBrand(@PathVariable String planeBrand) {
        return planeService.findPlanesByBrand(planeBrand);
    }

    @GetMapping("/model/{model}")
    public List<PlaneDTO> getPlanesByModel(@PathVariable String model) {
        return planeService.findPlanesByModel(model);
    }

    @GetMapping("/min-seats/{x}")
    public ResponseEntity<List<?>> getPlanesWithMinimalNumberOfSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMinimalNumberOfSeats(x);
        return ResponseEntity.ok(planes);
    }
    @GetMapping("/max-seats/{x}")
    public ResponseEntity<List<?>> getPlanesWithMaximalNumberOfSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMaximalNumberOfSeats(x);
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/min-regular-seats/{x}")
    public ResponseEntity<List<?>> getPlanesWithMinimalNumberOfRegularSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMinimalNumberOfRegularSeats(x);
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/max-regular-seats/{x}")
    public ResponseEntity<List<?>> getPlanesWithMaximalNumberOfRegularSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMaximalNumberOfRegularSeats(x);
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/min-premium-seats/{x}")
    public ResponseEntity<List<?>> getPlanesWithMinimalNumberOfPremiumSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMinimalNumberOfPremiumSeats(x);
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/max-premium-seats/{x}")

    public ResponseEntity<List<?>> getPlanesWithMaximalNumberOfPremiumSeats(@PathVariable int x) {
        List<?> planes = planeService.findAllPlanesWithMaximalNumberOfPremiumSeats(x);
        return ResponseEntity.ok(planes);
    }
}