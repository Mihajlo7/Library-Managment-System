package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Klasa predstavlja bibliotekara
 * @author Mihajlo
 */
@Entity
@Table(name = "library_admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Librarian extends User {
    /**
     * ime bibliotekara
     */
    private String name;
    /**
     * prezime bibliotekara
     */
    private String surname;
    /**
     * datum kada je poceo da radi
     */
    @Column(name = "from_date")
    private LocalDate fromDate;
    /**
     * zaduzenja koje je odobrio
     */
    @JsonIgnore
    @OneToMany(mappedBy = "librarian")
    private Set<Loan> loans;

    /**
     * Postavlja ime
     * @param name ime bibliotekara kao string
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
     * @param surname prezime bibliotekara kao string
     * @throws IllegalArgumentException prezime je null ili prazan string
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
     * Postavlja datum zaposlenja
     * @param fromDate datum zaposlenja kao LocalDate
     * @throws IllegalArgumentException datum zaposlenja je null
     * @throws IllegalArgumentException datum zaposlenja je nakon danasnjeg dana
     */
    public void setFromDate(LocalDate fromDate) {
        if(fromDate==null){
            throw new IllegalArgumentException("The from date must be not a null value");
        }
        if(fromDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The from date must be before today");
        }
        this.fromDate = fromDate;
    }

    /**
     * Equals metoda
     * @param o objekat koji se poredi sa bibliotekarom
     * @return true ako je objekat ima iste vrendosti kao bibliotekar ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Librarian librarian = (Librarian) o;
        return name.equals(librarian.name) && surname.equals(librarian.surname) && fromDate.equals(librarian.fromDate);
    }
    /**
     * Hash code metoda
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, surname, fromDate);
    }

    /**
     * ToString metoda
     * @return vrednosti atributa bibliotekara kao string
     */
    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
