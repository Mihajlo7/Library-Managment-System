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
 * @author Mihajlo
 * klasa predstavlja knjigu
 */
@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {
    /**
     * Konstruktor koji prima sve vredosti bez id
     * @param isbn
     * @param title
     * @param subject
     * @param publisher
     * @param language
     * @param numberOfPages
     * @param numberOfItems
     * @param authors
     */
    public Book(String isbn, String title, String subject, String publisher, String language, Integer numberOfPages, Integer numberOfItems, Set<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.numberOfItems = numberOfItems;
        this.authors = authors;
    }

    /**
     * jedinstveni identifikator knjige
     */
    @Id
    private String isbn;
    /**
     * naslov knjige
     */
    private String title;
    /**
     * opis knjige
     */
    private String subject;
    /**
     * objavljivac knjige
     */
    private String publisher;
    /**
     * jezik na kojem je napisana knjiga
     */
    private String language;
    /**
     * broj strana knjige
     */
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    /**
     * broj primeraka knjige u biblioteci
     */
    @Column(name = "number_of_items")
    private Integer numberOfItems;

    /**
     * autori knjige
     */
    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Author> authors;
    /**
     * primerci knjige koji postoje u biblioteci
     */
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Set<BookItem> bookItems;

    /**
     * Postavlja isbn knjige
     * @param isbn vrednost isbn kao string
     * @throws IllegalArgumentException isbn je null ili prazan string
     */
    public void setIsbn(String isbn) {
        if(isbn==null){
            throw new IllegalArgumentException("The isbn must not be a null");
        }
        if(isbn.length()<3 || isbn.length()>12){
            throw new IllegalArgumentException("Isbn must have between 3 and 12 numbers");
        }
        this.isbn = isbn;
    }

    /**
     * Postavlja naslov
     * @param title vrednost naslova kao string
     * @throws IllegalArgumentException naslov je null ili prazan string
     */
    public void setTitle(String title) {
        if(title==null){
            throw new IllegalArgumentException("The title must not have a null value");
        }
        if(title.equals("")){
            throw new IllegalArgumentException("The title must not be a empty value");
        }
        this.title = title;
    }

    /**
     * Postavlja opis knjige
     * @param subject
     * @throws IllegalArgumentException opis knjige je null ili prazan string
     */
    public void setSubject(String subject) {
        if(subject==null){
            throw new IllegalArgumentException("The subject must not have a null value");
        }
        if(subject.equals("")){
            throw new IllegalArgumentException("The subject must not be a empty value");
        }
        this.subject = subject;
    }

    /**
     * Postavlja obljavaca knjige
     * @param publisher obljavljivac knjige kao string
     * @throws IllegalArgumentException obljavljivac knjige null ili prazan string
     *
     */
    public void setPublisher(String publisher) {
        if(publisher==null){
            throw new IllegalArgumentException("The publisher must not have a null value");
        }
        if(publisher.equals("")){
            throw new IllegalArgumentException("The publisher must not be a empty value");
        }
        this.publisher = publisher;
    }

    /**
     * Postavlja jezik
     * @param language jezik na kojem je knjiga kao string
     * @throws IllegalArgumentException jezik knjige je null ili prazan string
     */
    public void setLanguage(String language) {
        if(language==null){
            throw new IllegalArgumentException("The language must not have a null value");
        }
        if(language.equals("")){
            throw new IllegalArgumentException("The language must not be a empty value");
        }
        this.language = language;
    }

    /**
     * Postavlja broj stranice knjige
     * @param numberOfPages broj stranica knjige kao celobrojna vrednost
     * @throws IllegalArgumentException broj stranica manji od 3
     */
    public void setNumberOfPages(Integer numberOfPages) {
        if(numberOfPages<=3){
            throw new IllegalArgumentException("Book must have more than 3 pages");
        }
        this.numberOfPages = numberOfPages;
    }

    /**
     * Postavlja broj primeraka knjige u biblioteci
     * @param numberOfItems broj primeraka knjige kao celobrojna vrednost
     */
    public void setNumberOfItems(Integer numberOfItems) {

        this.numberOfItems = numberOfItems;
    }

    /**
     * Postavlja autore knjige
     * @param authors autori knjige kao Set
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    /**
     * ToString metoda
     * @return vrednosti atributa knjige kao string
     */
    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", numberOfItems=" + numberOfItems +
                '}';
    }

    /**
     * Postavlja primerke knjige
     * @param bookItems primerci knjige kao Set
     */
    public void setBookItems(Set<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    /**
     * Equals metoda
     * @param o poredi objekat sa knjigom
     * @return true ako je objekat ima iste vrendosti kao knjiga ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    /**
     * Hash code metoda
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(isbn, title);
    }
}
