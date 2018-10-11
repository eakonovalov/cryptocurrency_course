package com.eakonovalov.cryptocurrency.merkletree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MerkleTreeTest {

    @Test
    public void getMerkleRoot() {
        List<String> transactions = new ArrayList<>();
        transactions.add("00");
        assertMerkleRoot(transactions);
        transactions.add("11");
        assertMerkleRoot(transactions);
        transactions.add("22");
        assertMerkleRoot(transactions);
        transactions.add("33");
        assertMerkleRoot(transactions);
        transactions.add("44");
        assertMerkleRoot(transactions);
        transactions.add("55");
        assertMerkleRoot(transactions);
        transactions.add("66");
        assertMerkleRoot(transactions);
        transactions.add("77");
        assertMerkleRoot(transactions);
        transactions.add("88");
        assertMerkleRoot(transactions);
        transactions.add("99");
        assertMerkleRoot(transactions);
        transactions.add("aa");
        assertMerkleRoot(transactions);
        transactions.add("bb");
        assertMerkleRoot(transactions);
        transactions.add("cc");
        assertMerkleRoot(transactions);
        transactions.add("dd");
        assertMerkleRoot(transactions);
        transactions.add("ee");
        assertMerkleRoot(transactions);
        transactions.add("ff");
        assertMerkleRoot(transactions);
    }

    private void assertMerkleRoot(List<String> transactions) {
        MerkleTreeReference merkleTreeReference = new MerkleTreeReference(transactions);
        MerkleTree merkleTree = new MerkleTree(transactions);
        assertEquals(merkleTreeReference.getMerkeRoot(), merkleTree.getMerkleRoot());
    }

}
