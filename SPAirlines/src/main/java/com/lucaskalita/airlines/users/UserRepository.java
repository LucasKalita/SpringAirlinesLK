package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findUsersByDateOfBirthBetween (LocalDate date1, LocalDate date2);
    public List<User> findUsersByDateOfBirthBefore(LocalDate date);
    public List<User> findUsersByDateOfBirthAfter(LocalDate date);
    public List<User> findUsersByAccountType(AccountType accountType);
}
