package com.eakonovalov.cryptocurrency.blockchain;

public class GoldenHashVerifierImpl implements GoldenHashVerifier {

    private int difficulty;

    public GoldenHashVerifierImpl(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean isGoldenHash(byte[] hash) {
        int bytes = difficulty / 2;
        for (int i = 0; i < bytes; i++) {
            if (numberOfLeadingZeros(hash[i]) < 2) {
                return false;
            }
        }

        if (difficulty % 2 == 1) {
            return numberOfLeadingZeros(hash[bytes]) >= 1;
        }

        return true;
    }

    protected int numberOfLeadingZeros(byte b) {
        if (b == 0) return 2;
        if (b >>> 4 == 0) return 1;
        return 0;
    }

}
