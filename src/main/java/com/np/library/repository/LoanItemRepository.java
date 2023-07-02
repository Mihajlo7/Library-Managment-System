package com.np.library.repository;

import com.np.library.domain.LoanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanItemRepository extends JpaRepository<LoanItem,Long> {
    List<LoanItem> findByLoanId(Long id);
}
