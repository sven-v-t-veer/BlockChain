package com.sven.bc.blockchain;

import com.sven.bc.block.Block;
import com.sven.bc.exceptions.InconsistentHashException;

public class BlockChain {
	
	
	class Node {
		private Node next;
		private Block data;
		
		public Node(Block block) {
			this.data = block;
		}
		
		public Block getData() {
			return data;
		}
		
	}
	
	private Node head = null;
	private Node tail = null;
	private int length = 0;
	
	public BlockChain() {

	}
	
	public void add(Block block) throws InconsistentHashException {
		Node node = new Node(block);
		if (length != 0 && (tail.getData().getHash() != block.getPreviousHash())) {
			throw new InconsistentHashException("Previous hash for Block not equal to hash of previous block!");
		} else if (length == 0) {  //initial block in chain.
			this.head = new Node(block);
			this.tail = head;
			length = 1;
			return;
		}
		if (tail == null) {
			head.next = node;
		} else {
			tail.next = node;
		}
		tail = node;
		length++;
	}
	
	public Block get(int index) throws IndexOutOfBoundsException{
		if (index < 0 || index > length) throw new IndexOutOfBoundsException();
		int i = 0;
		Node found = head;
		while (i < index) {
			found = found.next;
			i++;
		}
		return found.data;
	}
	
	public Block last() {
		return this.tail.getData();
	}
	
	public int size() {
		return length;
	}


}
