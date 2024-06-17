package com.lucaskalita.airlines.users.userService;

import com.lucaskalita.airlines.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserDateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findUsersBornBeforeCertainDate(LocalDate localDate) {
        log.trace("Searching for users born before {}", localDate);
        return userRepository.findAllByDateOfBirthBefore(localDate).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornAfterCertainDate(LocalDate localDate) {
        log.trace("Searching for users born after {}", localDate);
        return userRepository.findAllByDateOfBirthAfter(localDate).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornBetweenDates(LocalDate localDate, LocalDate localDate2) {
        log.trace("Searching for users born between dates: {} and {}", localDate, localDate2);
        return userRepository.findAllByDateOfBirthBetween(localDate, localDate2).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornCertainMonth(String month){
        log.trace("Searching for users born in: " + month);
        return userRepository.findUsersBornInMonth(month).stream().map(userMapper::fromEntityToDto).toList();
    }
    public List<UserDTO> findUsersBornOnCertainDate(LocalDate localDate){
        log.trace("Looking for Users born on: " + localDate);
        return userRepository.findAllByDateOfBirth(localDate).stream().map(userMapper::fromEntityToDto).toList();
    }
    public List<UserDTO> findBirthdayUsers(String date){
        log.trace("Searching for Birthdays");
        return userRepository.findBirthdayUsers(date).stream().map(userMapper::fromEntityToDto).toList();
    }

}