package com.lucaskalita.airlines.users.userController;

import com.lucaskalita.airlines.users.AccountType;
import com.lucaskalita.airlines.users.UserDTO;

import com.lucaskalita.airlines.users.userService.UserDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserDateController {

    private final UserDateService userService;

    @GetMapping("/born-before/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getUsersBornBeforeDate(@PathVariable LocalDate date) {
        return userService.findUsersBornBeforeCertainDate(date);
    }

    @GetMapping("/born-after/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getUsersBornAfterDate(@PathVariable LocalDate date) {
        return userService.findUsersBornAfterCertainDate(date);
    }

    @GetMapping("/born-between/{date1}_{date2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getUsersBornBetweenDates(@PathVariable LocalDate date1, @PathVariable LocalDate date2) {
        return userService.findUsersBornBetweenDates(date1, date2);
    }
    @GetMapping("/bornByMonth/{month}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getAllUsersBornByMonth(@PathVariable String month){
        return userService.findUsersBornCertainMonth(month);
    }
    @GetMapping("/bornOnDate/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getAllUsersBornOnThatDate(LocalDate date){
        return userService.findUsersBornOnCertainDate(date);
    }
    @GetMapping("/bornToday/{date}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getAllUsersBornOnThatDate(String date){
        return userService.findBirthdayUsers(date);
    }

}