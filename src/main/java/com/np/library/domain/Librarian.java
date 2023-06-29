package com.np.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "library_admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Librarian extends User {
    private String name;
    private String surname;
    @Column(name = "from_date")
    private LocalDate fromDate;

    public void setName(String name) {
        if(name==null){
            throw new IllegalArgumentException("The name must not have a null value");
        }
        if(name.equals("")){
            throw new IllegalArgumentException("The name must not be a empty value");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if(surname==null){
            throw new IllegalArgumentException("The surname must not have a null value");
        }
        if(surname.equals("")){
            throw new IllegalArgumentException("The surname must not be a empty value");
        }
        this.surname = surname;
    }

    public void setFromDate(LocalDate fromDate) {
        if(fromDate==null){
            throw new IllegalArgumentException("The from date must be not a null value");
        }
        if(fromDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The from date must be before today");
        }
        this.fromDate = fromDate;
    }
}
