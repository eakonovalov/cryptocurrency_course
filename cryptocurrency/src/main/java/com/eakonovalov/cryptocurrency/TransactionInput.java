package com.eakonovalov.cryptocurrency;


public class TransactionInput {

    //every input has an output. This id is the transactionId of the TransactionOutput
    private String transactionOutputId;
    //this is the unspent transaction output
    private TransactionOutput utxo;

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public String getTransactionOutputId() {
        return transactionOutputId;
    }

    public void setTransactionOutputId(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }

    public TransactionOutput getUtxo() {
        return utxo;
    }

    public void setUtxo(TransactionOutput utxo) {
        this.utxo = utxo;
    }

}
