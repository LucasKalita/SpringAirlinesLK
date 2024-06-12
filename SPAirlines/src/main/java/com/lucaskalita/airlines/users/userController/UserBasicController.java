package com.lucaskalita.airlines.users.userController;

import com.lucaskalita.airlines.users.UserDTO;
import com.lucaskalita.airlines.users.userService.UserBasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserBasicController {

    private final UserBasicService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {

        return userService.findUserByID(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
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

}