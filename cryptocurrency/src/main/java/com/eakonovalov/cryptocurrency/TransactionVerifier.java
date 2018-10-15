package com.eakonovalov.cryptocurrency;

import java.security.PrivateKey;

public interface TransactionVerifier {

    boolean verifyTransaction(Transaction t);

    void generateSignature(PrivateKey privateKey);

}
