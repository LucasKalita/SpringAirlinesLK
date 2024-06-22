package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String Email);

    Optional<User> findByUsername(String username);

    Optional<User> findBySocialSecurityNumber(String socialSecurityNumber);

    List<User> findAllByDateOfBirthBetween(LocalDate date1, LocalDate date2);

    List<User> findAllByDateOfBirthBefore(LocalDate date);

    List<User> findAllByDateOfBirthAfter(LocalDate date);

    List<User> findAllByAccountType(AccountType accountType);

    List<User> findAllByDateOfBirth (LocalDate dateOfBirth);

    @Query(value = "SELECT * FROM Account u WHERE to_char(u.date_of_birth, 'MM') = :month", nativeQuery = true)
    List<User> findUsersBornInMonth(String month);

    @Query(value = "SELECT * FROM Account u WHERE to_char(u.date_of_birth, 'MM-DD') = :date", nativeQuery = true)
    List<User> findBirthdayUsers(String date);


}
