package com.lucaskalita.airlines.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findUsersByDateOfBirthBetween (LocalDate date1, LocalDate date2);
}
