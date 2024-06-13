package com.lucaskalita.airlines.users.userController;

import com.lucaskalita.airlines.users.AccountType;
import com.lucaskalita.airlines.users.UserDTO;
import com.lucaskalita.airlines.users.userService.UserAccountService;
import com.lucaskalita.airlines.utilities.MoneyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userService;


    @PutMapping("/add-money/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addMoneyToAccount(@RequestBody MoneyDTO money, @PathVariable String username) {
        userService.addMoneyToAccount(money.getMoney(), username);
    }

    @PutMapping("/remove-money/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMoneyFromAccount(@RequestBody MoneyDTO money, @PathVariable String username) {
        userService.withdrawMoneyFromAccount(money.getMoney(), username);
    }

    @GetMapping("/account-type/{accountType}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getUsersByAccountType(@PathVariable AccountType accountType) {
        return userService.findUserByAccountType(accountType);
    }
    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO findUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }
    @GetMapping("/socialSecurityNumber/{number}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO findUserBySocialSecurityNumber(@PathVariable String number){
        return userService.findUserBySecurityNumber(number);
    }
}