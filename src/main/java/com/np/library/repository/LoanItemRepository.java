package com.np.library.repository;

import com.np.library.domain.LoanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 Repozitorijum za pristup podacima o stavkama zaduzenja.
*/

@Repository
public interface LoanItemRepository extends JpaRepository<LoanItem,Long> {
    /**
     *Pronalazi stavke pozajmica na osnovu ID-ja zaduzenja.
     *@param id ID zaduzenja
     *@return lista stavki zaduzenja
    */
    List<LoanItem> findByLoanId(Long id);
}
