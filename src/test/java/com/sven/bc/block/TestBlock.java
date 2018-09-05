package com.sven.bc.block;

import java.util.List;

public class TestBlock extends AbstactBlock{
	
	public TestBlock(int id, List<Transaction> transactions, String previousHash) {
		super(id, transactions, previousHash);
	}
}
