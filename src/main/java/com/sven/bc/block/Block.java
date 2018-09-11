package com.sven.bc.block;

import java.util.Date;
import java.util.List;

import com.sven.bc.anotations.SerializedClass;
import com.sven.bc.anotations.SerializedField;
import com.sven.bc.serializer.Serializable;

@SerializedClass
public abstract class Block extends Serializable{
	
	@SerializedField
	private final int id;

	private final List<Transaction> transactions;
	
	@SerializedField
	private String merkeleRoot; //hash of all transaction
	
	@SerializedField
	private final long timeStamp;
	
	@SerializedField
	private long nonce = 0;
	
	@SerializedField
	private final String previousHash;
	
	// Present block's hash should not be serialized for hashing
	private String hash = null;

	public Block(int id, List<Transaction> transactions, String previousHash) {
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

}
