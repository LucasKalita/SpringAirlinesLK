package com.lucaskalita.airlines.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO){
     flightService.createNewFlight(flightDTO);
     return flightDTO;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public FlightDTO getFlightById(@PathVariable Long id){
        return flightService.findFlightById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable Long id){
        flightService.deleteFlightById(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FlightDTO updateFlight(@PathVariable Long id, @RequestBody FlightDTO flightDTO){
        return flightService.updateFlight(id, flightDTO);
    }
    @GetMapping("/{planeId}")
    @ResponseStatus(HttpStatus.FOUND)
    public FlightDTO findByPlaneId(@PathVariable Long planeId){
        return flightService.findFlightByPlaneId(planeId);
    }

}


