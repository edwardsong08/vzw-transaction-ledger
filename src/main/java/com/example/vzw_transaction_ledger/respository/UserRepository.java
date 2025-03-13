package com.example.vzwtransactionledger.repository;

import com.example.vzwtransactionledger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom query methods if needed
}
