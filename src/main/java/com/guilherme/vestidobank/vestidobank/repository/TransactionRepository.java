package com.guilherme.vestidobank.vestidobank.repository;

import com.guilherme.vestidobank.vestidobank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
