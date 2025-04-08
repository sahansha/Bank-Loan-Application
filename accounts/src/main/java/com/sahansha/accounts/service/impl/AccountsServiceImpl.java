package com.sahansha.accounts.service.impl;

import com.sahansha.accounts.Exception.CustomerAlreadyExists;
import com.sahansha.accounts.Exception.ResourceNotFoundException;
import com.sahansha.accounts.constants.AccountsConstants;
import com.sahansha.accounts.dto.AccountsDTO;
import com.sahansha.accounts.dto.CustomerDTO;
import com.sahansha.accounts.entity.Accounts;
import com.sahansha.accounts.entity.Customer;
import com.sahansha.accounts.mapper.AccountsMapper;
import com.sahansha.accounts.mapper.CustomerMapper;
import com.sahansha.accounts.repository.AccountsRepository;
import com.sahansha.accounts.repository.CustomerRepository;
import com.sahansha.accounts.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public void createAccount(CustomerDTO customerDTO) {
        try{
            Customer customer= CustomerMapper.mapToCustomer(customerDTO, new Customer());
            Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
            if (customerOptional.isPresent()) {
                throw new CustomerAlreadyExists
                        (String.format("Customer already registered with mobile number %s"
                                ,customerDTO.getMobileNumber()));
            }
            customer.setCreatedAt(LocalDateTime.now());
            customer.setCreatedBy("Anonymous");
            Customer savedCustomer = customerRepository.save(customer);
            Accounts createdAccounts = accountsRepository.save(createNewAccount(savedCustomer));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        Long randomNumber = (long) (Math.random() * 1000000000);
        accounts.setAccountNumber(randomNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedBy("Anonymous");
        accounts.setCreatedAt(LocalDateTime.now());
        return accounts;

    }
//        @param mobileNumber - mobile number of customer
//    @return CustomerDTO
    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        try{
            Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                    );
            Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
                    );
            CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
            AccountsDTO accountsDTO= AccountsMapper.mapToAccountsDto(accounts, new AccountsDTO());
            customerDTO.setAccountsDTO(accountsDTO);
            return customerDTO;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // @param customerDTO - CustomerDTO object
    // @return boolean
    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated=false;
        try{
            AccountsDTO accountsDTO=customerDTO.getAccountsDTO();
            if(accountsDTO!=null)    {
                Accounts accounts=accountsRepository.findById(accountsDTO.getAccountNumber())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Account","accountNumber",accountsDTO.getAccountNumber().toString())
                        );
                AccountsMapper.mapToAccounts(accountsDTO,accounts);
                accounts.setUpdatedAt(LocalDateTime.now());
                accounts.setUpdatedBy("Anonymous");
                accountsRepository.save(accounts);
                Customer customer=customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                        () -> new ResourceNotFoundException("Customer",
                                "customerId",accounts.getCustomerId().toString()));
                CustomerMapper.mapToCustomer(customerDTO,customer);
                customer.setUpdatedAt(LocalDateTime.now());
                customer.setUpdatedBy("Anonymous");
                customerRepository.save(customer);
                isUpdated=true;
            }
            return isUpdated;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // @param mobileNumber - mobile number of customer
    // @return boolean
    @Override
    public boolean deleteAccount(String mobileNumber) {
        try{
            Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                    );
            accountsRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.delete(customer);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}