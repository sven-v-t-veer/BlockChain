package com.sven.bc.block;

import java.util.List;

public class TestBlock extends Block{
	
	public TestBlock(int id, List<Transaction> transactions, String previousHash) {
		super(id, transactions, previousHash);
	}


}
