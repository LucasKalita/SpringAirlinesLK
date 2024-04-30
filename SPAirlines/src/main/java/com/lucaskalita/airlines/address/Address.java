package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.utilities.Country;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private Country country;
    private String state;
    private String city;
    private String postalCode;
    private String street;
    private String parcelNumber;
    @OneToMany(mappedBy = "address")
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;

        if (getCountry() != address.getCountry()) return false;
        if (getState() != null ? !getState().equals(address.getState()) : address.getState() != null) return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getPostalCode() != null ? !getPostalCode().equals(address.getPostalCode()) : address.getPostalCode() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        return getParcelNumber() != null ? getParcelNumber().equals(address.getParcelNumber()) : address.getParcelNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getCountry() != null ? getCountry().hashCode() : 0;
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getPostalCode() != null ? getPostalCode().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getParcelNumber() != null ? getParcelNumber().hashCode() : 0);
        return result;
    }
}
