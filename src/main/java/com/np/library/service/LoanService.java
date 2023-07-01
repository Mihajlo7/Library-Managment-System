package com.np.library.service;

import com.np.library.domain.Loan;

import java.util.List;

public interface LoanService {
    public void createLoan(Loan loan);
    public List<Loan> getLoansByLibraryUsers(Long id);
    public void returnLoan(Long id);
}
