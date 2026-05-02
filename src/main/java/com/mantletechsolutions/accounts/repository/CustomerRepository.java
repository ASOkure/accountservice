package com.mantletechsolutions.accounts.repository;

import com.mantletechsolutions.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
