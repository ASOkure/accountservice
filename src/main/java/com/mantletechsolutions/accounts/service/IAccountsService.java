package com.mantletechsolutions.accounts.service;

import com.mantletechsolutions.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;


public interface IAccountsService {
    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
}
