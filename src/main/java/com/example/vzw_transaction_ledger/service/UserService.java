package com.example.vzw_transaction_ledger.service;

import com.example.vzw_transaction_ledger.model.User;
import com.example.vzw_transaction_ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()){
            User user = userOpt.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // Delete a user by ID with validation to prevent deletion if there are existing transactions
    public void deleteUser(Long id) {
        // Check if the user has any transactions
        long txnCount = userRepository.countTransactionsByUserId(id);
        if (txnCount > 0) {
            throw new RuntimeException("user.delete.error");
        }
        userRepository.deleteById(id);
    }

    // Method to check if an email already exists
    public boolean emailExists(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent();
    }
}
