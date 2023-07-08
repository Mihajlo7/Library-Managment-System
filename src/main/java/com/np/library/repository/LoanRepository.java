package com.np.library.repository;

import com.np.library.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 Repozitorijum za pristup podacima o zaduzenjima.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    /**
     * Pronalazi iznamljivanja na osnovu ID-ja clana biblioteke.
     * @param id  clana biblioteke
     * @return Optional objekat koji sadrži listu iznamljivanja ako postoje, inace prazan Optional
     */
    Optional<List<Loan>> findByLibraryUserId(Long id);
}
