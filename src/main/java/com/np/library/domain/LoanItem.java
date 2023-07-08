package com.np.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.np.library.domain.enumeration.LoanItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Klasa koja predstavlja stavku zaduzenja
 * @author Mihajlo
 */
@Entity
@Table(name = "library_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoanItem {
    /**
     * jedinstveni identifikator stavke
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * datum vracanja knjige
     */
    @Column(name = "return_date")
    private LocalDate returnDate;
    /**
     * komentar
     */
    private String description;
    /**
     * status stavke zaduzenja
     */
    @Enumerated(EnumType.STRING)
    private LoanItemStatus status;
    /**
     * zaduzenje kojem pripada stavka
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "loan_id",nullable = false)
    private Loan loan;
    /**
     * primerak knjige koji se zaduzen
     */
    @ManyToOne
    @JoinColumn(name = "book_item_id",nullable = false)
    private BookItem bookItem;

    /**
     * Postavlja datum vracanja primerka
     * @param returnDate datum vracanja knjige kao LocalDate
     * @throws IllegalArgumentException datum vracanja je razlicit od nule a status zaduznje je aktivan
     * @throws IllegalArgumentException datum vracanja je  nul a status zaduznje je vracen
     */
    public void setReturnDate(LocalDate returnDate) {
        if(status==LoanItemStatus.ACTIVE && returnDate!=null){
            throw new IllegalArgumentException("The return date must be null, because loan item is active");
        }
        if(status==LoanItemStatus.RETURNED && returnDate==null){
            throw new IllegalArgumentException("The return date must not be null, because loan item is returned");
        }
        this.returnDate = returnDate;
    }

    /**
     * Postavka komentara
     * @param description komenntra stavke zaduzenja kao string
     * @throws IllegalArgumentException komentar je razlicit od nule a status je aktivan
     * @throws IllegalArgumentException komentar je nula a status je vracen
     */
    public void setDescription(String description) {
        if(status==LoanItemStatus.ACTIVE && description!=null){
            throw new IllegalArgumentException("The description must be null, because loan item is active");
        }
        if(status==LoanItemStatus.RETURNED && description==null){
            throw new IllegalArgumentException("The description must not be null, because loan item is returned");
        }
        this.description = description;
    }

    /**
     * Postavka statusa
     * @param status status stavke zaduzenja kao enum
     */
    public void setStatus(LoanItemStatus status) {
        this.status = status;
    }

    /**
     * Postavka zaduzenje
     * @param loan zaduzenje za koje je stavka vezana
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Postavka primerka
     * @param bookItem primerak za koji je stavka zaduzenja vezana
     */
    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    /**
     * ToString metoda
     * @return vrednsot atributa stavka zaduzenja kao string
     */
    @Override
    public String toString() {
        return "LoanItem{" +
                "id=" + id +
                ", returnDate=" + returnDate +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", loan=" + loan +
                ", bookItem=" + bookItem +
                '}';
    }
    /**
     * Equals metoda
     * @param o objekat koji se poredi sa stavkom zaduznja
     * @return true ako je objekat ima iste vrendosti kao stavka zaduzenja  ili je isti objekat, false inace
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanItem loanItem = (LoanItem) o;
        return id.equals(loanItem.id) && Objects.equals(loan, loanItem.loan);
    }
    /**
     * HashCode metoda
     * @return  int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, returnDate, description, status, loan, bookItem);
    }

    /**
     * Postavka id
     * @param id jedinstveni identifikator kao long
     */
    public void setId(Long id) {
        this.id = id;
    }
}
