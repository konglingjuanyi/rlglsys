package com.rlglsys.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesEnCodeAndDeCode {

	  /*
	   * 好医生加密
	   */
	    public String getencode(String key,String value){
	    	String strcode="";
	    	 try {
			    String jiami=java.net.URLEncoder.encode(value, "utf-8");
			    strcode=toHexString(encrypt(jiami, key));
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	return strcode;
	    }
		  /*
		   * 好医生解密
		   */
	    public String getdecode(String key,String value){
	    	String strcode="";
	    	 try {
			    strcode=java.net.URLDecoder.decode(decrypt(value,key), "utf-8") ; 
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    	return strcode;
	    }
	    
	// 华医网加密
	public String encode(String value, String key) {
		String result = "";
		try {
			String jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();
			result = toHexString(encrypt(jiami, key)).toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	// 华医网解密
	public String decode(String result, String key) {
		String decodeResult = "";
		try {
			decodeResult = java.net.URLDecoder.decode(this.decrypt(result, key), "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return decodeResult;
	}

	// 解密数据
	public String decrypt(String message, String key) throws Exception {

		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	public byte[] encrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

		return cipher.doFinal(message.getBytes("UTF-8"));
	}

	public byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	public String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}

}
