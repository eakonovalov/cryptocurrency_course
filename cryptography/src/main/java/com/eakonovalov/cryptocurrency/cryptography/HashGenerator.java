package com.eakonovalov.cryptocurrency.cryptography;

public interface HashGenerator {

    byte[] generate(String payload);

    String asString(byte[] hash);

}
