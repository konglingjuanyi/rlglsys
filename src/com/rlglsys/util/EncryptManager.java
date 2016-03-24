package com.rlglsys.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptManager {

	/**
	 * 用户密码加密
	 * @param msg
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String EncryptStr(String msg) throws UnsupportedEncodingException  
	{  
		byte[] resultBytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("SHA");
			byte[] srcBytes;
			try {
				srcBytes = msg.getBytes("UTF-8");
				md5.update(srcBytes);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultBytes = md5.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuffer md5StrBuff = new StringBuffer();
		// 将加密后的byte数组转换为十六进制的字符串,否则的话生成的字符串会乱码
		for (int i = 0; i < resultBytes.length; i++) {
			if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & resultBytes[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & resultBytes[i]));
			}

		}
		return md5StrBuff.toString();
	}
}
