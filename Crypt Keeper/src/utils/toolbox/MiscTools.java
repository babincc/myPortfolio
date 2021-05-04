package utils.toolbox;

import java.util.Scanner;

/**
 * This class is a mashup of common methods that are needed throughout the
 * program. They have no other official place to go; so, they go here. It's a
 * random assortment of tools.
 * 
 * @author Christian Babin
 * @version 1.0.2
 * @since 1.0.0
 */
public class MiscTools {

	/**
	 * This method gets keyboard input from the user.
	 * 
	 * @return Whatever the user types.
	 */
	public static String getUserInput() {
		
		Scanner userIn = new Scanner(System.in);
		
		String input = userIn.nextLine();
		
		return input;
		
	}
	
	/**
	 * This method pads the left of a string with zeros to the desired length.
	 * 
	 * @param strToPad   - The string that needs zeros added to it
	 * @param desiredLen - The length that the final string needs to be
	 * @return The original string with zeros padding the left of it to get it to
	 *         the desired length.
	 */
	public static String padLeft(String strToPad, int desiredLen) {
		
		if(strToPad == null) {
			throw new IllegalArgumentException("Error Code: SE5");
		}
		
		if(strToPad.length() >= desiredLen) {
			return strToPad;
		}
		
		String padding = "";
		
		for(int i = 0; i < desiredLen-strToPad.length(); i++) {
			padding += "0";
		}
		
		return padding + strToPad;
		
	}
	
}
