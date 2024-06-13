package com.lucaskalita.airlines.flight.flightService;
import com.lucaskalita.airlines.airport.*;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.flight.FlightRepository;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
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
    private final AirportRepository airportRepository;

    public List<FlightDTO> findFlightsByArrivalAirport(AirportDTO airportDTO) {
        log.info("Filtering flights by arrival airport: {}", airportDTO);
        Airport arrAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByArrivalAirport(arrAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

    public List<FlightDTO> findFlightsByDepartureAirport(AirportDTO airportDTO) {
        log.trace("Filtering flights by departure airport: {}", airportDTO.toString());
        Airport depAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByDepartureAirport(depAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenCities(String depCity, String arrCity) {
        log.trace("Filtering flights connected between {} and {}", depCity, arrCity);

        Airport depAirport1 = airportRepository.findByCity(depCity)
                .orElseThrow(() -> new ObjectNotFoundException("No object found"));

        Airport arrAirport1 = airportRepository.findByCity(arrCity)
                .orElseThrow(()->new ObjectNotFoundException("No object found"));

        return flightRepository.findAllByDepartureAirport(depAirport1).stream()
                .filter(flight -> flight.getArrivalAirport().equals(arrAirport1))
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenCodes(String departureAirportCode, String arrivalAirportCode) {
        log.trace("Filtering flights connected between {} and {}", departureAirportCode, arrivalAirportCode);

        Airport depAirport1 = airportRepository.findByAirportCode(departureAirportCode)
                .orElseThrow(() -> new ObjectNotFoundException("No object found"));

        Airport arrAirport1 = airportRepository.findByAirportCode(arrivalAirportCode)
                .orElseThrow(()->new ObjectNotFoundException("No object found"));

        return flightRepository.findAllByDepartureAirport(depAirport1).stream()
                .filter(flight -> flight.getArrivalAirport().equals(arrAirport1))
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

    public List<FlightDTO> findFlightsBetweenCountries(Country departureCountry, Country arrivalCountry){
        log.trace("Searching for flight between country " + departureCountry + " and " + arrivalCountry);
       return flightRepository.findAllByDepartureAirportCountryAndArrivalAirportCountry(departureCountry, arrivalCountry)
               .stream()
               .map(flightMapper::fromEntityToDto)
               .toList();

    }
    public List<FlightDTO> findFlightsFromCityToCountry(String departureCity, Country destinationCountry){
        log.trace("Searching for flights from " + departureCity + " to " + destinationCountry.toString());

       Airport airport = airportRepository.findByCity(departureCity)
               .orElseThrow(()-> new ObjectNotFoundException("No object found"));

        return flightRepository.findAllByDepartureAirport(airport).stream()
                .filter(flight -> flight.getArrivalAirport().getCountry().equals(destinationCountry))
                .map(flightMapper::fromEntityToDto)
                .toList();

    }
    public List<FlightDTO> findFlightsCountryToCity( Country country, String city){
        log.trace("Searching for flights from " + country.toString() + " to " + city);

        Airport airport = airportRepository.findByCity(city)
                .orElseThrow(()-> new ObjectNotFoundException("No object found"));

        return flightRepository.findAllByArrivalAirport(airport).stream()
                .filter(flight -> flight.getDepartureAirport().getCountry().equals(country))
                .map(flightMapper::fromEntityToDto)
                .toList();

    }
    public List<FlightDTO> findFlightsBetweenAirports( AirportDTO depAirport, AirportDTO arrAirport){

        return flightRepository.findAllByDepartureAirportAndArrivalAirport(airportMapper.fromDtoToEntity(depAirport),airportMapper.fromDtoToEntity(arrAirport))
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
}
