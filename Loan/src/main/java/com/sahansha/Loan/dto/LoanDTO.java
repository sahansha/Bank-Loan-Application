package com.sahansha.Loan.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanDTO {

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Long totalLoan;

    private Long amountPaid;

    private Long outstandingAmount;

}
