package com.sahansha.Loan.repository;

import com.sahansha.Loan.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loans,Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);

}
