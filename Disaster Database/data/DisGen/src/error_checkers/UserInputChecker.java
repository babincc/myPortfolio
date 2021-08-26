package error_checkers;

import main.Gen_A_Disaster;

/**
 * This class is used to check user input to make sure they are not going to
 * break the program with their input.
 * 
 * @author Christian Babin
 * @version 1.1.0
 */
public class UserInputChecker {
	
	public static boolean numEntitiesHasErrors(String[] args) {
		
		if(args.length == 0) {
			throw new IllegalStateException("ERROR: You must have an argument that specifies desired number of data entries to be generated!");
		} else if(numEntriesChoiceHasErrors(args[0])) {
			throw new IllegalArgumentException("ERROR: You must have an argument that specifies desired number of data entries to be generated! \"" + args[0] + "\" does not fit this criteria!");
		}
		
		return false;
		
	}

	/**
	 * This method checks to see if the user's choice for the disaster type is a
	 * valid choice.
	 * 
	 * @param disTypeChoice - the number they chose for disaster type
	 * @return True if there is an error.
	 */
	public static boolean disTypeChoiceHasErrors(String disTypeChoice) {
		
		// see if the input is a decimal
		if(isDecimal(disTypeChoice)) {
			System.out.println("\n\n\n\n\n\nERROR: \"" + disTypeChoice + "\" is not a choice! You can't have decimals!");
			return true;
		}
		
		// make sure the user inputs a number
		int disChoiceIndex;
		try {
			disChoiceIndex = Integer.parseInt(disTypeChoice) - 1;
		} catch(NumberFormatException e) {
			System.out.println("\n\n\n\n\n\nERROR: \"" + disTypeChoice + "\" is not a number!");
			return true;
		}
		
		// make sure the user input is a real index in DIS_TYPES
		if(disChoiceIndex >= 0 && disChoiceIndex < Gen_A_Disaster.getDisTypes().length) {
			return false;
		} else {
			System.out.println("\n\n\n\n\n\nERROR: \"" + (disChoiceIndex+1) + "\" is not a choice!");
			return true;
		}
		
	}
	
	/**
	 * This method checks to see if the user's choice for the number of database
	 * entries to generate is a valid choice.
	 * 
	 * @param numEntriesChoice - the number of entries the user wants to generate
	 * @return True if there are errors.
	 */
	public static boolean numEntriesChoiceHasErrors(String numEntriesChoice) {
		
		final int MAX_NUM_ENTRIES = 10000;
		
		// see if the input is a decimal
		if(isDecimal(numEntriesChoice)) {
			System.out.println("\n\n\n\n\n\nERROR: \"" + numEntriesChoice + "\" is not possible! You can't have decimals!");
			return true;
		}
		
		// make sure the user inputs a number
		int numEntries;
		try {
			numEntries = Integer.parseInt(numEntriesChoice);
		} catch(NumberFormatException e) {
			System.out.println("\n\n\n\n\n\nERROR: \"" + numEntriesChoice + "\" is not a number!");
			return true;
		}
		
		// make sure they choose a number from a decent range of entries
		if(numEntries > 0 && numEntries <= MAX_NUM_ENTRIES) {
			return false;
		} else if(numEntries > MAX_NUM_ENTRIES) {
			System.out.println("\n\n\n\n\n\nERROR: \"" + numEntries + "\" is too many entries! Please enter a smaller number!");
		} else {
			System.out.println("\n\n\n\n\n\nERROR: \"" + numEntries + "\" is not enough entries! You must have at least 1 entry!");
		}
		
		return true;
		
	}
	
	/**
	 * This method checks to see if a String is a decimal number.
	 * 
	 * @param userIn - The string being tested
	 * @return True if it is a decimal number.
	 */
	private static boolean isDecimal(String userIn) {
		
		if(userIn.contains(".")) {
			try {
				double checkDecimal = Double.parseDouble(userIn);
				return true;
			} catch(NumberFormatException e) {
				return false;
			}
		}
		
		return false;
		
	}
	
}
