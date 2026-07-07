package com.mantletechsolutions.accounts.service.impl;

import com.mantletechsolutions.accounts.dto.AccountsDto;
import com.mantletechsolutions.accounts.dto.CardsDto;
import com.mantletechsolutions.accounts.dto.CustomerDetailsDto;
import com.mantletechsolutions.accounts.dto.LoansDto;
import com.mantletechsolutions.accounts.entity.Accounts;
import com.mantletechsolutions.accounts.entity.Customer;
import com.mantletechsolutions.accounts.exception.ResourceNotFoundException;
import com.mantletechsolutions.accounts.mapper.AccountsMapper;
import com.mantletechsolutions.accounts.mapper.CustomerMapper;
import com.mantletechsolutions.accounts.repository.AccountsRepository;
import com.mantletechsolutions.accounts.repository.CustomerRepository;
import com.mantletechsolutions.accounts.service.ICustomersService;
import com.mantletechsolutions.accounts.service.client.CardsFeignClient;
import com.mantletechsolutions.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }


}
