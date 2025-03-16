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

    // Create a blockchain instance to record transaction events (creation, update, deletion)
    private Blockchain blockchain = new Blockchain();

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
        // Save the transaction to the database using JPA
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Create a summary string from the saved transaction
        String blockData = "Transaction ID: " + savedTransaction.getId() +
                           ", Amount: " + savedTransaction.getAmount() +
                           ", User ID: " + (savedTransaction.getUser() != null ? savedTransaction.getUser().getId() : "N/A");

        // Add a new block to the blockchain with the transaction summary
        blockchain.addBlock(blockData);

        // Log the blockchain state for debugging purposes
        System.out.println("Blockchain state after creation: " + blockchain.getChain());

        return savedTransaction;
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(id);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setTimestamp(transactionDetails.getTimestamp());
            transaction.setUser(transactionDetails.getUser());
            
            // Save the updated transaction
            Transaction updatedTransaction = transactionRepository.save(transaction);

            // Create a summary string to capture the update details
            String blockData = "Updated Transaction ID: " + updatedTransaction.getId() +
                               ", New Amount: " + updatedTransaction.getAmount() +
                               ", User ID: " + (updatedTransaction.getUser() != null ? updatedTransaction.getUser().getId() : "N/A");

            // Add a new block to the blockchain for the update event
            blockchain.addBlock(blockData);

            // Log the blockchain state after update
            System.out.println("Blockchain state after update: " + blockchain.getChain());

            return updatedTransaction;
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(Long id) {
        // Delete the transaction from the database
        transactionRepository.deleteById(id);

        // Create a summary string for the deletion event
        String blockData = "Deleted Transaction ID: " + id;

        // Add a new block to the blockchain for the deletion event
        blockchain.addBlock(blockData);

        // Log the blockchain state after deletion
        System.out.println("Blockchain state after deletion: " + blockchain.getChain());
    }

    // Optional: Provide a getter to access the blockchain for debugging or audit purposes
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
