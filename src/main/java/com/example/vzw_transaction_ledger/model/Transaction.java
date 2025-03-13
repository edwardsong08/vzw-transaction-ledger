package com.example.vzw_transaction_ledger.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime timestamp;

    // Optional: link transaction to a user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Transaction() {}
    public Transaction(Double amount, LocalDateTime timestamp, User user) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.user = user;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
