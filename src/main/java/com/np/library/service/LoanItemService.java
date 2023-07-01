package com.np.library.service;

import com.np.library.domain.LoanItem;

import java.util.List;

public interface LoanItemService {
    public List<LoanItem> getLoanItemsByLoan(Long id);
}
