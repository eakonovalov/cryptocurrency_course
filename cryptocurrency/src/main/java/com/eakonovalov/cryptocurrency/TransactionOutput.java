package com.eakonovalov.cryptocurrency;


import com.eakonovalov.cryptography.SHA256HashGenerator;

import java.math.BigDecimal;
import java.security.PublicKey;

public class TransactionOutput {

    //identifier of the transaction output
    private String id;
    //transaction id of the parent (so the transaction it was created in)
    private String parentTransactionId;
    //the new owner of the coin
    private PublicKey receiver;
    //amount of coins
    private BigDecimal amount;

    public TransactionOutput(PublicKey receiver, BigDecimal amount, String parentTransactionId) {
        this.receiver = receiver;
        this.amount = amount;
        this.parentTransactionId = parentTransactionId;
        this.id = SHA256HashGenerator.generateAsString(receiver.toString() + amount.toPlainString() + parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
        return publicKey.equals(receiver);
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
