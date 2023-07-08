package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * @author Mihajlo Pavlovic
 * Klasa opisujue autora
 *
 */
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Author {
    /**
     * jedinstveni identifikator autora
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * ime i prezime autora
     */
    private String name;
    /**
     * biografija autora
     */
    private String biography;
    /**
     * knjige koje je napisao
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

    /**
     * Konstruktor koji prima ime i biografiju
     * @param name
     * @param biography
     */
    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    /**
     * Postavlja ime autora
     * @param name
     * @throws IllegalArgumentException ako je name null ili prazan string
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
     * Postavlja biografiju autora
     * @param biography
     */
    public void setBiography(String biography) {
//        if(biography==null){
//            throw new IllegalArgumentException("The biography must not have a null value");
//        }
//        if(biography.equals("")){
//            throw new IllegalArgumentException("The biography must not be a empty value");
//        }
        this.biography = biography;
    }

    /**
     * Postavlja knjige koje je napisao autor
     * @param books
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /**
     * ToString metoda
     * @return Vrednost atributa autora kao string
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                '}';
    }

    /**
     * Poredi dva objekta autora
     * @param o objekat sa kojim se poredi
     * @return true ako su objekti isti ili sa istim vrednostima, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id.equals(author.id) && Objects.equals(name, author.name) && Objects.equals(biography, author.biography);
    }

    /**
     * Vraca hash code vrednost autora
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, biography);
    }
}
