package com.sven.bc.serializer;

import java.util.List;

import org.junit.Test;

import com.sven.bc.Constants;
import com.sven.bc.block.AbstactBlock;
import com.sven.bc.block.Transaction;

public class BlockSerializerTest {
	
	
	class Block extends AbstactBlock{
		public Block(int id, List<Transaction> transactions, String previousHash) {
			super(id, transactions, previousHash);
		}
	}
	
	@Test
	public void testSerializer() {
		BlockSerializer s = new BlockSerializer();
		s.serialize(new Block(1, null, Constants.GENESIS_PREV_HASH));
	}

}
