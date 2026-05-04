package com.mantletechsolutions.accounts.service;

import com.mantletechsolutions.accounts.dto.CustomerDto;



public interface IAccountsService {
    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount (String mobileNumber);
}
