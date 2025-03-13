package com.example.vzw_transaction_ledger.service;

import com.example.vzw_transaction_ledger.model.Transaction;
import com.example.vzw_transaction_ledger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Retrieve all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Retrieve a transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Create a new transaction
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(id);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTimestamp(transactionDetails.getTimestamp());
            transaction.setUser(transactionDetails.getUser());
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
