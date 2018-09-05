package com.sven.bc.miner;

import org.junit.Test;

import com.sven.bc.Constants;
import com.sven.bc.block.Block;
import com.sven.bc.block.TestBlock;
import com.sven.bc.blockchain.BlockChain;

public class MinerTest {
	
	@Test
	public void minerTest() {
		Miner miner = new Miner();
		Block initial = new TestBlock(0, null, Constants.GENESIS_PREV_HASH);
		BlockChain chain = new BlockChain();
		miner.mine(initial, chain);
		Block one = new TestBlock(1, null, chain.last().getHash());
		miner.mine(one, chain);
		Block two = new TestBlock(2, null, chain.last().getHash());
		miner.mine(two, chain);
		Block three = new TestBlock(3, null, chain.last().getHash());
		miner.mine(three, chain);
		System.out.println("Blockchain size: " + chain.size());
		System.out.println("Last block: " + chain.last().toString());
		System.out.println("Reward: " + miner.getReward());
		for (int i = 0; i < chain.size(); i++ ) {
			Block block = chain.get(i);
			System.out.println("Block: " + block.getId() + " " + block);
			System.out.println("Previous: " + block.getPreviousHash());
			System.out.println("Hash: " + block.getHash());
		}
	}

}
