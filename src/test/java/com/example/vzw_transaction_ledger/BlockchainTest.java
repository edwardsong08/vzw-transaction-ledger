package com.example.vzw_transaction_ledger;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.vzw_transaction_ledger.blockchain.Blockchain;
import com.example.vzw_transaction_ledger.blockchain.Block;


@SpringBootTest
public class BlockchainTest {

    @Test
    public void testBlockchainValidity() {
        // Create a new blockchain
        Blockchain blockchain = new Blockchain();
        
        // Add a few blocks with sample data
        blockchain.addBlock("First transaction data");
        blockchain.addBlock("Second transaction data");
        blockchain.addBlock("Third transaction data");
        
        // Print out block details for debugging purposes
        for (Block block : blockchain.getChain()) {
            System.out.println("Block hash: " + block.getHash());
            System.out.println("Previous hash: " + block.getPreviousHash());
            System.out.println("Data: " + block.getData());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("-----------------------------");
        }
        
        // Assert that the blockchain is valid
        assertThat(blockchain.isChainValid()).isTrue();
    }
}
