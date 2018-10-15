package com.eakonovalov.cryptocurrency;

import com.eakonovalov.blockchain.Block;
import com.eakonovalov.blockchain.BlockChain;
import com.eakonovalov.blockchain.Constants;
import com.eakonovalov.blockchain.Miner;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class WalletTest {

    @Test
    public void transferMoney() {
        //the security provider is Bouncy Castle: more flexible than JCE
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        //create wallets + blockchain + the single miner in the network
        Wallet userA = new Wallet();
        Wallet userB = new Wallet();
        Wallet lender = new Wallet();
        BlockChain blockChain = new BlockChain(2);
        Miner miner = new Miner(blockChain);
        TransactionVerifierImpl verifier = new TransactionVerifierImpl();

        //create genesis transaction that sends 500 coins to userA:
        Transaction genesisTransaction = new Transaction(lender.getPublicKey(), userA.getPublicKey(), new BigDecimal(500), null);
        genesisTransaction.setSignature(verifier.generateSignature(genesisTransaction, lender.getPrivateKey()));
        genesisTransaction.setId("0");
        genesisTransaction.getOutputs().add(new TransactionOutput(genesisTransaction.getReceiver(), genesisTransaction.getAmount(), genesisTransaction.getId()));
        UTXOStorage.UTXOs.put(genesisTransaction.getOutputs().get(0).getId(), genesisTransaction.getOutputs().get(0));

        System.out.println("Constructing the genesis block...");
        Block genesis = new Block(0, genesisTransaction.toString(), Constants.GENESIS_HASH);
        miner.mine(genesis);

        System.out.println("\nuserA's balance is: " + userA.calculateBalance());
        System.out.println("\nuserA tries to send money (120 coins) to userB...");

        List<Transaction> transactions = new ArrayList<>();
        try {
            Transaction t = userA.transferMoney(userB.getPublicKey(), new BigDecimal(120));
            if (verifier.verifyTransaction(t)) {
                transactions.add(t);
            }
        } catch (NotEnoughOfMoneyException e) {
            System.out.println("Invalid transaction because of not enough money...");
        }

        Block block1 = new Block(1, transactions.toString(), genesis.getHash());
        miner.mine(block1);

        System.out.println("\nuserA's balance is: " + userA.calculateBalance());
        System.out.println("userB's balance is: " + userB.calculateBalance());

        System.out.println("\nuserA sends more funds (600) than it has...");
        transactions = new ArrayList<>();
        try {
            Transaction t = userA.transferMoney(userB.getPublicKey(), new BigDecimal(600));
            if (t != null && verifier.verifyTransaction(t)) {
                transactions.add(t);
            }
        } catch (NotEnoughOfMoneyException e) {
            System.out.println("Invalid transaction because of not enough money...");
        }

        Block block2 = new Block(2, transactions.toString(), block1.getHash());
        miner.mine(block2);

        System.out.println("\nuserA's balance is: " + userA.calculateBalance());
        System.out.println("userB's balance is: " + userB.calculateBalance());

        System.out.println("\nuserB is attempting to send funds (110) to userA...");
        transactions = new ArrayList<>();
        try {
            Transaction t = userB.transferMoney(userA.getPublicKey(), new BigDecimal(110));
            if (verifier.verifyTransaction(t)) {
                transactions.add(t);
            }
        } catch (NotEnoughOfMoneyException e) {
            System.out.println("Invalid transaction because of not enough money...");
        }
        Block block3 = new Block(3, transactions.toString(), block2.getHash());
        miner.mine(block3);

        System.out.println("\nuserA's balance is: " + userA.calculateBalance());
        System.out.println("userB's balance is: " + userB.calculateBalance());

        System.out.println("\nMiner's reward: " + miner.getReward());

    }

}
