package com.sahansha.Loan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanAlreadyExists extends RuntimeException{
    public LoanAlreadyExists(String message) {
        super(message);
    }
}
