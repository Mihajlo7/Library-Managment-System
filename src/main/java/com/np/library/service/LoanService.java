package com.np.library.service;

import com.np.library.domain.Loan;

import java.util.List;

public interface LoanService {
    public void createLoan(Loan loan);
    public void returnLoan(Long id);
}
