package com.sven.bc.miner;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.sven.bc.Constants;
import com.sven.bc.block.Block;
import com.sven.bc.blockchain.BlockChain;
import com.sven.bc.exceptions.InconsistentHashException;
import com.sven.bc.hasher.Hasher;

public class Miner {
	
	private Hasher hasher = new Hasher();
	private int reward = 0;
	
	public void mine(Block block, BlockChain chain) {
		String hash = null;
		try {
			while (!goldenHash(hash)) {
				block.incrementNonce();
				hash = hasher.sha256(block);
			}
			block.setHash(hash);
			chain.add(block);
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InconsistentHashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addReward();
	}
	
	private void addReward() {
		this.reward += Constants.MINER_REWARD;
	}

	private boolean goldenHash(String hash) { 
		if (hash == null) return false;
		for (int i = 0; i < Constants.DIFFICULTY; i++ ) {
			if (hash.charAt(i) != '0') {
				return false;
			}
		}
		return true;
	}

	public int getReward() {
		// TODO Auto-generated method stub
		return reward;
	}

}
