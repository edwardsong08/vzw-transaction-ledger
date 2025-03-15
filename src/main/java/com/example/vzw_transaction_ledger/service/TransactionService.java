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

    // Create a blockchain instance to record transaction summaries.
    // This blockchain is our simulated blockchain that will record each transaction as a new block.
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

        // Create a summary string from the saved transaction.
        // For example, we record the transaction ID, amount, and associated user ID.
        String blockData = "Transaction ID: " + savedTransaction.getId() +
                           ", Amount: " + savedTransaction.getAmount() +
                           ", User ID: " + (savedTransaction.getUser() != null ? savedTransaction.getUser().getId() : "N/A");

        // Add a new block to the blockchain with the transaction summary
        blockchain.addBlock(blockData);

        // (Optional) Log the current blockchain state for debugging
        System.out.println("Blockchain state: " + blockchain.getChain());

        // Return the saved transaction as usual
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
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction not found with id " + id);
        }
    }

    // Delete a transaction by ID
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Optional: Getter method to access the blockchain (for testing or auditing)
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
