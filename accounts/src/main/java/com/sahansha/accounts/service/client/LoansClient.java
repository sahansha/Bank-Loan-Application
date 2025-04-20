package com.sahansha.accounts.service.client;

import com.sahansha.accounts.dto.LoanDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans",fallback = LoansClientFallback.class)
public interface LoansClient {

    @GetMapping(value= "/api/v1/loan/fetch")
    public ResponseEntity<LoanDTO> fetchLoan(@RequestParam String mobileNumber);
}
