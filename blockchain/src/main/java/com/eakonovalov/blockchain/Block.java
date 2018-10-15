package com.eakonovalov.blockchain;

import java.util.concurrent.atomic.AtomicLong;

public class Block {

    private long id;
    private String block;
    private long timestamp = System.currentTimeMillis();

    private String previousHash;
    private String hash;
    private AtomicLong nounce = new AtomicLong(0);

    private String payload;

    public Block(long id, String block, String previousHash) {
        this.id = id;
        this.block = block;
        this.previousHash = previousHash;
        payload = Long.toString(id) + "|" + block + "|" + timestamp + "|" + previousHash + "|";
    }

    public long getId() {
        return id;
    }

    public String getBlock() {
        return block;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getPayload() {
        return payload;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public AtomicLong getNounce() {
        return nounce;
    }

    @Override
    public String toString() {
        return "Block{" + payload + "|" + nounce + "}";
    }

}
