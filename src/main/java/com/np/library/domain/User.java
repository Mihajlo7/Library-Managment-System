package com.np.library.domain;


import com.np.library.domain.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

/**
 * Klasa predstavlja korisnika biblioteke
 * Mogu biti clan biblioteke i bibliotekar
 * @author Mihajlo
 */
@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public  class User {
    /**
     * identifikator serijalizacije
     */
    private static final long serialVersionUID = 8055251038771851999L;
    /**
     * korisnicko ime
     */
    private String username;
    /**
     * sifra
     */
    private String password;
    /**
     * jedinstveni identifikator korisnika
     */
    @Id
    @GeneratedValue
    @Setter
    private Long id;
    /**
     * uloga korisnika
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;
    /**
     * biblioteka
     */
    @ManyToOne
    @JoinColumn(name = "library_id",nullable = false)
    private Library library;

    /**
     * Postavlja korisnicko ime
     * @param username korisnicko
     * @throws IllegalArgumentException korisnicko ime je null ili prazan string
     */
    public void setUsername(String username) {
        if(username==null){
            throw new IllegalArgumentException("The username must not be a null value");
        }
        if(username.equals("")){
            throw new IllegalArgumentException("The username must not be a empty value");
        }
        this.username = username;
    }

    /**
     * Postavlja sifru
     * @param password sifra korisnika kao string
     * @throws IllegalArgumentException sifra je null ili prazan string
     */
    public void setPassword(String password) {
        if(password==null){
            throw new IllegalArgumentException("The password must not be a null value");
        }
        if(password.equals("")){
            throw new IllegalArgumentException("The password must not be a empty value");
        }
        this.password = password;
    }

    /**
     * Postavlja ulogu
     * @param role uloga korisnika kao enum
     * @throws IllegalArgumentException uloga je null
     */
    public void setRole(UserRole role) {
        if(role==null){
            throw new IllegalArgumentException("The role must not be a null value");
        }
        this.role = role;
    }

    /**
     * Postavlja biblioteku
     * @param library biblioteka kao klasa Library
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * ToString metoda
     * @return vrednosti atributa korisnika kao string
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", role=" + role +
                ", library=" + library +
                '}';
    }
    /**
     * Equals metoda
     * @param o objekat se poredi sa korisnikom
     * @return true ako je objekat ima iste vrendosti kao korisnika  ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && id.equals(user.id) && role == user.role;
    }
    /**
     * HashCode metoda
     * @return  int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, password, id, role, library);
    }
}
