package com.eakonovalov.cryptography;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashGenerator {

    private static final MessageDigest MD;

    static {
        try {
            MD = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] generate(String data) {
        return MD.digest(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String generateAsString(String data) {
        return asString(generate(data));
    }

    public static String asString(byte[] hash) {
        StringBuilder hexadecimalString = new StringBuilder();

        for (byte b : hash) {
            String hexadecimal = Integer.toHexString(0xff & b);
            if (hexadecimal.length() == 1) hexadecimalString.append('0');
            hexadecimalString.append(hexadecimal);
        }

        return hexadecimalString.toString();
    }

}
