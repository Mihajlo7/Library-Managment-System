package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "library")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<User> users;
    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<BookItem> bookItems;

    public void setName(String name) {
        if(name==null){
            throw new IllegalArgumentException("The name must not have a null value");
        }
        if(name.equals("")){
            throw new IllegalArgumentException("The name must not be a empty value");
        }
        this.name = name;
    }

    public void setAddress(String address) {
        if(address==null){
            throw new IllegalArgumentException("The address must not have a null value");
        }
        if(address.equals("")){
            throw new IllegalArgumentException("The address must not be a empty value");
        }
        this.address = address;
    }

    public void setCity(String city) {
        if(city==null){
            throw new IllegalArgumentException("The city must not have a null value");
        }
        if(city.equals("")){
            throw new IllegalArgumentException("The city must not be a empty value");
        }
        this.city = city;
    }

    public void setCountry(String country) {
        if(country==null){
            throw new IllegalArgumentException("The country must not have a null value");
        }
        if(country.equals("")){
            throw new IllegalArgumentException("The country must not be a empty value");
        }
        this.country = country;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
