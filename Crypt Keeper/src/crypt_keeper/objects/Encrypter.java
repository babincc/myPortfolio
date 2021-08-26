package crypt_keeper.objects;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import crypt_keeper.CryptKeeper;
import utils.toolbox.SaltShaker;

/**
 * This class is used to encrypt the plaintext.
 * 
 * @author Christian Babin
 * @version 3.0.0
 * @since 1.0.0
 */
public class Encrypter {

	private String plaintext;
	private Ciphertext ciphertext;
	private String password;
	
	public Encrypter() {
		
		ciphertext = new Ciphertext();
		
	}
	
	public Encrypter(String plaintext) {
		
		this();
		
		setPlaintext(plaintext);
		
	}
	
	public Encrypter(String plaintext, String password) {
		
		this(plaintext);
		
		setPassword(password);
		
	}
	
	public void setPlaintext(String plaintext) {
	
		this.plaintext = plaintext;
	
	}

	public void setCiphertext(Ciphertext ciphertext) {
	
		this.ciphertext = ciphertext;
	
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	
	}

	public String getPlaintext() {
	
		return plaintext;
	
	}

	public Ciphertext getCiphertext() {
	
		return ciphertext;
	
	}
	
	public String getPassword() {
		
		return password;
	
	}
	
	/**
	 * This method encrypts the plaintext.
	 * 
	 * @return The ciphertext encrypted from the plaintext.
	 */
	public Ciphertext encrypt() {
		
		if(plaintext == null) {
			throw new IllegalStateException("Error Code: NE3");
		}
		
		// add random salt to the plaintext to change length of ciphertext
		SaltShaker saltShaker = new SaltShaker();
		plaintext = saltShaker.addSalt(plaintext);
		
		// remove the spaces from the plaintext
		String spacelessPlaintext = plaintext.replace(" ", CryptKeeper.SPACE);
		
		byte[] plaintextBytes = spacelessPlaintext.getBytes(StandardCharsets.US_ASCII);
		byte[] OTPBytes = ciphertext.getOTP().getBytes(StandardCharsets.US_ASCII);
		byte[] ciphertextBytes = new byte[plaintextBytes.length];
				
		int counter = 0;
		for(int i = 0; i < plaintextBytes.length; i++) {
			int currB = plaintextBytes[i];

			currB -= 33;

			currB += ciphertext.getOffset();

			currB += OTPBytes[counter];

			currB %= 94;

			ciphertextBytes[i] = (byte) (currB + 33);

			// loop through the OTP over and over as you go through the plaintext
			if(counter == OTPBytes.length-1) {
				counter = 0;
			} else {
				counter++;
			}
		}
		
		// convert ASCII array to string
		String ciphertextStr = new String(ciphertextBytes, StandardCharsets.UTF_8);

		ciphertext.setEncStr(ciphertextStr);
		
		return ciphertext;
		
	}
	
	/**
	 * This method encrypts the plaintext.
	 * 
	 * @param plaintext - The message being encrypted.
	 * @param password - The password being used to encrypt this message.
	 * @return The ciphertext encrypted from the plaintext.
	 */
	public Ciphertext encrypt(String plaintext, String password) {
		
		setPlaintext(plaintext);
		
		setPassword(password);
		
		encrypt();
		
		Encrypter tempEncrypter = new Encrypter(ciphertext.toString(), password);
		tempEncrypter.getCiphertext().setOffset(69);
		tempEncrypter.getCiphertext().setOTP(password);
		
		tempEncrypter.encrypt();
		
		ciphertext.setEncStr(tempEncrypter.getCiphertext().getEncStr());
		
		ciphertext.finalSweep();
		
		return ciphertext;
		
	}
	
}
