package com.lucaskalita.airlines.plane;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaneDTO getPlaneById(@PathVariable Long id) {
        return planeService.findPlanetById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createPlane(@RequestBody PlaneDTO planeDTO) {
        return planeService.createPlane(planeDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePlaneById(@PathVariable Long id) {
        planeService.deletePlaneById(id);
    }

    @PutMapping("/updatePlane/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePlane(@PathVariable Long id, @RequestBody PlaneDTO planeDTO) {
        planeService.updatePlane(id, planeDTO);
    }

    @GetMapping("/brand/{planeBrand}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesByBrand(@PathVariable String planeBrand) {
        return planeService.findPlanesByBrand(planeBrand);
    }

    @GetMapping("/model/{planeModel}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesByModel(@PathVariable String planeModel) {
        return planeService.findPlanesByModel(planeModel);
    }

    @GetMapping("/min-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMinimalNumberOfSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMinimalNumberOfSeats(x);
    }

    @GetMapping("/max-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMaximalNumberOfSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMaximalNumberOfSeats(x);
    }

    @GetMapping("/min-regular-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMinimalNumberOfRegularSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMinimalNumberOfRegularSeats(x);
    }

    @GetMapping("/max-regular-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMaximalNumberOfRegularSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMaximalNumberOfRegularSeats(x);
    }

    @GetMapping("/min-premium-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMinimalNumberOfPremiumSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMinimalNumberOfPremiumSeats(x);
    }

    @GetMapping("/max-premium-seats/{x}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<PlaneDTO> getPlanesWithMaximalNumberOfPremiumSeats(@PathVariable int x) {
        return planeService.findAllPlanesWithMaximalNumberOfPremiumSeats(x);
    }
}