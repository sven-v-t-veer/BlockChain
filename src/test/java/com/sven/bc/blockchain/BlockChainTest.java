package com.sven.bc.blockchain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sven.bc.block.TestBlock;
import com.sven.bc.exceptions.InconsistentHashException;

public class BlockChainTest {
	
	
	@Test
	public void blockChainTest() {
		TestBlock b1 = new TestBlock(0, null, "0");
		BlockChain chain = new BlockChain();
		try {
			chain.add(b1);
			assertEquals(chain.size(), 1);
			b1.setHash("0");
			chain.add(b1);
			assertEquals(chain.size(), 2);
			chain.add(b1);
			chain.add(b1);
			assertEquals(chain.size(), 4);
		} catch (InconsistentHashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
