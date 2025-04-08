package com.sahansha.accounts.service;

import com.sahansha.accounts.dto.CustomerDTO;

public interface IAccountsService {

//    @param customerDTO - CustomerDTO object
//    @return void
    void createAccount(CustomerDTO customerDTO);

//    @param mobileNumber - mobile number of customer
//    @return CustomerDTO
    CustomerDTO fetchAccount(String mobileNumber);

    // @param customerDTO - CustomerDTO object
    // @return boolean
    boolean updateAccount(CustomerDTO customerDTO);

    // @param mobileNumber - mobile number of customer
    // @return boolean
    boolean deleteAccount(String mobileNumber);
}
