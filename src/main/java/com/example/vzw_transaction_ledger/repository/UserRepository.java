package com.example.vzw_transaction_ledger.repository;

import com.example.vzw_transaction_ledger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;  // Import Optional

public interface UserRepository extends JpaRepository<User, Long> {
    // Add a method to find a user by email
    Optional<User> findByEmail(String email);
}
