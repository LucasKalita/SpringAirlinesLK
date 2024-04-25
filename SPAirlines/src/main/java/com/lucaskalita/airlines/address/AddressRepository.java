package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.utilities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findAllAddressByCountry(Country country);
    public List<Address> findAllAddressByState(String State);
    public List<Address> findAllAddressByCity(String City);
    public List<Address> findAllAddressByPostalCode(String postalCode);
    }

