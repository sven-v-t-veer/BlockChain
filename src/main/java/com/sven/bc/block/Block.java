package com.sven.bc.block;

import com.sven.bc.serializer.Serializable;

public interface Block extends Serializable{
	
	public byte[] getBytes();
	public String getPreviousHash();
	public String getHash();
	public void setHash(String hash);
	public void incrementNonce();

}
