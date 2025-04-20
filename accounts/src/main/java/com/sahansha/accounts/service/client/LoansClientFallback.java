package com.sahansha.accounts.service.client;

import com.sahansha.accounts.dto.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansClientFallback implements LoansClient {
    @Override
    public ResponseEntity<LoanDTO> fetchLoan(String mobileNumber) {
        return null;
    }
}
