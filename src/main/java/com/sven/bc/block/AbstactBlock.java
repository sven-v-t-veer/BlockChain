package com.sven.bc.block;

import java.util.Date;
import java.util.List;

import com.sven.bc.anotations.SerializedField;
import com.sven.bc.serializer.BlockSerializer;

public abstract class AbstactBlock implements Block{
	
	@SerializedField
	private final int id;
	
	@SerializedField
	private final List<Transaction> transactions;
	
	@SerializedField
	private final long timeStamp;
	
	@SerializedField
	private long nonce = 0;
	
	@SerializedField
	private final String previousHash;
	
	// Present block's hash should not be serialized for hashing
	private String hash = null;

	public AbstactBlock(int id, List<Transaction> transactions, String previousHash) {
		this.id = id;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.transactions = transactions;
	}
	
	public final int getId() {
		return id;
	}
	
	public final void incrementNonce() {
		this.nonce++;
	}

	public final long getNonce() {
		return nonce;
	}
	
	public final String getHash() {
		return hash;
	}

	public final void setHash(String hash) {
		this.hash = hash;
	}

	public final List<Transaction> getTransactions() {
		return transactions;
	}

	public final long getTimeStamp() {
		return timeStamp;
	}
	
	public final String getPreviousHash() {
		return previousHash;
	}
	
	public byte[] getBytes() {
		BlockSerializer s = new BlockSerializer();
		return s.serialize(this);
	}
}
