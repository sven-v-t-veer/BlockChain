package com.sven.bc.hasher;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sven.bc.anotations.SerializedClass;
import com.sven.bc.serializer.BasicSerializer;
import com.sven.bc.serializer.Serializable;
import com.sven.bc.serializer.Serializer;

public class Hasher {
	
	public static String sha256(Serializable b) throws NoSuchAlgorithmException, IOException {
		Serializer s = getSerializer(b);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return hashToString(digest.digest(s.serialize(b)));
	}
	
	public static String sha256(String s) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return hashToString(digest.digest(s.getBytes("UTF-8")));
	}
	
	
	static final String hashToString(byte[] hash) {
		StringBuffer hexadecimalString = new StringBuffer();
		
		for (int i = 0; i < hash.length; i++) {
			String hexadecimal = Integer.toHexString(0xff & hash[i]);
			if (hexadecimal.length() == 1) hexadecimalString.append('0');
			hexadecimalString.append(hexadecimal);
		}
		return hexadecimalString.toString();
	}

	static Serializer getSerializer(Serializable serializable) {
		Class<?> clazz = serializable.getClass();
		if (clazz.isAnnotationPresent(SerializedClass.class)) {
			SerializedClass sc = clazz.getAnnotation(SerializedClass.class);
			try {
				String className = sc.serializer();
				Class<?> cl = Class.forName(sc.serializer());
				return (Serializer) cl.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				return null;
			}
		} else {
			return new BasicSerializer();
		}
	}

}
