package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.model.Transaction;
import com.example.vzw_transaction_ledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionApiController {

    @Autowired
    private TransactionService transactionService;

    // GET /api/transactions - Return a JSON list of all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
       return transactionService.getAllTransactions();
    }

    // GET /api/transactions/{id} - Return a JSON representation of a single transaction
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
       return transactionService.getTransactionById(id).orElse(null);
    }
    
    // Additional endpoints (POST, PUT, DELETE) can be added as needed.
}
