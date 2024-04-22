package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.exceptions.NoMoneyOnTheAccountException;
import com.lucaskalita.airlines.exceptions.WrongDateException;
import com.lucaskalita.airlines.exceptions.WrongUserIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public User findUserByID(Long id) {
        log.info("Searching for user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            log.info("Found user with this id:{}", id);
            return user;
        }).orElseThrow(() -> new WrongUserIDException("No user with this id: " + id));
    }

    public void deleteUserByID(Long id) {
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
            throw new WrongUserIDException("User with id: " + id + " not found");
        }
    }



    public void addMoneyToAccount(BigDecimal money, Long id) {
        log.trace("Adding money({}) to account for user with id: {}", money, userRepository.findById(id));

        Optional<User> userOptional = userRepository.findById(id);
        if (userRepository.existsById(id)) {
            User user = userOptional.get();
            BigDecimal newBalance = user.getAccountBalance().add(money);
            user.setAccountBalance(newBalance);
            userRepository.save(user);

            log.trace("New balance for the user with id {}: {}", user.getId(), newBalance);
        } else {
            throw new WrongUserIDException("User with id: " + id + " not found");
        }
    }
    public void removeMoneyFromAccount(BigDecimal money, User user) throws NoMoneyOnTheAccountException {
        log.trace("Removing money({}) from account for user: {}", money, user);

        BigDecimal currentBalance = user.getAccountBalance();

        if (currentBalance.compareTo(money) < 0) {
            throw new NoMoneyOnTheAccountException("Insufficient funds");
        }

        BigDecimal newBalance = currentBalance.subtract(money);
        user.setAccountBalance(newBalance);
        userRepository.save(user);

        log.trace("New balance for the user {}: {}", user.getId(), newBalance);
    }
    public List<UserDTO> findUsersByAccountType(AccountType accountType) {
        log.trace("Searching for {} users", accountType);
        return userRepository.findAll()
                .stream()
                .filter(x -> x.getAccountType().equals(accountType))
                .map(userMapper::fromEntityToDto).collect(Collectors.toList());
    }

    public List<UserDTO> findUsersBornBeforeCertainDate(LocalDate localDate) {
        log.trace("Searching for users born before {}", localDate);
        return userRepository.findUsersByDateOfBirthBefore(localDate)
                .stream()
                .map(userMapper::fromEntityToDto)
                .toList();
    }

    public List<UserDTO> findUsersBornAfterCertainDate(LocalDate localDate) {
        log.trace("Searching for users born after {}", localDate);
        return userRepository.findUsersByDateOfBirthAfter(localDate)
                .stream()
                .map(userMapper::fromEntityToDto)
                .toList();
    }

    public List<UserDTO> findUsersBornBetweenDates(LocalDate localDate, LocalDate localDate2) {
        log.trace("Searching for users born between dates: {} and {}", localDate, localDate2);
    return userRepository.findUsersByDateOfBirthBetween(localDate, localDate2)
            .stream()
            .map(userMapper::fromEntityToDto)
            .toList();
    }
    public List<UserDTO>findUserByAccountType(AccountType accountType){
        log.trace("Searching for {}", accountType);
        return userRepository.findUsersByAccountType(accountType)
                .stream()
                .map(userMapper::fromEntityToDto)
                .toList();
    }


}