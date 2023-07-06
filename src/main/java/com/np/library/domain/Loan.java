package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.np.library.domain.enumeration.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "loan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {
    @Id
    @GeneratedValue
    @Setter
    private Long id;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @ManyToOne
    @JoinColumn(name = "library_user_id",nullable = false)
    private LibraryUser libraryUser;
    @ManyToOne
    @JoinColumn(name = "librarian_id",nullable = false)
    private Librarian librarian;
    @OneToMany(mappedBy = "loan")
    private Set<LoanItem> loanItems;

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

    public void setLoanItems(Set<LoanItem> loanItems) {
        this.loanItems = loanItems;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", status=" + status +
                ", libraryUser=" + libraryUser +
                ", librarian=" + librarian +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id.equals(loan.id) && Objects.equals(fromDate, loan.fromDate) && Objects.equals(toDate, loan.toDate) && status == loan.status && Objects.equals(libraryUser, loan.libraryUser) && Objects.equals(librarian, loan.librarian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, toDate, status, libraryUser, librarian);
    }
}
