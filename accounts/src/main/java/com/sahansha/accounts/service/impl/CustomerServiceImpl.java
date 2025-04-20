package com.sahansha.accounts.service.impl;

import com.sahansha.accounts.Exception.ResourceNotFoundException;
import com.sahansha.accounts.dto.AccountsDTO;
import com.sahansha.accounts.dto.CardsDto;
import com.sahansha.accounts.dto.CustomerDetailsDTO;
import com.sahansha.accounts.dto.LoanDTO;
import com.sahansha.accounts.entity.Accounts;
import com.sahansha.accounts.entity.Customer;
import com.sahansha.accounts.mapper.AccountsMapper;
import com.sahansha.accounts.mapper.CustomerMapper;
import com.sahansha.accounts.repository.AccountsRepository;
import com.sahansha.accounts.repository.CustomerRepository;
import com.sahansha.accounts.service.ICustomerService;
import com.sahansha.accounts.service.client.CardsClient;
import com.sahansha.accounts.service.client.LoansClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final LoansClient loansClient;
    private final CardsClient cardsClient;
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        try{
            Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                    );
            Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
                    );
            CustomerDetailsDTO customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDTO());
            customerDetailsDto.setAccountsDTO(AccountsMapper.mapToAccountsDto(accounts, new AccountsDTO()));
            ResponseEntity<LoanDTO> loanDTO = loansClient.fetchLoan(mobileNumber);
            ResponseEntity<CardsDto> cardsDto = cardsClient.fetchCardDetails(mobileNumber);
            if(loanDTO!=null)
            {
                customerDetailsDto.setLoansDTO(loanDTO.getBody());
            }
            if (cardsDto != null) {
                customerDetailsDto.setCardsDto(cardsDto.getBody());
            }
            return customerDetailsDto;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
