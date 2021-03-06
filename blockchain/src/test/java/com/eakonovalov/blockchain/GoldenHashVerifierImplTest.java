package com.eakonovalov.blockchain;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoldenHashVerifierImplTest {

    @Test
    public void isGoldenHash() {
        GoldenHashVerifier verifier = new GoldenHashVerifierImpl(5);

        assertTrue(verifier.isGoldenHash(new byte[]{0, 0, 1}));
        assertFalse(verifier.isGoldenHash(new byte[]{0, 1, 1}));
    }

}
