package com.eakonovalov.merkletree;

import com.eakonovalov.cryptography.SHA256HashGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MerkleTree {

    private List<String> transactions;

    public MerkleTree(List<String> transactions) {
        this.transactions = transactions;
    }

    public String getMerkleRoot() {
        if (transactions == null || transactions.size() == 0) return null;

        if (transactions.size() == 1) return SHA256HashGenerator.generateAsString(transactions.get(0));

        List<List<String>> tree = new ArrayList<>();
        tree.add(new ArrayList<>());

        Iterator<String> itr = transactions.iterator();
        while (itr.hasNext()) {
            String t1 = itr.next();
            String t2 = null;
            if (itr.hasNext()) {
                t2 = itr.next();
            }
            tree.get(0).add(concat(t1, t2 != null ? t2 : t1));

            merge(tree, false);
        }

        merge(tree, true);

        return tree.get(0).get(0);
    }

    private void merge(List<List<String>> tree, boolean processOrphans) {
        for (int depth = 0; depth < tree.size(); depth++) {
            List<String> level = tree.get(depth);
            if (level.size() == 2) {
                if (tree.size() == depth + 1) {
                    tree.add(new ArrayList<>());
                }
                tree.get(depth + 1).add(concat(level.get(0), level.get(1)));
                level.clear();
                if (!processOrphans) {
                    continue;
                }
            } else if (!processOrphans) {
                break;
            }

            if (level.size() == 1 && tree.size() > depth + 1) {
                tree.get(depth + 1).add(concat(level.get(0), level.get(0)));
                level.clear();
            }
            if (level.size() == 0) {
                tree.remove(depth);
                depth--;
            }

        }

    }

    private String concat(String first, String second) {
        return SHA256HashGenerator.generateAsString(first + second);
    }

}
