package com.np.library.domain;

import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "book_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookItem {
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
}
