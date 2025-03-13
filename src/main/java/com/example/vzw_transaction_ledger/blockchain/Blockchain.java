package com.example.vzwtransactionledger.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // Initialize chain with a genesis block
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("Genesis Block", "0");
    }

    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(data, previousBlock.getHash());
        chain.add(newBlock);
    }

    // Getters and utility methods
}
