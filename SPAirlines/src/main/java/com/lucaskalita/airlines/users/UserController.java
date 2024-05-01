package com.lucaskalita.airlines.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserByID(id);

    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
         userService.updateUser(id, userDTO);
    }

    @PatchMapping("/add-money/{id}")
    public ResponseEntity<Void> addMoneyToAccount(@RequestParam BigDecimal money, @PathVariable Long id) {
        userService.addMoneyToAccount(money, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account-type/{accountType}")
    public ResponseEntity<List<UserDTO>> getUsersByAccountType(@PathVariable AccountType accountType) {
        List<UserDTO> users = userService.findUserByAccountType(accountType);
        return ResponseEntity.ok(users);
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