package com.example.vzw_transaction_ledger.repository;

import com.example.vzw_transaction_ledger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import java.util.Optional;  // Import Optional

public interface UserRepository extends JpaRepository<User, Long> {
    // Add a method to find a user by email
    Optional<User> findByEmail(String email);

    // Count transactions associated with a user
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.user.id = :userId")
    long countTransactionsByUserId(@Param("userId") Long userId);
}
