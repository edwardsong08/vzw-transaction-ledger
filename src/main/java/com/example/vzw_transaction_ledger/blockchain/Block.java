package com.example.vzwtransactionledger.blockchain;

public class Block {
    private String data;
    private String previousHash;
    private String hash;
    // Additional properties like timestamp, nonce, etc.

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        // Calculate hash (simplified)
        this.hash = Integer.toString((data + previousHash).hashCode());
    }

    // Getters and setters
}
