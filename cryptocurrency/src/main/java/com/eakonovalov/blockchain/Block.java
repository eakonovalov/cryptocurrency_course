package com.eakonovalov.blockchain;

import com.eakonovalov.cryptocurrency.Transaction;
import com.eakonovalov.cryptocurrency.cryptography.CryptographyHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {

    private int id;
    private int nonce;
    private long timeStamp;
    private String hash;
    private String previousHash;
    public List<Transaction> transactions;

    public Block(String previousHash) {
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        generateHash();
    }

    public void generateHash() {
        String dataToHash = Integer.toString(id) + previousHash + Long.toString(timeStamp) + transactions.toString() + Integer.toString(nonce);
        String hashValue = CryptographyHelper.generateHash(dataToHash);
        this.hash = hashValue;
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public String getHash() {
        return this.hash;
    }

    //check if the given transaction is valid or not
    public boolean addTransaction(Transaction transaction) {

        if (transaction == null) return false;

        //if the block is the genesis block we do not process
        if ((!previousHash.equals(Constants.GENESIS_PREV_HASH))) {
            if ((!transaction.verifyTransaction())) {
                System.out.println("Transaction is not valid...");
                return false;
            }
        }

        transactions.add(transaction);
        System.out.println("Transaction is valid and it's added to the block " + this);
        return true;
    }
}
