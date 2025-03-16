package com.example.vzw_transaction_ledger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ensure the amount is provided and greater than zero.
    @NotNull(message = "{transaction.amount.notNull}")
    @Min(value = 1, message = "{transaction.amount.min}")
    private Double amount;

    // Ensure the timestamp is provided.
    @NotNull(message = "{transaction.timestamp.notNull}")
    private LocalDateTime timestamp;

    // Ensure the transaction is associated with a user.
    @NotNull(message = "{transaction.user.notNull}")
    @ManyToOne
    @JoinColumn(name = "user_id")  // This will reference the "users" table.
    private User user;

    // Constructors
    public Transaction() {}

    public Transaction(Double amount, LocalDateTime timestamp, User user) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.user = user;
    }

    // Getters and setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }
    public Double getAmount() { 
        return amount; 
    }
    public void setAmount(Double amount) { 
        this.amount = amount; 
    }
    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }
    public void setTimestamp(LocalDateTime timestamp) { 
        this.timestamp = timestamp; 
    }
    public User getUser() { 
        return user; 
    }
    public void setUser(User user) { 
        this.user = user; 
    }
}
