package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDateOfBirthBetween(LocalDate date1, LocalDate date2);

    List<User> findAllByDateOfBirthBefore(LocalDate date);

    List<User> findAllByDateOfBirthAfter(LocalDate date);

    List<User> findAllByAccountType(AccountType accountType);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String Email);

    Optional<User> findBySocialSecurityNumber(String socialSecurityNumber);
}
