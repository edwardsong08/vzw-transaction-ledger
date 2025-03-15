package com.example.vzw_transaction_ledger.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // Create the genesis block
        chain.add(createGenesisBlock());
    }

    // Create the genesis block with fixed previous hash ("0")
    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    // Get the latest block in the chain
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    // Add a new block to the chain
    public void addBlock(String data) {
        Block previousBlock = getLatestBlock();
        Block newBlock = new Block(data, previousBlock.getHash());
        chain.add(newBlock);
    }

    // Optional: Validate the chain's integrity
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);
            // Check if current block's hash is correct
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            // Check if the current block's previous hash matches the previous block's hash
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    // Getter for the blockchain
    public List<Block> getChain() {
        return chain;
    }
}
