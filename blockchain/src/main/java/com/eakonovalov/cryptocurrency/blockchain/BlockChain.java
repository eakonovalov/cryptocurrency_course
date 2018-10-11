package com.eakonovalov.cryptocurrency.blockchain;

import java.util.LinkedList;

public class BlockChain {

    private LinkedList<Block> chain = new LinkedList<>();

    private int difficulty;

    public BlockChain(int difficulty) {
        this.difficulty = difficulty;
    }

    public void addBlock(Block block) {
        chain.add(block);
    }

    public int size() {
        return chain.size();
    }

    public Block getLast() {
        return chain.peekLast();
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("BlockChain{\n");
        for(Block b : chain) {
            result.append(b).append("\n");
        }
        result.append("}");

        return result.toString();
    }

}
