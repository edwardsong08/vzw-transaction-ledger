package com.example.vzw_transaction_ledger.service;

import com.example.vzw_transaction_ledger.blockchain.Blockchain;
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

    // Autowire the blockchain bean from the application context
    @Autowired
    private Blockchain blockchain;

    // Retrieve all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Retrieve a transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Create a new transaction: block includes transaction id, amount, and user id.
    public Transaction createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        String blockData = "Transaction ID: " + savedTransaction.getId() +
                           ", Amount: " + savedTransaction.getAmount() +
                           ", User ID: " + (savedTransaction.getUser() != null ? savedTransaction.getUser().getId() : "N/A");
        blockchain.addBlock(blockData);
        System.out.println("Blockchain state after creation: " + blockchain.getChain());
        return savedTransaction;
    }

    // Update an existing transaction:
    // Block should include transaction id, previous amount, new amount, and user id.
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(id);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            double previousAmount = transaction.getAmount(); // Capture previous amount

            // Update transaction fields
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTimestamp(transactionDetails.getTimestamp());
            transaction.setUser(transactionDetails.getUser());
            Transaction updatedTransaction = transactionRepository.save(transaction);

            String blockData = "Updated Transaction ID: " + updatedTransaction.getId() +
                               ", User ID: " + (updatedTransaction.getUser() != null ? updatedTransaction.getUser().getId() : "N/A") +
                               ", Previous Amount: " + previousAmount +
                               ", New Amount: " + updatedTransaction.getAmount();
            blockchain.addBlock(blockData);
            System.out.println("Blockchain state after update: " + blockchain.getChain());
            return updatedTransaction;
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Delete a transaction:
    // Block should include transaction id, deleted transaction amount, and user id.
    public void deleteTransaction(Long id) {
        // Retrieve transaction details before deletion
        Optional<Transaction> transactionOpt = transactionRepository.findById(id);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            String blockData = "Deleted Transaction ID: " + id +
                               ", User ID: " + (transaction.getUser() != null ? transaction.getUser().getId() : "N/A") +
                               ", Deleted Amount: " + transaction.getAmount();
            // Delete the transaction from the database
            transactionRepository.deleteById(id);
            // Add block with deletion details
            blockchain.addBlock(blockData);
            System.out.println("Blockchain state after deletion: " + blockchain.getChain());
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Optional: Provide a getter to access the blockchain for debugging or audit purposes
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
