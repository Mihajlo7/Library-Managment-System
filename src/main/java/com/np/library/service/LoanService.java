package com.np.library.service;

import com.np.library.domain.Loan;

import java.util.List;
/**
 * Interfejs koji predstavlja servis za rad sa iznamljivanjem.
 */
public interface LoanService {
    /**
     * Kreira novo iznamljivanje.
     *
     * @param loan iznamljivanje koja se kreira
     */
    public void createLoan(Loan loan);
    /**
     * Vraca iznamljivanje sa datim identifikatorom.
     *
     * @param id identifikator iznamljivanja koje se vraća
     */
    public void returnLoan(Long id);
}
