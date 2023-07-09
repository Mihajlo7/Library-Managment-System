package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Klasa koja predstavlja primerak knjige
 * @author Mihajlo
 */
@Entity
@Table(name = "book_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookItem {
    /**
     * Konstruktor
     * @param usageStatus status koriscenja
     * @param bookStatus status knjige
     * @param purchaseDate datum nabavke
     * @param book knjiga
     */
    public BookItem(UsageStatus usageStatus, BookStatus bookStatus, LocalDate purchaseDate, Book book) {
        this.usageStatus = usageStatus;
        this.bookStatus = bookStatus;
        this.purchaseDate = purchaseDate;
        this.book = book;
    }

    /**
     * jedinstveni identifikator primerka
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * dostupnost primerka
     */
    @Column(name = "usage_status")
    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;
    /**
     * status primerka
     */
    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    /**
     * datum nabavke primerka
     */
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    /**
     * knjiga
     */
    @ManyToOne
    @JoinColumn(name = "book_isbn",nullable = false)
    private Book book;
    /**
     * biblioteka u kojoj se nalazi primerak knjige
     */
    @ManyToOne
    @JoinColumn(name = "library_id",nullable = false)
    private Library library;
    /**
     * zaduzenja primeraka
     */
    @JsonIgnore
    @OneToMany(mappedBy = "bookItem")
    private Set<LoanItem> loanItems;

    /**
     * Postavlja dostupnost primerka
     * @param usageStatus dostupnost primerka kao Enum vrednost
     * @throws IllegalArgumentException dostupnost primerka je null
     */
    public void setUsageStatus(UsageStatus usageStatus) {
        if(usageStatus==null){
            throw new IllegalArgumentException("The usageStats must not have a null value");
        }
        this.usageStatus = usageStatus;
    }

    /**
     * Postavlja status primerka
     * @param bookStatus status primerka kao Enum vrednost
     * @throws IllegalArgumentException status primerka je null
     */
    public void setBookStatus(BookStatus bookStatus) {
        if(bookStatus==null){
            throw new IllegalArgumentException("The bookStatus must not have a null value");
        }
        this.bookStatus = bookStatus;
    }

    /**
     * Postavlja datum nabavke primerka
     * @param purchaseDate datum nabavke primerka kao LocalDate
     * @throws IllegalArgumentException datum nabavke primerka je null
     * @throws IllegalArgumentException datum nabavke primerka je posle danasnje dana
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        if(purchaseDate==null){
            throw new IllegalArgumentException("The purchaseDate must not have a null value");
        }
        if(purchaseDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The purchaseDate must be before today");
        }
        this.purchaseDate = purchaseDate;
    }

    /**
     * Postavlja knjigu
     * @param book knjiga za koju je vezan primerak kao klasa Book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Postavlja biblioteku
     * @param library biblioteka kao klasa Library
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * Postavlja istoriju zaduzivanja
     * @param loanItems zaduzivanja knjige kao set zaduzenja
     */
    public void setLoanItems(Set<LoanItem> loanItems) {
        this.loanItems = loanItems;
    }

    /**
     * ToString metoda
     * @return vrednosti atributa primerka kao string
     */
    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", usageStatus=" + usageStatus +
                ", bookStatus=" + bookStatus +
                ", purchaseDate=" + purchaseDate +
                ", book=" + book +
                ", library=" + library +
                '}';
    }

    /**
     * Equals metoda
     * @param o objekat se poredi sa primerkom
     * @return true ako je objekat ima iste vrendosti kao primerak ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(id, bookItem.id) && usageStatus == bookItem.usageStatus &&
                bookStatus == bookItem.bookStatus&&Objects.equals(purchaseDate,bookItem.getPurchaseDate()) && book.equals(bookItem.getBook());
    }
    /**
     * Hash code metoda
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, usageStatus, bookStatus, purchaseDate, book, library);
    }
}
