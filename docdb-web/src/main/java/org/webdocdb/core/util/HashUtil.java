package org.webdocdb.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public enum HashAlgorithm{
		MD2("MD2"),
		MD5("MD5"),
		SHA_1("SHA-1"),
		SHA_256("SHA-256"),
		SHA_384("SHA-384"),
		SHA_512("SHA-512"),
		;
		private String algorithmName;
		private HashAlgorithm(String algorithmName) {
			this.algorithmName = algorithmName;
		}
		public String getName() {
			return algorithmName;
		}
	}
	
	public static String hash(HashAlgorithm algorithm, String value) {
		MessageDigest md = null;
	    StringBuilder sb = null;
	    try {
	        md = MessageDigest.getInstance(algorithm.getName());
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        throw new RuntimeException(e);
	    }
	    md.update(value.getBytes());
	    sb = new StringBuilder();
	    for (byte b : md.digest()) {
	        String hex = String.format("%02x", b);
	        sb.append(hex);
	    }
	    return sb.toString();
	}
}
