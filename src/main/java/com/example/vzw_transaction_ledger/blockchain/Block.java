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

    // Optionally, add getters for other fields if needed.
}
