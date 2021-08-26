package utils.errors.checkers;

/**
 * This class is used to check the user's input for errors based on what they
 * are inputting information for.
 * 
 * @author Christian Babin
 * @version 3.1.1
 * @since 1.0.0
 */
public class UserInputChecker {

	/**
	 * This method checks to see if the ciphertext entered by the user is a valid
	 * ciphertext.
	 * 
	 * @param ciphertextStr - The ciphertext entered by the user
	 * @return True if there is an error in the input.
	 */
	public static boolean ciphertextHasErrors(String ciphertextStr) {
		
		return noDataWasEntered(ciphertextStr) || ciphertextStr.length() <= 23;
		
	}

	/**
	 * This method checks to see if the password entered by the user is valid.
	 * 
	 * @param password - The password entered by the user
	 * @return True if there is an error in the input.
	 */
	public static boolean passwordHasErrors(String password) {
		
		return password.length() > 10000;
		
	}
	
	/**
	 * This method checks to see if the user actually inputed any data.
	 * 
	 * @param input - What the user submitted as input
	 * @return True if the user input field was submitted empty. False if they typed
	 *         something.
	 */
	public static boolean noDataWasEntered(String input) {
		
		if(input == null || input.equals("")) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
