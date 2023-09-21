package com.guilherme.vestidobank.vestidobank.repository;

import com.guilherme.vestidobank.vestidobank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
