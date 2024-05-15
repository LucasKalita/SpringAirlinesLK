package com.lucaskalita.airlines.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Long createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserByID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    @PutMapping("/add-money/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addMoneyToAccount(@RequestParam BigDecimal money, @PathVariable String username) {
        userService.addMoneyToAccount(money, username);

    }
    @PutMapping("/remove-money/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMoneyFromAccount(@RequestParam BigDecimal money, @PathVariable String username){
        userService.removeMoneyFromAccount(money, username);
    }

    @GetMapping("/account-type/{accountType}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsersByAccountType(@PathVariable AccountType accountType) {
        return (userService.findUserByAccountType(accountType));
    }

    @GetMapping("/born-before/{date}")
    public ResponseEntity<List<UserDTO>> getUsersBornBeforeDate(@PathVariable LocalDate date) {
        List<UserDTO> users = userService.findUsersBornBeforeCertainDate(date);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/born-after/{date}")
    public ResponseEntity<List<UserDTO>> getUsersBornAfterDate(@PathVariable LocalDate date) {
        List<UserDTO> users = userService.findUsersBornAfterCertainDate(date);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/born-between/{date1}/{date2}")
    public ResponseEntity<List<UserDTO>> getUsersBornBetweenDates(@PathVariable LocalDate date1,
                                                                  @PathVariable LocalDate date2) {
        List<UserDTO> users = userService.findUsersBornBetweenDates(date1, date2);
        return ResponseEntity.ok(users);
    }

}