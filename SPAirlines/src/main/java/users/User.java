package users;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import ticket.Ticket;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.Period;
import java.util.List;
import java.util.Objects;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String Address;
    private LocalDateTime dateOfBirth;
    private String Pesel;
    private String password;
    private String email;
    private BigDecimal accountBalance;
    private List<Ticket> userListOfActiveTickets;
    private List<Ticket> userListOfArchiveTickets;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPesel(String pesel) {
        Pesel = pesel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setUserListOfActiveTickets(List<Ticket> userListOfActiveTickets) {
        this.userListOfActiveTickets = userListOfActiveTickets;
    }

    public void setUserListOfArchiveTickets(List<Ticket> userListOfArchiveTickets) {
        this.userListOfArchiveTickets = userListOfArchiveTickets;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return Address;
    }
    public int getUserAge(){
        return Period.between(dateOfBirth.toLocalDate(),LocalDateTime.now().toLocalDate()).getYears();
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPesel() {
        return Pesel;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public List<Ticket> getUserListOfActiveTickets() {
        return userListOfActiveTickets;
    }

    public List<Ticket> getUserListOfArchiveTickets() {
        return userListOfArchiveTickets;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(Address, user.Address)) return false;
        if (!Objects.equals(dateOfBirth, user.dateOfBirth)) return false;
        if (!Objects.equals(Pesel, user.Pesel)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(accountBalance, user.accountBalance))
            return false;
        if (!Objects.equals(userListOfActiveTickets, user.userListOfActiveTickets))
            return false;
        if (!Objects.equals(userListOfArchiveTickets, user.userListOfArchiveTickets))
            return false;
        return accountType == user.accountType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (Address != null ? Address.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (Pesel != null ? Pesel.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (accountBalance != null ? accountBalance.hashCode() : 0);
        result = 31 * result + (userListOfActiveTickets != null ? userListOfActiveTickets.hashCode() : 0);
        result = 31 * result + (userListOfArchiveTickets != null ? userListOfArchiveTickets.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Address='" + Address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", Pesel='" + Pesel + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", accountBalance=" + accountBalance +
                ", userListOfActiveTickets=" + userListOfActiveTickets +
                ", userListOfArchiveTickets=" + userListOfArchiveTickets +
                ", accountType=" + accountType +
                '}';
    }
}