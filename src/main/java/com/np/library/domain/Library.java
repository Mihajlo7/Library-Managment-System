package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

/**
 * Klasa koja predstavlja biblioteku
 * @author Mihajlo
 */
@Entity
@Table(name = "library")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Library {
    /**
     * jedinstveni identifikator biblioteke
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private Long id;
    /**
     * naziv biblioteke
     */
    private String name;
    /**
     * adresa biblioteke
     */
    private String address;
    /**
     * grad u kojem se nalazi
     */
    private String city;
    /**
     * drzava u kojoj je biblioteka
     */
    private String country;
    /**
     * korisnici biblioteke
     */
    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<User> users;
    /**
     * primerci knjiga u biblioteci
     */
    @JsonIgnore
    @OneToMany(mappedBy = "library")
    private Set<BookItem> bookItems;

    /**
     * Postavlja naziv
     * @param name naziv biblioteke kao string
     * @throws IllegalArgumentException naziv je null ili prazan string
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
     * Postavlja adresu
     * @param address adresa biblioteke kao string
     * @throws IllegalArgumentException adresa je null ili prazan string
     */
    public void setAddress(String address) {
        if(address==null){
            throw new IllegalArgumentException("The address must not have a null value");
        }
        if(address.equals("")){
            throw new IllegalArgumentException("The address must not be a empty value");
        }
        this.address = address;
    }

    /**
     * Postavlja grad
     * @param city grad biblioteke kao string
     * @throws IllegalArgumentException grad je null ili prazan string
     */
    public void setCity(String city) {
        if(city==null){
            throw new IllegalArgumentException("The city must not have a null value");
        }
        if(city.equals("")){
            throw new IllegalArgumentException("The city must not be a empty value");
        }
        this.city = city;
    }

    /**
     * Postavlja drzavu
     * @param country drzava bublioteke kao string
     * @throws IllegalArgumentException drzava je null ili prazan string
     */
    public void setCountry(String country) {
        if(country==null){
            throw new IllegalArgumentException("The country must not have a null value");
        }
        if(country.equals("")){
            throw new IllegalArgumentException("The country must not be a empty value");
        }
        this.country = country;
    }

    /**
     * ToString metode
     * @return vrednost atributa biblioteke kao string
     */
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

    /**
     * Postavlja korisnike
     * @param users korisnici biblioteke kao set
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Postavlja primerke
     * @param bookItems primerci kao set
     */
    public void setBookItems(Set<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    /**
     * Equals metoda
     * @param o objekat se poredi sa bibliotekom
     * @return true ako je objekat ima iste vrendosti kao biblioteka ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id.equals(library.id) && Objects.equals(name, library.name) && Objects.equals(address, library.address) && Objects.equals(city, library.city) && Objects.equals(country, library.country);
    }

    /**
     * HashCode metoda
     * @return  int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, city, country);
    }
}
