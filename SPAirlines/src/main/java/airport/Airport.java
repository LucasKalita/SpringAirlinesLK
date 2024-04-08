package airport;

import jakarta.persistence.Entity;

@Entity
public class Airport {
    String country;
    String airportCode;

    @Override
    public String toString() {
        return "Airport{" +
                "country='" + country + '\'' +
                ", airportCode='" + airportCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;

        if (!getCountry().equals(airport.getCountry())) return false;
        return getAirportCode().equals(airport.getAirportCode());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getAirportCode().hashCode();
        return result;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
