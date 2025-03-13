package com.example.vzw_transaction_ledger.repository;

import com.example.vzw_transaction_ledger.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // You can add custom query methods here if needed.
}
