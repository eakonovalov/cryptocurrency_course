package com.eakonovalov.cryptocurrency;

import com.eakonovalov.cryptography.SHA256HashGenerator;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    //id of the transaction is a hash
    private String id;
    //we use PublicKeys to reference the sender or receiver
    private PublicKey sender;
    private PublicKey receiver;
    //amount of coins the transaction sends to the receiver from the sender
    private double amount;
    //make sure the transaction is signed to prevent anyone else from spending the coins
    private byte[] signature;

    //every transaction has inputs and outputs
    private List<TransactionInput> inputs;
    private List<TransactionOutput> outputs;

    // Constructor:
    public Transaction(PublicKey sender, PublicKey receiver, double amount, List<TransactionInput> inputs) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.inputs = inputs;
        this.id = SHA256HashGenerator.generateAsString(sender.toString() + receiver.toString() + Double.toString(amount));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PublicKey getSender() {
        return sender;
    }

    public void setSender(PublicKey sender) {
        this.sender = sender;
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

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public List<TransactionInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<TransactionInput> inputs) {
        this.inputs = inputs;
    }

    public List<TransactionOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<TransactionOutput> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", amount=" + amount +
                '}';
    }

}
