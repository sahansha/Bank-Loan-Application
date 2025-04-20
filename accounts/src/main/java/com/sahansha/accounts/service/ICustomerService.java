package com.sahansha.accounts.service;

import com.sahansha.accounts.dto.CustomerDetailsDTO;

public interface ICustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
