package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.utilities.Country;
import lombok.Builder;

@Builder
public record AddressDTO(

        Country country,
        String state,
        String city,
        String postalCode,
        String street,
        String parcelNumber){
}
