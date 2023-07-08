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

@Entity
@Table(name = "book_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookItem {

    public BookItem(UsageStatus usageStatus, BookStatus bookStatus, LocalDate purchaseDate, Book book) {
        this.usageStatus = usageStatus;
        this.bookStatus = bookStatus;
        this.purchaseDate = purchaseDate;
        this.book = book;
    }

    public BookItem(Long id,UsageStatus usageStatus, BookStatus bookStatus, LocalDate purchaseDate, Book book, Library library) {
        this.usageStatus = usageStatus;
        this.bookStatus = bookStatus;
        this.purchaseDate = purchaseDate;
        this.book = book;
        this.library = library;
        this.id=id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "usage_status")
    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;
    @Column(name = "book_status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    @ManyToOne
    @JoinColumn(name = "book_isbn",nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "library_id",nullable = false)
    private Library library;
    @JsonIgnore
    @OneToMany(mappedBy = "bookItem")
    private Set<LoanItem> loanItems;

    public void setUsageStatus(UsageStatus usageStatus) {
        if(usageStatus==null){
            throw new IllegalArgumentException("The usageStats must not have a null value");
        }
        this.usageStatus = usageStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        if(bookStatus==null){
            throw new IllegalArgumentException("The bookStatus must not have a null value");
        }
        this.bookStatus = bookStatus;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        if(purchaseDate==null){
            throw new IllegalArgumentException("The purchaseDate must not have a null value");
        }
        if(purchaseDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The purchaseDate must be before today");
        }
        this.purchaseDate = purchaseDate;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setLoanItems(Set<LoanItem> loanItems) {
        this.loanItems = loanItems;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(id, bookItem.id) && usageStatus == bookItem.usageStatus &&
                bookStatus == bookItem.bookStatus&&Objects.equals(purchaseDate,bookItem.getPurchaseDate()) && book.equals(bookItem.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usageStatus, bookStatus, purchaseDate, book, library);
    }
}
