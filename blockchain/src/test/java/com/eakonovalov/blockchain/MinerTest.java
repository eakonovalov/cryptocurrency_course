package com.eakonovalov.blockchain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinerTest {

    @Test
    public void mine() {
        BlockChain blockChain = new BlockChain(5);
        Miner miner = new Miner(blockChain);

        Block block = new Block(0, "", Constants.GENESIS_HASH);
        miner.mine(block);

        block = new Block(1, "transaction1", blockChain.getLast().getHash());
        miner.mine(block);

        block = new Block(2, "transaction2", blockChain.getLast().getHash());
        miner.mine(block);

        assertEquals(blockChain.size(), 3);

    }

}
