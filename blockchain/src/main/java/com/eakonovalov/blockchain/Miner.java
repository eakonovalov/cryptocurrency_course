package com.eakonovalov.blockchain;

import com.eakonovalov.cryptography.HashGenerator;
import com.eakonovalov.cryptography.SHA256HashGenerator;

import java.math.BigDecimal;

public class Miner {

    private BlockChain blockChain;
    private HashGenerator generator = new SHA256HashGenerator();
    private GoldenHashVerifier verifier;

    private BigDecimal reward = BigDecimal.ZERO;

    public Miner(BlockChain blockChain) {
        this.blockChain = blockChain;
        verifier = new GoldenHashVerifierImpl(blockChain.getDifficulty());
    }

    public void mine(Block block) {
        byte[] hash;
        while (!verifier.isGoldenHash(hash = generator.generate(block.getPayload() + block.getNounce()))) {
            block.getNounce().incrementAndGet();
        }

        block.setHash(generator.asString(hash));
        blockChain.addBlock(block);

        reward = reward.add(Constants.MINER_REWARD);

    }

    public BigDecimal getReward() {
        return reward;
    }

}
