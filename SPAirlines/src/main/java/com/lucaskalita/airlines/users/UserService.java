package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.exceptions.WrongUserIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User findUserByID(Long id) {
        log.info("Searching for user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            log.info("Found user with this id:{}", id);
            return user;
        }).orElseThrow(() -> new WrongUserIDException("No user with this id: " + id));
    }
    public void deleteUserByID(Long id){
        log.info("Deleting user by id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new WrongUserIDException("No user with this id: " + id));

        log.info("Found user with this id:{}, deleting now", id);
        userRepository.delete(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.trace("Updating user with id: {}", id);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();

            userToUpdate.setUsername(userDTO.username());
            userToUpdate.setName(userDTO.name());
            userToUpdate.setSurname(userDTO.surname());
            userToUpdate.setAddress(userDTO.address());
            userToUpdate.setDateOfBirth(userDTO.dateOfBirth());
            userToUpdate.setSocialSecurityNumber(userDTO.socialSecurityNumber());
            userToUpdate.setPassword(passwordEncoder.encode(userDTO.password()));
            userToUpdate.setEmail(userDTO.email());
            userToUpdate.setAccountBalance(userDTO.accountBalance());
            userToUpdate.setUserListOfActiveTicketsIds(userDTO.userListOfActiveTicketsIds());
            userToUpdate.setUserListOfArchiveTicketsIds(userDTO.userListOfArchiveTicketsIds());
            userToUpdate.setAccountType(userDTO.accountType());

            // Save the updated user entity
            User updatedUser = userRepository.save(userToUpdate);

            return userMapper.fromEntityToDto(updatedUser);
        } else {
            throw new WrongUserIDException("User with id: "+id+" not found");
        }
    }
    public List<UserDTO> findAllUsers(){
        log.trace("Searching all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public void AddMoneyToAccount(BigDecimal money, User user){
        log.trace("Adding money({}) to account", money);
        user.setAccountBalance(user.getAccountBalance().add(money));
        log.trace("New balance for the {} account: {}", user, user.getAccountBalance());
    }
    public List<UserDTO> findUsersByAccountType(AccountType accountType){
        log.trace("Searching for {} users", accountType);
        return userRepository.findAll()
                .stream()
                .filter(x->x.getAccountType().equals(accountType))
                .map(userMapper::fromEntityToDto).collect(Collectors.toList());
    }
    public List<UserDTO> findUsersBornBeforeCertainDate (LocalDateTime localDateTime){
        Loca
    }



}
