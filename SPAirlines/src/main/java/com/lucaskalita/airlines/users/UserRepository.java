package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<User> findAllByDateOfBirth (LocalDate localDate);

    //to_char na dzien i mies podmienic z MM na DD-MM
    @Query(value = "SELECT * FROM Account u WHERE to_char(u.date_of_birth, 'MM') = :month", nativeQuery = true)
    List<User> findUsersBornInMonth(String month);
}
