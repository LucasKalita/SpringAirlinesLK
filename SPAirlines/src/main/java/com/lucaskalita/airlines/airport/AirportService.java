package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.exceptions.WrongAirportIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AirportService  {
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    AirportMapper airportMapper;

    public AirportDTO getAirportById(Long id){
        log.trace("Searching for airport by its ID: {}", id);
        Optional<Airport> airportOptional = airportRepository.findById(id);

        if (airportOptional.isPresent()) {
            return airportMapper.fromEntityToDto(airportOptional.get());
        } else {
            throw new WrongAirportIDException("Airport not found with id: " + id);
        }
    }
    public List<AirportDTO> getAllAirports (){
            log.trace("Searching for all Airports");
            List<Airport> airports = airportRepository.findAll();
            return airports.stream()
                    .map(airportMapper::fromEntityToDto)
                    .collect(Collectors.toList());
        }

        public void deleteAirport(Long id){
        log.trace("Deleting airport by it's id: {}", id);
            if (airportRepository.existsById(id)) {
                airportRepository.deleteById(id);
            } else {
                throw new RuntimeException("Airport not found with id: " + id);
            }
        }

        public AirportDTO addAirport (AirportDTO airportDTO){
        log.trace("Creating new Airport");
            Airport airport = airportMapper.fromDtoToEntity(airportDTO);
            Airport savedAirport = airportRepository.save(airport);

            return airportMapper.fromEntityToDto(savedAirport);
        }
    }

