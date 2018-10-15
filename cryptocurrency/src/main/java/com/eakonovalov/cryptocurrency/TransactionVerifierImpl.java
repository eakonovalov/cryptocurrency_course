package com.eakonovalov.cryptocurrency;

import com.eakonovalov.cryptography.CryptographyHelper;

import java.math.BigDecimal;
import java.security.PrivateKey;

public class TransactionVerifierImpl {

    public boolean verifyTransaction(Transaction t) {
        if (!verifySignature(t)) {
            System.out.println("Invalid t because of invalid signature...");
            return false;
        }

        //let's gather unspent transactions (we have to consider the inputs)
        for (TransactionInput transactionInput : t.getInputs()) {
            transactionInput.setUtxo(UTXOStorage.UTXOs.get(transactionInput.getTransactionOutputId()));
        }

        //transactions have 2 part: send an amount to the receiver + send the (balance-amount) back to the sender
        t.getOutputs().add(new TransactionOutput(t.getReceiver(), t.getAmount(), t.getId())); //send value to recipient
        t.getOutputs().add(new TransactionOutput(t.getSender(), getInputsSum(t).subtract(t.getAmount()), t.getId())); //send the left over 'change' back to sender

        //the outputs will be inputs for other transactions (so put them in the blockchain's UTXOs)
        for (TransactionOutput transactionOutput : t.getOutputs()) {
            UTXOStorage.UTXOs.put(transactionOutput.getId(), transactionOutput);
        }

        //remove t inputs from blockchain's UTXOs list because they've been spent
        for (TransactionInput transactionInput : t.getInputs()) {
            if (transactionInput.getUtxo() != null)
                UTXOStorage.UTXOs.remove(transactionInput.getUtxo().getId());
        }

        return true;
    }

    public byte[] generateSignature(Transaction t, PrivateKey privateKey) {
        String data = t.getSender().toString() + t.getReceiver().toString() + t.getAmount().toPlainString();
        return CryptographyHelper.applyECDSASignature(privateKey, data);
    }

    private boolean verifySignature(Transaction t) {
        String data = t.getSender().toString() + t.getReceiver().toString() + t.getAmount().toPlainString();
        return CryptographyHelper.verifyECDSASignature(t.getSender(), data, t.getSignature());
    }

    private BigDecimal getInputsSum(Transaction t) {
        BigDecimal sum = new BigDecimal(0);

        for (TransactionInput transactionInput : t.getInputs()) {
            if (transactionInput.getUtxo() != null) {
                sum = sum.add(transactionInput.getUtxo().getAmount());
            }
        }

        return sum;
    }

}
