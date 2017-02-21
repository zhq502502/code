package com.packages.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DesEncrypter {
  Cipher ecipher;

  Cipher dcipher;

  DesEncrypter(SecretKey key) {
	  try{
		  ecipher = Cipher.getInstance("DES");
		  dcipher = Cipher.getInstance("DES");
		  ecipher.init(Cipher.ENCRYPT_MODE, key);
		  dcipher.init(Cipher.DECRYPT_MODE, key);
	  } catch(Exception e){
		  e.printStackTrace();
	  }
  }

  public String encrypt(String str) {
		byte[] utf8 = null;
		try {
			utf8 = str.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] enc = null;
		try {
			enc = ecipher.doFinal(utf8);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String str) {
		byte[] dec = null;
		try {
			dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] utf8 = null;
		try {
			utf8 = dcipher.doFinal(dec);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		try {
			return new String(utf8, "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
  }
  /* // MD5函数 */
	public static String md5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
  
  public static void main(String[] argv) throws Exception {
    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    DesEncrypter encrypter = new DesEncrypter(key);
    String encrypted = encrypter.encrypt("2");
    String decrypted = encrypter.decrypt(encrypted);
    System.out.println(encrypted);
    System.out.println(decrypted);
    System.out.println(md5("admin"));
  }
}
