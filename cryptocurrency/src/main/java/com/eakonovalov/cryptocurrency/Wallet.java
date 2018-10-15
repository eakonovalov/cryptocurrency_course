package com.eakonovalov.cryptocurrency;

import com.eakonovalov.cryptography.CryptographyHelper;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Wallet {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Wallet() {
        KeyPair keyPair = CryptographyHelper.ellipticCurveCrypto();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public double calculateBalance() {
        double balance = 0;

        for (Map.Entry<String, TransactionOutput> item : UTXOStorage.UTXOs.entrySet()) {
            TransactionOutput transactionOutput = item.getValue();
            if (transactionOutput.isMine(publicKey)) {
                balance += transactionOutput.getAmount();
            }
        }

        return balance;
    }

    public Transaction transferMoney(PublicKey receiver, double amount) {
        if (calculateBalance() < amount) {
            throw new NotEnoughOfMoneyException();
        }

        //we store the inputs for the transaction in this array
        List<TransactionInput> inputs = new ArrayList<>();

        //let's find our unspent transactions (the blockchain stores all the UTXOs)
        for (Map.Entry<String, TransactionOutput> item : UTXOStorage.UTXOs.entrySet()) {
            TransactionOutput UTXO = item.getValue();

            if (UTXO.isMine(this.publicKey)) {
                inputs.add(new TransactionInput(UTXO.getId()));
            }
        }

        //let's create the new transaction
        Transaction newTransaction = new Transaction(publicKey, receiver, amount, inputs);
        //the sender signs the transaction
        newTransaction.setSignature(new TransactionVerifierImpl().generateSignature(newTransaction, privateKey));

        return newTransaction;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

}
