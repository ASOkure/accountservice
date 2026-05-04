package com.mantletechsolutions.accounts.impl;

import com.mantletechsolutions.accounts.constants.AccountsConstants;
import com.mantletechsolutions.accounts.dto.CustomerDto;
import com.mantletechsolutions.accounts.entity.Accounts;
import com.mantletechsolutions.accounts.entity.Customer;
import com.mantletechsolutions.accounts.exception.CustomerAlreadyExistsException;
import com.mantletechsolutions.accounts.mapper.CustomerMapper;
import com.mantletechsolutions.accounts.repository.AccountsRepository;
import com.mantletechsolutions.accounts.repository.CustomerRepository;
import com.mantletechsolutions.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
       Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        customer.setCreatedBy("Anonymous");
        return newAccount;
    }

}
