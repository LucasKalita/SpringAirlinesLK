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
        return planeService.findPlaneById(id);
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
    @GetMapping("/findPlaneByFlightNumber/{number}")
    @ResponseStatus(HttpStatus.FOUND)
    public PlaneDTO planeDTO(@PathVariable String number){
       return planeService.findByFlightNumber(number);
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


}