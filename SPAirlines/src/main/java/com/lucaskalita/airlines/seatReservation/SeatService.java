package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.exceptions.WrongSeatIDException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeatService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatMapper seatMapper;

    public SeatDTO getSeatByID(Long id) {
        log.trace("Searching for seat by id:{}", id);
        return seatRepository.findById(id)
                .map(seat -> {
                    log.trace("Found id:{}", id);
                    return seatMapper.fromEntityToDto(seat);
                }).orElseThrow(WrongSeatIDException::new);

    }

    public List<SeatDTO> findAllSeats() {
        log.trace("Searching for all seats");
        return seatRepository.findAll().stream().map(seatMapper::fromEntityToDto).collect(Collectors.toList());
    }

    public List<SeatDTO> findAllAvailableSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAll()
                .stream()
                .filter(x -> !x.isReserved())
                .map(seatMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<SeatDTO> findAllReservedSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAll()
                .stream()
                .filter(Seat::isReserved)
                .map(seatMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<SeatDTO> findAllRegularSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAll()
                .stream()
                .filter(x -> !x.isPremium())
                .map(seatMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<SeatDTO> findAllPremiumSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAll()
                .stream()
                .filter(Seat::isPremium)
                .map(seatMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
}