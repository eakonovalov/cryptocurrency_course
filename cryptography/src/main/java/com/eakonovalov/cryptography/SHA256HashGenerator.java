package com.eakonovalov.cryptography;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashGenerator implements HashGenerator {

    private MessageDigest md;

    public SHA256HashGenerator() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] generate(String payload) {
        return md.digest(payload.getBytes(StandardCharsets.UTF_8));
    }

    public String asString(byte[] hash) {
        StringBuilder hexadecimalString = new StringBuilder();

        for (byte b : hash) {
            String hexadecimal = Integer.toHexString(0xff & b);
            if (hexadecimal.length() == 1) hexadecimalString.append('0');
            hexadecimalString.append(hexadecimal);
        }

        return hexadecimalString.toString();
    }

}
