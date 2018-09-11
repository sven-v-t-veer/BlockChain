package com.sven.bc.hasher;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.sven.bc.anotations.SerializedClass;
import com.sven.bc.block.Block;
import com.sven.bc.block.Transaction;
import com.sven.bc.serializer.BasicSerializer;
import com.sven.bc.serializer.Serializable;
import com.sven.bc.serializer.Serializer;

public class HasherTest {
	
	class BasicSerializerBlock extends Block{
		public BasicSerializerBlock(int id, List<Transaction> transactions, String previousHash) {
			super(id, transactions, previousHash);
		}
	}
	
	@SerializedClass(serializer="com.sven.bc.hasher.TestSerializer")
	class AnnotatedBasicSerializerBlock extends Block {
		public AnnotatedBasicSerializerBlock(int id, List<Transaction> transactions, String previousHash) {
			super(id, transactions, previousHash);
		}
	}
	
	@Test
	public void getSerializerBasicSerializerTest() {
		Block b = new BasicSerializerBlock(1, null, "000");
		Serializer s = Hasher.getSerializer(b);
		System.out.println(s.getClass().getName());
		assertTrue(s instanceof BasicSerializer);
	}
	
	@Test
	public void getSerializerAnnotatedBasicSerializerTest() {
		Block b = new AnnotatedBasicSerializerBlock(1, null, "000");
		Serializer s = Hasher.getSerializer(b);
		System.out.println(s.getClass().getName());
		assertTrue(s instanceof TestSerializer);
	}
}
