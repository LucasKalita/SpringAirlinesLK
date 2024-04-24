package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.exceptions.WrongSeatIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;

    public SeatDTO findSeatById(Long id) {
        log.trace("Searching for seat by id: {}", id);

        Optional<Seat> optionalSeat = seatRepository.findById(id);

        optionalSeat.ifPresentOrElse(
                seat -> log.trace("Found seat: {}",
                        seatMapper.fromEntityToDto(seat)),
                () -> {
                    throw new WrongSeatIDException("Wrong seat id: " + id);
                }
        );

        return optionalSeat.map(seatMapper::fromEntityToDto).orElse(null);
    }



    public List<SeatDTO> findAllUnReservedSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsReservedFalse()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }
    public  List<SeatDTO> findAllReservedSeats(){
        log.trace("Searching for all reserved seats");
        return seatRepository.findAllByIsReservedTrue()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }

    public List<SeatDTO> findAllRegularSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsPremiumFalse()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }

    public List<SeatDTO> findAllPremiumSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsPremiumTrue()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }
}