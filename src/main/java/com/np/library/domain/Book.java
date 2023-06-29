package com.np.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book {
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

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;
    @OneToMany(mappedBy = "book")
    private Set<BookItem> bookItems;

    public void setIsbn(String isbn) {
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
        if(numberOfPages<=0){
            throw new IllegalArgumentException("Book must have 1 instance");
        }
        this.numberOfItems = numberOfItems;
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
}
