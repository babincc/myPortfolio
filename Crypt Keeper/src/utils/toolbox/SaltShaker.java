package utils.toolbox;

import utils.toolbox.math.MathClass;

/**
 * This class is used to add random salt to the string. It is mainly to make the
 * string longer so it will be harder to tell how long the original input is.
 * 
 * @author Christian Babin
 * @version 2.0.0
 * @since 2.0.0
 */
public class SaltShaker {

	// all of the random salts to add to the plaintext (salt can be added to this list but never removed)
	private final String[] SALT = {
			"Table salt",
			"Sodium Chloride",
			"NaCl",
			
			"Orange, flammable salt",
			"Potassium Dichromate",
			"K2Cr2O7",
			
			"Road salt",
			"Calcium Chloride",
			"CaCl2",
			
			"Dry salt for pools and spas",
			"Sodium Bisulfate",
			"NaHSO4",
			
			"Blue salt",
			"Copper Sulfate",
			"CuSO4",
			
			"Soap salt",
			"Sodium Hydroxide",
			"NaOH",
			
			"Baking soda",
			"Sodium Bi-carbonate",
			"NaHCO3",
			
			"Plaster of Paris",
			"Hemihydrate Calcium Sulphate",
			"CaSO4",
			
			"Pepper"};
	
	public SaltShaker() {
	}
	
	/**
	 * This method adds salt to the plaintext.
	 * 
	 * @param plaintext - The message being encrypted
	 * @return The plaintext with random salt throughout it.
	 */
	public String addSalt(String plaintext) {
		
		// pick a random starting point in the string
		int i = MathClass.genRand(0, plaintext.length());
		
		String saltyText = plaintext.substring(0, i);
		
		// go from the starting point and start adding salt to random places
		for(; i < plaintext.length();) {
			int index = MathClass.genRand(0, SALT.length-1); // which salt to add
			
			String salt = "!_" + SALT[index] + ".2@ck_repCode"; // make the salt less likely to be something the user put in the plaintext
			
			saltyText += salt; // add the salt to the string
			
			int temp = MathClass.genRand(i+1, plaintext.length()); // find the next location for salt
			
			saltyText += plaintext.substring(i, temp); // add the rest of the plaintext up to the next salt spot
			
			i = temp; // set the next salt spot
		}
		
		return saltyText;
		
	}
	
	/**
	 * This method removes salt from a string to reveal a clean plaintext.
	 * 
	 * @param saltyText - The almost decrypted plaintext with salt in it
	 * @return A clean plaintext with no salt in it.
	 */
	public String removeSalt(String saltyText) {
		
		String plaintext = saltyText;
		
		for(String salt : SALT) {
			String saltCode = "!_" + salt + ".2@ck_repCode";

			plaintext = plaintext.replace(saltCode, "");
		}
		
		return plaintext;
		
	}
	
}
