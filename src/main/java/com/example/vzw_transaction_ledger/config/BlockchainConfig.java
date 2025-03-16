package com.example.vzw_transaction_ledger.config;

import com.example.vzw_transaction_ledger.blockchain.Blockchain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockchainConfig {

    @Bean
    public Blockchain blockchain() {
        return new Blockchain();
    }
}
