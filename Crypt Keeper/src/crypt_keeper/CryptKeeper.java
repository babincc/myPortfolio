package crypt_keeper;

import java.util.Scanner;

import crypt_keeper.objects.Ciphertext;
import crypt_keeper.objects.Decrypter;
import crypt_keeper.objects.Encrypter;
import utils.errors.checkers.UserInputChecker;
import utils.toolbox.MiscTools;

/**
 * This is the main class for the program. It will find out from the user if it
 * is supposed to be encrypting or decrypting. It will take an input string and
 * do the appropriate action on it.
 * 
 * @author Christian Babin
 * @version 3.3.0
 * @since 1.0.0
 */
public class CryptKeeper {

	public static final String SPACE = "!_sp.1@ck_repCode";
	public static final String DEFAULT_PASS = "!_pass.3@ck_repCode";
	
	private static boolean isDecrypting; // True if the user is trying to decrypt their string
	
	public static void main(String[] args) {

		userWantsToDecrypt();
		
		String strForUser = null;
		
		if(isDecrypting) {
			strForUser = decryptProtocol();
			System.out.print("\n\nYour decrypted message is:\n" + "   ");
		} else if(!isDecrypting) {
			strForUser = encryptProtocol();
			System.out.print("\n\nYour encrypted message is:\n");
		}
		
		System.out.println(strForUser);
		
	}

	/**
	 * This method finds out from the user if the user is trying to encrypt or
	 * decrypt.
	 */
	private static void userWantsToDecrypt() {

		System.out.print("Do you want to encrypt or decrypt?\n" + "   ");
		
		// loop until the user gives a proper answer
		while(true) {
			String input = MiscTools.getUserInput().trim().toLowerCase();
			
			if(input.equals("encrypt") || input.equals("enc") || input.equals("e")) {
				isDecrypting = false;
				break;
			} else if(input.equals("decrypt") || input.equals("dec") || input.equals("d")) {
				isDecrypting = true;
				break;
			}
			
			System.out.print("\nInvalid input, try again.\n"
					+ "Do you want to encrypt or decrypt? (enter \"e\" or \"d\")\n" + "   ");
		}

	}
	
	/**
	 * This method start the decryption process.
	 * 
	 * @return The plaintext of the string that the user provides.
	 */
	private static String decryptProtocol() {
		
		Ciphertext ciphertext;
		String ciphertextStr;
		String plaintext = null;
		String password;
		
		System.out.print("\n\nEnter the encrypted text you would like to decrypt, then press enter:\n" + "   ");
		
		// loop until the user provides a proper input
		while(true) {
			ciphertextStr = MiscTools.getUserInput().trim();
			
			if(UserInputChecker.noDataWasEntered(ciphertextStr)) { // no input
				System.out.print("\nInput field was left blank, try again.\n"
						+ "Enter the encrypted text you would like to decrypt, then press enter:\n" + "   ");
				continue;
			} else if(UserInputChecker.ciphertextHasErrors(ciphertextStr)) { // input too short
				System.out.print("\nInvalid input, try again.\n"
						+ "Enter the encrypted text you would like to decrypt, then press enter:\n" + "   ");
				continue;
			}
			
			break; // there are no errors if we get to this point
		}
			
		// get the password that is going to be used to decrypt the message
		password = getPassword();
		
		ciphertext = new Ciphertext();
		ciphertext.setEncStr(ciphertextStr);
		
		Decrypter decrypter = new Decrypter();
		
		decrypter.decrypt(ciphertext, password);
		
		plaintext = decrypter.getPlaintext();
		
		return plaintext;
		
	}
	
	/**
	 * This method start the encryption process.
	 * 
	 * @return The ciphertext of the string that the user provides.
	 */
	private static String encryptProtocol() {
		
		String ciphertextStr = null;
		String plaintext;
		String password;
		
		System.out.print("\n\nEnter the message you would like to encrypt, then press enter:\n" + "   ");
		
		// get the message that the user wants to encrypt
		while(true) {
			plaintext = MiscTools.getUserInput().trim();
			
			if(UserInputChecker.noDataWasEntered(plaintext)) {
				System.out.print("\nInput field was left blank, try again.\n"
						+ "Enter the message you would like to encrypt, then press enter:\n" + "   ");
				continue;
			}
			
			if(plaintext.contains(SPACE)) {
				System.out.print("\nYou are not allowed to inlucde \"" + SPACE + "\", try again.\n"
						+ "Enter the message you would like to encrypt, then press enter:\n" + "   ");
				continue;
			}
			
			break; // there are no errors if we get to this point
		}
		
		// get the password that is going to be used to encrypt the message
		password = getPassword();

		Encrypter encrypter = new Encrypter();
		
		encrypter.encrypt(plaintext, password);
	
		ciphertextStr = encrypter.getCiphertext().toString();
		
		return ciphertextStr;
		
	}
	
	/**
	 * This method will be used to get the password the user plans to use to encrypt
	 * or decrypt the password. If they are encrypting, it will get them to confirm
	 * the password to ensure they do not mess up.
	 * 
	 * @return The user's selected password.
	 */
	private static String getPassword() {
		
		String promptText;
		if(isDecrypting) {
			promptText = "Enter the password that was used to encrypt this ciphertext (if none, press enter):";
		} else {
			promptText = "Enter a password that will be used to encrypt this message (if you don't want to use one, press enter):";
		}
		System.out.print("\n\n" + promptText + "\n" + "   ");
		
		String password;
		
		while(true) {
			password = MiscTools.getUserInput().trim();
			
			if(password.equals(DEFAULT_PASS) && !isDecrypting) {
				System.out.print("\nThis is a system password and is unsafe to use, try again.\n"
						+ promptText + "\n" + "   ");
				continue;
			} else if(UserInputChecker.passwordHasErrors(password)) {
				System.out.print("\nPassword too long, try again.\n"
						+ promptText + "\n" + "   ");
				continue;
			}
			
			break; // there are no errors if we get to this point
		}
		
		// if we are encrypting and the user typed a password
		if(!isDecrypting && !(password == null || password.equals(""))) {
			System.out.print("\nConfirm your password by entering it again:\n" + "   ");
			
			String confirmPass;
			String restartPhrase = null;
			
			while(true) {
				confirmPass = MiscTools.getUserInput().trim();
				
				if(!confirmPass.equals(password)) {
					if(restartPhrase == null) {
						if(password.equalsIgnoreCase("redo password")) {
							restartPhrase = "try password again";
						} else {
							restartPhrase = "redo password";
						}
					} else {
						if(confirmPass.equalsIgnoreCase(restartPhrase)) {
							return getPassword();
						}
					}
					
					System.out.print("\nThis password does not match the one previously entered.\n"
							+ "Confirm your password by entering it again (or type \"" + restartPhrase + "\" to fix your first password entry):\n" + "   ");
					continue;
				}
				
				break; // there are no errors if we get to this point
			}
		}
		
		// if the user doesn't use a password, use the default one
		if(password == null || password.equals("")) {
			password = DEFAULT_PASS;
		}
		
		return password;
		
	}

}
