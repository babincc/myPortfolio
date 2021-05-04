package crypt_keeper.objects;

import java.nio.charset.StandardCharsets;

import crypt_keeper.CryptKeeper;
import utils.toolbox.SaltShaker;

/**
 * This class is used to decrypt the ciphertext.
 * 
 * @author Christian Babin
 * @version 3.3.0
 * @since 1.0.0
 */
public class Decrypter {

	private String plaintext;
	private Ciphertext ciphertext;
	private String password;
	
	private final String DEPRICATION_WARNING = "Warning: This ciphertext was encrypted with an old verion of Crypt Keeper! It is recomended that you re-encrypt it with the current version and update it where ever you have your encryptions saved.";
	
	public Decrypter() {
	}
	
	public Decrypter(Ciphertext ciphertext) {
		
		setCiphertext(ciphertext);
		
	}
	
	public Decrypter(Ciphertext ciphertext, String password) {
		
		this(ciphertext);
		
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
	 * This method decrypts the ciphertext.
	 * 
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	public String decrypt() {
		
		if(ciphertext == null) {
			throw new IllegalStateException("Error Code: ND1");
		}
		
		// get the version information for when this ciphertext was made
		Version ckVersion = ciphertext.getvNum();
		int macro = ckVersion.getMacro();
		int meso = ckVersion.getMeso();
		int micro = ckVersion.getMicro();
		
		// Warn user about deprecation
		if(macro < 3 || meso < 1) {
			System.out.println(DEPRICATION_WARNING);
		}
		
		// decrypt based on version of encryption
		if(macro == 1) {							// v1.0.0, v1.0.1, v1.0.2
			return decrypt_v1_0_0_to_v1_0_2();
		} else if(macro == 2) {						// v2.0.0
			return decrypt_v2_0_0();
		} else if(macro == 3) {						// v3.0.0, v3.1.0, v3.1.1, v3.2.0, v3.3.0
			if(meso == 0) {								// v3.0.0
				return decrypt_v3_0_0();
			} else if(meso <= 3) {						// v3.1.0, v3.1.1, v3.2.0, v3.3.0
				return decrypt_v3_1_0_to_v3_3_0();
			}
		}
		
		// version number is not in the if tree
		throw new IllegalArgumentException("Error Code: VNW2");
		
	}
	
	/**
	 * This method decrypts the ciphertext.
	 * 
	 * @param ciphertext - The ciphertext being decrypted.
	 * @param password - The password used when the ciphertext was encrypted.
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	public String decrypt(Ciphertext ciphertext, String password) {
		
		setCiphertext(ciphertext);
		
		setPassword(password);
		
		return decrypt();
		
	}
	
	/**
	 * This method decrypts the ciphertext.
	 * 
	 * @param ciphertext - The ciphertext being decrypted.
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	public String decrypt(Ciphertext ciphertext) {
		
		setCiphertext(ciphertext);
		
		return decrypt();
		
	}
	
	/**
	 * @deprecated
	 * 
	 * This method decrypts ciphertexts that were encrypted in Crypt Keeper version
	 * 1.0.0 - 1.0.2.
	 * 
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	@Deprecated
	private String decrypt_v1_0_0_to_v1_0_2() {
		
		byte[] OTPBytes = ciphertext.getOTP().getBytes(StandardCharsets.US_ASCII);
		byte[] ciphertextBytes = ciphertext.getEncStr().getBytes(StandardCharsets.US_ASCII);
		byte[] plaintextBytes = new byte[ciphertextBytes.length];
		
		int counter = 0;
		for(int i = 0; i < ciphertextBytes.length; i++) {
			int currB = ciphertextBytes[i];

			currB -= 33;

			currB -= ciphertext.getOffset();

			currB -= OTPBytes[counter];

			while(currB < 0) {
				currB += 94;
			}

			plaintextBytes[i] = (byte) (currB + 33);

			// loop through the OTP over and over as you go through the plaintext
			if(counter == OTPBytes.length-1) {
				counter = 0;
			} else {
				counter++;
			}
		}
		
		// convert ASCII array to string
		plaintext = new String(plaintextBytes, StandardCharsets.UTF_8);

		plaintext = plaintext.replace(CryptKeeper.SPACE, " ");
		
		return plaintext;
		
	}
	
	/**
	 * @deprecated
	 * 
	 * This method decrypts ciphertexts that were encrypted in Crypt Keeper version
	 * 2.0.0.
	 * 
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	@Deprecated
	private String decrypt_v2_0_0() {
		
		decrypt_v1_0_0_to_v1_0_2();
		
		// remove the salt so the message is clear
		SaltShaker saltShaker = new SaltShaker();
		plaintext = saltShaker.removeSalt(plaintext);
		
		return plaintext;
		
	}
	
	/**
	 * @deprecated
	 * 
	 * This method decrypts ciphertexts that were encrypted in Crypt Keeper version
	 * 3.0.0.
	 * 
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	@Deprecated
	private String decrypt_v3_0_0() {
		
		ciphertext.setOffset(69);
		ciphertext.setOTP(password);
		
		ciphertext = new Ciphertext(decrypt_v2_0_0());

		decrypt_v2_0_0();
		
		return plaintext;
		
	}
	
	/**
	 * This method decrypts ciphertexts that were encrypted in Crypt Keeper version
	 * 3.1.0 - 3.3.0.
	 * 
	 * @return The plaintext message decrypted from the ciphertext.
	 */
	private String decrypt_v3_1_0_to_v3_3_0() {
		
		ciphertext.setOffset(69);
		ciphertext.setOTP(password);
		
		ciphertext = new Ciphertext(decrypt_v2_0_0());

		if(!ciphertext.isVerifiedCKCiphertext()) {
			throw new IllegalArgumentException("Error Code: NV7");
		}
		
		decrypt_v2_0_0();
		
		return plaintext;
		
	}
	
}
