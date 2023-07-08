package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.np.library.domain.enumeration.AccountStatus;
import com.np.library.domain.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Klasa prestavlja clanove biblioteke
 * @author Mihajlo
 */
@Entity
@Table(name = "library_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class LibraryUser extends User {
    /**
     * Konstruktor
     * @param username
     * @param password
     * @param id
     * @param role
     * @param library
     * @param name
     * @param surname
     * @param age
     * @param status
     * @param timeJoined
     * @param email
     * @param loans
     */
    public LibraryUser(String username, String password, Long id, UserRole role, Library library, String name, String surname, Integer age, AccountStatus status, LocalDate timeJoined, String email, Set<Loan> loans) {
        super(username, password, id, role, library);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.status = status;
        this.timeJoined = timeJoined;
        this.email = email;
        this.loans = loans;
    }

    /**
     * ime clana
     */
    private String name;
    /**
     * prezime clana
     */
    private String surname;
    /**
     * godine starosti
     */
    private Integer age;
    /**
     * status naloga
     */
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    /**
     * datum uclanjenja
     */
    @Column(name = "time_joined")
    private LocalDate timeJoined;
    /**
     * email clana
     */
    private String email;
    /**
     * zaduzenja clana
     */
    @JsonIgnore
    @OneToMany(mappedBy = "libraryUser")
    private Set<Loan> loans;

    /**
     * Postavlja ime
     * @param name ime clana kao string
     * @throws IllegalArgumentException ime je null ili prazan string
     */
    public void setName(String name) {
        if(name==null){
            throw new IllegalArgumentException("The name must not have a null value");
        }
        if(name.equals("")){
            throw new IllegalArgumentException("The name must not be a empty value");
        }
        this.name = name;
    }

    /**
     * Postavlja prezime
     * @param surname prezime clana kao string
     * @throws IllegalArgumentException prezime clana je null ili prazan string
     */
    public void setSurname(String surname) {
        if(surname==null){
            throw new IllegalArgumentException("The surname must not have a null value");
        }
        if(surname.equals("")){
            throw new IllegalArgumentException("The surname must not be a empty value");
        }
        this.surname = surname;
    }

    /**
     * Postavlja godine
     * @param age godine kao celobrojna vrednost
     * @throws IllegalArgumentException godine su manje od 10
     */
    public void setAge(Integer age) {
        if(age<10){
            throw new IllegalArgumentException("The member of library must have more than 10 year");
        }
        this.age = age;
    }

    /**
     * Postavlja status
     * @param status status naloge kao Enum vrednost
     * @throws IllegalArgumentException status je null
     */
    public void setStatus(AccountStatus status) {
        if(status==null){
            throw new IllegalArgumentException("Status must not be null");
        }
        this.status = status;
    }

    /**
     * Postavlja datum uclanjenja
     * @param timeJoined datum uclanjenja kao LocalDate
     * @throws  IllegalArgumentException datum uclanjenja je null
     */
    public void setTimeJoined(LocalDate timeJoined) {
        if(timeJoined==null){
            throw new IllegalArgumentException("TimeJoined must not be null");
        }
        this.timeJoined = timeJoined;
    }

    /**
     * Postavlja email
     * @param email email vrednost kao string
     * @throws IllegalArgumentException email je null ili prazan string
     */
    public void setEmail(String email) {
        if(email==null){
            throw new IllegalArgumentException("The email must not have a null value");
        }
        if(email.equals("")){
            throw new IllegalArgumentException("The email must not be a empty value");
        }
        this.email = email;
    }

    /**
     * ToString metoda
     * @return vrednosti clana kao string
     */
    @Override
    public String toString() {
        return "LibraryUser{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", timeJoined=" + timeJoined +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Postavlja zaduzenja clana
     * @param loans zaduzenja clana kao set
     */
    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    /**
     * Equals metoda
     * @param o objekat se poredi sa clanom
     * @return true ako je objekat ima iste vrendosti kao clana  ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryUser user = (LibraryUser) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age) && status == user.status && Objects.equals(timeJoined, user.timeJoined) && Objects.equals(email, user.email);
    }
    /**
     * HashCode metoda
     * @return  int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, status, timeJoined, email);
    }
}
