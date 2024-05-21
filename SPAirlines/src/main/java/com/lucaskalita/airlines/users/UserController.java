package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.utilities.MoneyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        return userService.findUserByID(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
    }

    @PutMapping("/refundTicket/{}")
    @ResponseStatus(HttpStatus.OK)
    public void refundTicket(String ticketNumber) {
    userService.refundTicket(ticketNumber);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {

        userService.deleteUserByID(id);
    }

    @PutMapping("/userDetails/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserDetails(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUserDetails(id, userDTO);
    }

    @PutMapping("/userAddress/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserAddress(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUserAddress(id, userDTO);
    }

    @PutMapping("/add-money/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addMoneyToAccount(@RequestBody MoneyDTO money, @PathVariable String username) {
        userService.addMoneyToAccount(money.getMoney(), username);
    }

    @PutMapping("/remove-money/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMoneyFromAccount(@RequestBody BigDecimal money, @PathVariable String username) {
        userService.withdrawMoneyFromAccount(money, username);
    }

    @PutMapping("/buyTicket/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void buyTicket(@RequestBody TicketDTO ticketDTO, @PathVariable String username) {
        userService.buyTicket(ticketDTO, username);
    }

    @GetMapping("/account-type/{accountType}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsersByAccountType(@PathVariable AccountType accountType) {
        return (userService.findUserByAccountType(accountType));
    }

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

    @GetMapping("/born-between/{date1}-{date2}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDTO> getUsersBornBetweenDates(@PathVariable LocalDate date1, @PathVariable LocalDate date2) {
        return userService.findUsersBornBetweenDates(date1, date2);
    }

}