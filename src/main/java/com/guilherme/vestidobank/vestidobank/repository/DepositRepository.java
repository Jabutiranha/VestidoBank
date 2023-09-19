package com.guilherme.vestidobank.vestidobank.repository;

import com.guilherme.vestidobank.vestidobank.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
