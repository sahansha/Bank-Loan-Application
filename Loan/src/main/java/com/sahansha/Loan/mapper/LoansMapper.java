package com.sahansha.Loan.mapper;

import com.sahansha.Loan.dto.LoanDTO;
import com.sahansha.Loan.entity.Loans;

public class LoansMapper {

    public static Loans LoanDtoToLoans(LoanDTO loanDTO,Loans loans)
    {
        loans.setLoanNumber(loanDTO.getLoanNumber());
        loans.setLoanType(loanDTO.getLoanType());
        loans.setMobileNumber(loanDTO.getMobileNumber());
        loans.setTotalLoan(loanDTO.getTotalLoan());
        loans.setAmountPaid(loanDTO.getAmountPaid());
        loans.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loans;
    }
    public static LoanDTO LoansToLoanDTO(Loans loans,LoanDTO loanDTO)
    {
        loanDTO.setLoanNumber(loans.getLoanNumber());
        loanDTO.setLoanType(loans.getLoanType());
        loanDTO.setMobileNumber(loans.getMobileNumber());
        loanDTO.setTotalLoan(loans.getTotalLoan());
        loanDTO.setAmountPaid(loans.getAmountPaid());
        loanDTO.setOutstandingAmount(loans.getOutstandingAmount());
        return loanDTO;
    }
}
