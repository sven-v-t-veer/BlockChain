package com.sven.bc.merkeleroot;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.sven.bc.block.Transaction;
import com.sven.bc.hasher.Hasher;

public class MerkeleRoot {
	
	private List<Transaction> transactions ;
	List<String> hashes;

	public MerkeleRoot(List<Transaction> transactions) throws Throwable {
		this.transactions = transactions;
		try {
			hashes = getTransactionHashes();
		} catch (Exception e) {
			throw e.getCause();
		}
	}
	
	public String getMerkeleRoot() throws NoSuchAlgorithmException, IOException {
		return getRoot(hashes).get(0);
	}
	
	private List<String> getRoot(List<String> hashes) throws NoSuchAlgorithmException, IOException {
		if (hashes.size() == 1) { // this is the root
			return hashes;
		}
		
		List<String> merged = new LinkedList<String>();
		
		//add hashes for merged hashes to root
		for (int i = 0; i < hashes.size() - 1; i+=2) {
			merged.add(merge(hashes.get(i), hashes.get(i + 1)));
		}
		
		// in case of odd length, merge last with itself
		if (hashes.size() % 2 == 1) merged.add(merge(hashes.get(hashes.size() - 1), hashes.get(hashes.size() - 1)));
		return getRoot(merged);
	}

	private List<String> getTransactionHashes() {
		List<String> list = transactions.stream().map(t -> {
			try {
				return Hasher.sha256(t);
			} catch (NoSuchAlgorithmException | IOException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		return list;
	}

	private String merge(String hash1, String hash2) throws NoSuchAlgorithmException, IOException {
		return Hasher.sha256(hash1 + hash2);
	}

}
