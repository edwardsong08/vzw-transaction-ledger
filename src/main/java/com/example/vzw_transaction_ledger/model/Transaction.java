package com.example.vzwtransactionledger.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime timestamp;

    // You might also link a transaction to a User, for example:
    // @ManyToOne
    // private User user;

    // Constructors, getters, setters, etc.
}
