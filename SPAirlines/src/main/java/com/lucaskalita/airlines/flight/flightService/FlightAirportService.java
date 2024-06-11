package com.lucaskalita.airlines.flight.flightService;
import com.lucaskalita.airlines.airport.*;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.flight.FlightRepository;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FlightAirportService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;
    private final AirportService airportService;

    public List<FlightDTO> findFlightsByArrivalAirport(AirportDTO airportDTO) {
        log.info("Filtering flights by arrival airport: {}", airportDTO);
        Airport arrAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByArrivalAirport(arrAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

    public List<FlightDTO> findFlightsByDepartureAirport(AirportDTO airportDTO) {
        log.trace("Filtering flights by departure airport: {}", airportDTO);
        Airport depAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByDepartureAirport(depAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenAirports(AirportDTO depAirport, AirportDTO arrAirport) {
        log.trace("Filtering flights connected between {} and {}", depAirport, arrAirport);
        Airport depAirport1 = airportMapper.fromDtoToEntity(depAirport);
        Airport arrAirport1 = airportMapper.fromDtoToEntity(arrAirport);
        return flightRepository.findAllByDepartureAirportAndArrivalAirport(depAirport1, arrAirport1)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenCities(String departureAirportCity, String arrivalAirportCity){
        log.trace("Searching for flight between city " + departureAirportCity + " and " + arrivalAirportCity);
        AirportDTO departureAirport = airportService.findAirportByCity(departureAirportCity);
        AirportDTO arrivalAirport = airportService.findAirportByCity(arrivalAirportCity);
        return findFlightsBetweenAirports(departureAirport, arrivalAirport);
    }
    //TODO ogarnąc to gówno nizej, wywalić szukanie po czystych airportach
    public List<FlightDTO> findFlightsBetweenCountries(Country departureCountry, Country arrivalCountry){
        log.trace("Searching for flight between country " + departureCountry + " and " + arrivalCountry);
        //   AirportDTO airportDTO1 = airportService.findAllAirportsInCountry(departureCountry, arrivalCountry);
        return  null;
    }
}
