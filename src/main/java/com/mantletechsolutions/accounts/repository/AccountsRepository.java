package com.mantletechsolutions.accounts.repository;

import com.mantletechsolutions.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
