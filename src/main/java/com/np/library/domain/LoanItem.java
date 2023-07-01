package com.np.library.domain;

import com.np.library.domain.enumeration.LoanItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "library_admin")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoanItem {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "return_date")
    private LocalDate returnDate;
    private String description;
    private LoanItemStatus status;
    @ManyToOne
    @JoinColumn(name = "loan_id",nullable = false)
    private Loan loan;
    @ManyToOne
    @JoinColumn(name = "book_item_id",nullable = false)
    private BookItem bookItem;

    public void setReturnDate(LocalDate returnDate) {
        if(status==LoanItemStatus.ACTIVE && returnDate!=null){
            throw new IllegalArgumentException("The return date must be null, because loan item is active");
        }
        if(status==LoanItemStatus.RETURNED && returnDate==null){
            throw new IllegalArgumentException("The return date must not be null, because loan item is returned");
        }
        this.returnDate = returnDate;
    }

    public void setDescription(String description) {
        if(status==LoanItemStatus.ACTIVE && description!=null){
            throw new IllegalArgumentException("The description must be null, because loan item is active");
        }
        if(status==LoanItemStatus.RETURNED && description==null){
            throw new IllegalArgumentException("The description must not be null, because loan item is returned");
        }
        this.description = description;
    }

    public void setStatus(LoanItemStatus status) {
        this.status = status;
    }
}
