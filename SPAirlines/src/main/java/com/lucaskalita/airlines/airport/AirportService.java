package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.exceptions.WrongAirportIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirportService  {

   private final AirportRepository airportRepository;

  private final AirportMapper airportMapper;

    public AirportDTO getAirportById(Long id){
        log.trace("Searching for airport by its ID: {}", id);


        if (airportRepository.findById(id).isPresent()) {
            return airportMapper.fromEntityToDto(airportRepository.findById(id).get());
        } else {
            throw new WrongAirportIDException("Airport not found with id: " + id);
        }
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

