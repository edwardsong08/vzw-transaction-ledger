package com.example.vzw_transaction_ledger.controller;

import com.example.vzw_transaction_ledger.blockchain.Blockchain;
import com.example.vzw_transaction_ledger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockchainApiController {

    @Autowired
    private TransactionService transactionService;

    // Expose the blockchain simulation as raw JSON.
    @GetMapping("/api/blockchain")
    public Blockchain getBlockchain() {
        return transactionService.getBlockchain();
    }
}
