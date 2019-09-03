package de.tu_darmstadt.informatik.skorvan.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class contains methods to encrypt and decipher files using AES with a
 * fixed key
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Crypto {
	// the used key, maybe find a better method to save this
	private static String key = "VRIUS+oEbUOpiO&N834dSD71vy^i90K=";

	/**
	 * encrypt a file, saving it to another file
	 * 
	 * @param in
	 *            the file to encrypt
	 * @param out
	 *            the file the encrypted data shall be written to
	 */
	public static void encrypt(File in, File out) {
		crypto(Cipher.ENCRYPT_MODE, in, out);
	}

	/**
	 * decrypt a file, saving it to another file
	 * 
	 * @param in
	 *            the file to decipher
	 * @param out
	 *            the file the deciphered data shall be written to
	 */
	public static void decrypt(File in, File out) {
		crypto(Cipher.DECRYPT_MODE, in, out);
	}

	/**
	 * use javax.crypto package to do AES encryption and decryption
	 * 
	 * @param cipherMode
	 *            one of Cipher.DECRYPT_MODE or Cipher.ENCRYPT_MODE
	 * @param inFile
	 *            the input file for the algorithm
	 * @param outFile
	 *            the file the output shall be written to
	 */
	private static void crypto(int cipherMode, File inFile, File outFile) {
		try (FileInputStream in = new FileInputStream(inFile); FileOutputStream out = new FileOutputStream(outFile)) {
			// use only 16 bytes as key - might be an unsafe operation (no
			// salting), but not using the whole key might be confusing as well
			Key secretKey = new SecretKeySpec(Arrays.copyOf(key.getBytes(), 16), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);

			byte[] inputBytes = new byte[(int) inFile.length()];
			in.read(inputBytes);

			byte[] outputBytes = cipher.doFinal(inputBytes);

			out.write(outputBytes);
		} catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		}
	}

}
