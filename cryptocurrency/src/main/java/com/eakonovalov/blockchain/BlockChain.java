package com.eakonovalov.blockchain;

import com.eakonovalov.cryptocurrency.TransactionOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlockChain {

    public static ArrayList<Block> blockChain;
    public static Map<String, TransactionOutput> UTXOs;

    public BlockChain() {
        BlockChain.UTXOs = new HashMap<String, TransactionOutput>();
        BlockChain.blockChain = new ArrayList<>();
    }

    public void addBlock(Block block) {
        BlockChain.blockChain.add(block);
    }

    public int size() {
        return BlockChain.blockChain.size();
    }

    @Override
    public String toString() {
        StringBuilder blockChain = new StringBuilder();

        for (Block block : BlockChain.blockChain)
            blockChain.append(block.toString()).append("\n");

        return blockChain.toString();
    }

}
