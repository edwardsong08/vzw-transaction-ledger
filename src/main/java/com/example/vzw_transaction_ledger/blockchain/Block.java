package com.example.vzw_transaction_ledger.blockchain;

public class Block {
    private String data;
    private String previousHash;
    private String hash;
    // Optionally, add timestamp, nonce, etc.

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        // Simplified hash calculation (for demonstration)
        this.hash = Integer.toString((data + previousHash).hashCode());
    }

    // Getter for the hash field
    public String getHash() {
        return this.hash;
    }

    // Getter for the data field
    public String getData() {
        return this.data;
    }

    // Getter for the previousHash field
    public String getPreviousHash() {
        return this.previousHash;
    }
}
