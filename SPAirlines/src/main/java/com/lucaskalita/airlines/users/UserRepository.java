package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByDateOfBirthBetween(LocalDate date1, LocalDate date2);
    public List<User> findAllByDateOfBirthBefore(LocalDate date);
    public List<User> findAllByDateOfBirthAfter(LocalDate date);
    public List<User> findAllByAccountType(AccountType accountType);
    public User findByUsername(String username);
}
