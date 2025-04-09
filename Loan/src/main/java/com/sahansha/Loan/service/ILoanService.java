package com.sahansha.Loan.service;

import com.sahansha.Loan.dto.LoanDTO;
import com.sahansha.Loan.entity.Loans;

public interface ILoanService {

    //@Param mobile number
    void createLoan(String mobileNumber);

    //@Param mobile number
    //@return Loans

    LoanDTO fetchLoan(String mobileNumber);

    /**
     *
    // * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoanDTO loanDTO);

    /**
     *
    // * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);
}
