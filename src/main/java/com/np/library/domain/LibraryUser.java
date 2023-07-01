package com.np.library.domain;

import com.np.library.domain.enumeration.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "library_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LibraryUser extends User {
    private String name;
    private String surname;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Column(name = "time_joined")
    private LocalDate timeJoined;
    private String email;
    @Transient
    @OneToMany(mappedBy = "libraryUser")
    private Set<Loan> loans;

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

    public void setAge(Integer age) {
        if(age<10){
            throw new IllegalArgumentException("The member of library must have more than 10 year");
        }
        this.age = age;
    }

    public void setStatus(AccountStatus status) {
        if(status==null){
            throw new IllegalArgumentException("Status must not be null");
        }
        this.status = status;
    }

    public void setTimeJoined(LocalDate timeJoined) {
        if(timeJoined==null){
            throw new IllegalArgumentException("TimeJoined must not be null");
        }
        this.timeJoined = timeJoined;
    }

    public void setEmail(String email) {
        if(email==null){
            throw new IllegalArgumentException("The name must not have a null value");
        }
        if(email.equals("")){
            throw new IllegalArgumentException("The name must not be a empty value");
        }
        this.email = email;
    }

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
}
