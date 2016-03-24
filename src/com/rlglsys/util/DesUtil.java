package com.rlglsys.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesUtil {

	private final static String DES = "DES";

	public String encrypt(Object... obj) throws Exception {

		return encrypt(obj[0].toString(), "dsfgdshjgd");
	}

	public String decrypt(Object... obj) throws Exception {

		return decrypt(obj[0].toString(), "dsfgdshjgd");
	}

	public static void main(String[] args) throws Exception {

		String data = "QY8+EI0gLuQ=";

		String key = "dsfgdshjgd";

		// System.err.println(encrypt(data, key));

		System.err.println(decrypt(data, key));

	}

	/**
	 * 
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * 
	 * @param key
	 *            加密键byte数组
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public static String encrypt(String data, String key) throws Exception {

		byte[] bt = encrypt(data.getBytes(), key.getBytes());

		String strs = new BASE64Encoder().encode(bt);

		return strs;

	}

	/**
	 * 
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * 
	 * @param key
	 *            加密键byte数组
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */

	public static String decrypt(String data, String key) throws IOException,

	Exception {

		if (data == null)

			return null;

		BASE64Decoder decoder = new BASE64Decoder();

		byte[] buf = decoder.decodeBuffer(data);

		byte[] bt = decrypt(buf, key.getBytes());

		return new String(bt);

	}

	/**
	 * 
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * 
	 * @param key
	 *            加密键byte数组
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		// 生成一个可信任的随机数源

		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作

		Cipher cipher = Cipher.getInstance(DES);
		// 用密钥初始化Cipher对象

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * 
	 * @param key
	 *            加密键byte数组
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	/**
	 * 循环解密
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<String> decrypt(List<String> list) throws Exception {
		List<String> myList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			myList.add(decrypt(list.get(i).toString(), "dsfgdshjgd"));
		}
		return myList;
	}

	/**
	 * 循环加密
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<String> encrypt(List<String> list) throws Exception {
		List<String> myList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			myList.add(encrypt(list.get(i).toString(), "dsfgdshjgd"));
		}
		return myList;
	}

}
