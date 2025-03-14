package com.example.vzw_transaction_ledger.repository;

import com.example.vzw_transaction_ledger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom query methods if needed
}
