package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.User;
import com.example.vzw_transaction_ledger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    private UserService userService;

    // GET /api/users - Return a JSON list of all users
    @GetMapping
    public List<User> getAllUsers() {
       return userService.getAllUsers();
    }

    // GET /api/users/{id} - Return a JSON representation of a single user
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
       return userService.getUserById(id).orElse(null);
    }
    
    // You can add additional API endpoints (e.g., POST, PUT, DELETE) if needed
}
