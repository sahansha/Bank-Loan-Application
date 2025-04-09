package com.sahansha.Loan.service.Impl;

import com.sahansha.Loan.constants.LoansConstants;
import com.sahansha.Loan.dto.LoanDTO;
import com.sahansha.Loan.entity.Loans;
import com.sahansha.Loan.exception.LoanAlreadyExists;
import com.sahansha.Loan.exception.ResourceNotFoundException;
import com.sahansha.Loan.mapper.LoansMapper;
import com.sahansha.Loan.repository.LoanRepository;
import com.sahansha.Loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        try{
            Optional<Loans> existingLoan=loanRepository.findByMobileNumber(mobileNumber);
            if(existingLoan.isPresent())
            {
                throw new LoanAlreadyExists(String.format("Loan already exits with this %s mobile number",mobileNumber));
            }
            loanRepository.save(createNewLoan(mobileNumber));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }



    private Loans createNewLoan(String mobileNumber)
    {
        long newLoanNumber=(long)(Math.random()* 1000000000);
        return Loans.builder()
                .loanNumber(Long.toString(newLoanNumber))
                .loanType(LoansConstants.HOME_LOAN)
                .totalLoan(LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid(0L)
                .outstandingAmount(LoansConstants.NEW_LOAN_LIMIT)
                .mobileNumber(mobileNumber)
                .build();
    }

    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        try{
            Loans loans=loanRepository.findByMobileNumber(mobileNumber).
            orElseThrow(() -> new ResourceNotFoundException("Loan","mobileNumber",mobileNumber));
            return LoansMapper.LoansToLoanDTO(loans,new LoanDTO());
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateLoan(LoanDTO loanDTO) {
        try {
            Loans loans = loanRepository.findByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDTO.getLoanNumber()));
            LoansMapper.LoanDtoToLoans(loanDTO, loans);
            loanRepository.save(loans);
            return  true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        try{
            Loans loans = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
            );
            loanRepository.deleteById(loans.getLoanId());
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
