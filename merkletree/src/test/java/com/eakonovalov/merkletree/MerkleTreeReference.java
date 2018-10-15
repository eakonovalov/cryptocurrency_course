package com.eakonovalov.merkletree;

import com.eakonovalov.cryptography.HashGenerator;
import com.eakonovalov.cryptography.SHA256HashGenerator;

import java.util.ArrayList;
import java.util.List;

public class MerkleTreeReference {

    //we store the transactions in this list
    private List<String> transactions;

    private HashGenerator hg = new SHA256HashGenerator();

    public MerkleTreeReference(List<String> transactions) {
        this.transactions = new ArrayList<>(transactions);
    }

    //the root is in this list in the end
    public String getMerkeRoot() {
        if(transactions == null || transactions.size() == 0) return null;
        if(transactions.size() == 1) {
            transactions.add(hg.asString(hg.generate(transactions.remove(0))));
        }
        return construct(this.transactions).get(0);
    }

    //it is a recursive function that keeps merging the
    //neighboring hashes (index i and i+1)
    private List<String> construct(List<String> transactions) {

        //base case for recursive method calls
        if (transactions.size() == 1) return transactions;

        //it contains fewer and fewer items in every iteration
        List<String> updatedList = new ArrayList<>();

        //merging the neighboring items
        for (int i = 0; i < transactions.size() - 1; i += 2)
            updatedList.add(mergeHash(transactions.get(i), transactions.get(i + 1)));

        //if the number of transactions is odd: the last item is hashed with itself
        if (transactions.size() % 2 == 1)
            updatedList.add(mergeHash(transactions.get(transactions.size() - 1), transactions.get(transactions.size() - 1)));

        //recursive method call
        return construct(updatedList);
    }

    //concatenate two strings and hash it with SHA256
    private String mergeHash(String hash1, String hash2) {
        String mergedHash = hash1 + hash2;
        return hg.asString(hg.generate(mergedHash));
    }

}
