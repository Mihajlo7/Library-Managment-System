package com.np.library.repository;

import com.np.library.domain.LoanItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanItemRepository extends JpaRepository<LoanItem,Long> {
}