package com.np.library.domain;

import com.np.library.domain.enumeration.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LoanStatus status;
    @ManyToOne
    @JoinColumn(name = "library_user_id",nullable = false)
    private LibraryUser libraryUser;
    @ManyToOne
    @JoinColumn(name = "librarian_id",nullable = false)
    private Librarian librarian;

    public void setFromDate(LocalDate fromDate) {
        if(fromDate==null){
            throw new IllegalArgumentException("The from date must not be a null value");
        }
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        if(toDate==null){
            throw new IllegalArgumentException("The from date must not be a null value");
        }
        if(toDate.isBefore(fromDate)){
            throw new IllegalArgumentException("The from date must not be before toDate");
        }
        this.toDate = toDate;
    }

    public void setStatus(LoanStatus status) {
        if(status==null){
            throw new IllegalArgumentException("Status must not be null");
        }
        this.status = status;
    }
}
