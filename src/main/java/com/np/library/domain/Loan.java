package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.np.library.domain.enumeration.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Klasa koja prestavlja zaduzenje
 * @author Mihajlo
 */
@Entity
@Table(name = "loan")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Loan {
    /**
     * jedinstveni identifikator zaduzenja
     */
    @Id
    @GeneratedValue
    @Setter
    private Long id;
    /**
     * datum pocetka zaduzenja
     */
    @Column(name = "from_date")
    private LocalDate fromDate;
    /**
     * datum kraja zaduzenja
     */
    @Column(name = "to_date")
    private LocalDate toDate;
    /**
     * status zaduzenja
     */
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    /**
     * clan za koga je vezano zaduzenje
     */
    @ManyToOne
    @JoinColumn(name = "library_user_id",nullable = false)
    private LibraryUser libraryUser;
    /**
     * bibliotekar koji je kreirao zaduzenje
     */
    @ManyToOne
    @JoinColumn(name = "librarian_id",nullable = false)
    private Librarian librarian;
    /**
     * stavke zaduzenje
     */
    @OneToMany(mappedBy = "loan")
    private Set<LoanItem> loanItems;

    /**
     * Postavlja datum pocetka zaduzenja
     * @param fromDate datum pocetka zaduzenja kao LocalDate
     * @throws IllegalArgumentException datum pocetka zaduzenja je null
     */
    public void setFromDate(LocalDate fromDate) {
        if(fromDate==null){
            throw new IllegalArgumentException("The from date must not be a null value");
        }
        this.fromDate = fromDate;
    }
    /**
     * Postavlja datum kraja zaduzenja
     * @param toDate datum kraja zaduzenja kao LocalDate
     * @throws IllegalArgumentException datum pocetka zaduzenja je null
     */
    public void setToDate(LocalDate toDate) {
        if(toDate==null){
            throw new IllegalArgumentException("The from date must not be a null value");
        }
        if(toDate.isBefore(fromDate)){
            throw new IllegalArgumentException("The from date must not be before toDate");
        }
        this.toDate = toDate;
    }

    /**
     * Postavlja status
     * @param status status zaduzenja kao Enum
     * @throws IllegalArgumentException status je null
     */
    public void setStatus(LoanStatus status) {
        if(status==null){
            throw new IllegalArgumentException("Status must not be null");
        }
        this.status = status;
    }

    /**
     * Postavlja stavke zaduzenja
     * @param loanItems stavke zaduzenja kao set
     */
    public void setLoanItems(Set<LoanItem> loanItems) {
        this.loanItems = loanItems;
    }

    /**
     * Postavlja clana
     * @param libraryUser clan za koga je vezano zaduzenje kao klasa
     */
    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    /**
     * Postavlja bibliotekara
     * @param librarian bibliotekar kao klasa
     */
    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    /**
     * ToString metoda
     * @return vrednost zaduzenja kao string
     */
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

    /**
     * Equals metoda
     * @param o objekat koji se poredi sa zaduznjem
     * @return true ako je objekat ima iste vrendosti kao zaduzenje  ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id.equals(loan.id) && Objects.equals(fromDate, loan.fromDate) && Objects.equals(toDate, loan.toDate) && status == loan.status && Objects.equals(libraryUser, loan.libraryUser) && Objects.equals(librarian, loan.librarian);
    }
    /**
     * HashCode metoda
     * @return  int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, fromDate, toDate, status, libraryUser, librarian);
    }
}
