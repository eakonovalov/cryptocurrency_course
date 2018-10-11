package com.eakonovalov.cryptocurrency;


import com.eakonovalov.cryptocurrency.cryptography.HashGenerator;
import com.eakonovalov.cryptocurrency.cryptography.SHA256HashGenerator;

import java.security.PublicKey;

public class TransactionOutput {

    //identifier of the transaction output
    private String id;
    //transaction id of the parent (so the transaction it was created in)
    private String parentTransactionId;
    //the new owner of the coin
    private PublicKey receiver;
    //amount of coins
    private double amount;

    private HashGenerator hg = new SHA256HashGenerator();

    public TransactionOutput(PublicKey receiver, double amount, String parentTransactionId) {
        this.receiver = receiver;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        generateId();
    }

    private void generateId() {
        this.id = hg.asString(hg.generate(receiver.toString() + Double.toString(amount) + parentTransactionId));
    }

    public boolean isMine(PublicKey publicKey) {
        return publicKey == receiver;
    }

    public String getId() {
        return id;
    }

    public String getParentTransactionId() {
        return parentTransactionId;
    }

    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public void setReceiver(PublicKey receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
