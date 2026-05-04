package com.mantletechsolutions.accounts.repository;

import com.mantletechsolutions.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {


    Optional<Accounts> findByCustomerId(Long customerId);
}
