package com.mantletechsolutions.accounts.service;

import com.mantletechsolutions.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
