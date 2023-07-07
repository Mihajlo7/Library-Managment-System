package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {
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

    public Book(String isbn) {
        this.isbn = isbn;
    }

    @Id
    private String isbn;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    @Column(name = "number_of_items")
    private Integer numberOfItems;

    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Author> authors;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Set<BookItem> bookItems;

    public void setIsbn(String isbn) {
        if(isbn==null){
            throw new IllegalArgumentException("The isbn must not be a null");
        }
        if(isbn.length()<3 || isbn.length()>12){
            throw new IllegalArgumentException("Isbn must have between 3 and 12 numbers");
        }
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        if(title==null){
            throw new IllegalArgumentException("The title must not have a null value");
        }
        if(title.equals("")){
            throw new IllegalArgumentException("The title must not be a empty value");
        }
        this.title = title;
    }

    public void setSubject(String subject) {
        if(subject==null){
            throw new IllegalArgumentException("The subject must not have a null value");
        }
        if(subject.equals("")){
            throw new IllegalArgumentException("The subject must not be a empty value");
        }
        this.subject = subject;
    }

    public void setPublisher(String publisher) {
        if(publisher==null){
            throw new IllegalArgumentException("The publisher must not have a null value");
        }
        if(publisher.equals("")){
            throw new IllegalArgumentException("The publisher must not be a empty value");
        }
        this.publisher = publisher;
    }

    public void setLanguage(String language) {
        if(language==null){
            throw new IllegalArgumentException("The language must not have a null value");
        }
        if(language.equals("")){
            throw new IllegalArgumentException("The language must not be a empty value");
        }
        this.language = language;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        if(numberOfPages<=3){
            throw new IllegalArgumentException("Book must have more than 3 pages");
        }
        this.numberOfPages = numberOfPages;
    }

    public void setNumberOfItems(Integer numberOfItems) {

        this.numberOfItems = numberOfItems;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

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

    public void setBookItems(Set<BookItem> bookItems) {
        this.bookItems = bookItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title);
    }
}
