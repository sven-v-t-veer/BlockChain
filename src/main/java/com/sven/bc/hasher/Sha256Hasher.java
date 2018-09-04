package com.sven.bc.hasher;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sven.bc.block.Block;

public class Sha256Hasher {
	
	public String sha256(Block b) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return hashToString(digest.digest(b.getBytes()));
	}
	
	
	private final String hashToString(byte[] hash) {
		StringBuffer hexadecimalString = new StringBuffer();
		
		for (int i = 0; i < hash.length; i++) {
			String hexadecimal = Integer.toHexString(0xff & hash[i]);
			if (hexadecimal.length() == 1) hexadecimalString.append('0');
			hexadecimalString.append(hexadecimal);
		}
		return hexadecimalString.toString();
	}

	

}
