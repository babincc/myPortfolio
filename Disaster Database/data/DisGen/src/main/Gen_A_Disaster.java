package main;

import java.util.Scanner;

import error_checkers.UserInputChecker;
import generators.*;

/**
 * <p>
 * This class is used to generate a user selected number of disaster data
 * entries for a specific user selected disaster. These entries are meant to be
 * used for my CMSC 508 semester project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, run this file in the command prompt/terminal and follow
 * the instructions that it gives you. After it runs, it will have generated a
 * .csv file with all of your newly generated disaster data for the disaster of
 * your choice.
 * </p>
 * 
 * @author Christian Babin
 * @version 2.1.0
 */
public class Gen_A_Disaster {
	
	private static int disType;			// the index for the disaster type in the DIS_TYPE list
	private static String disTypeStr;	// the type of disaster the user wants to generate
	private static int numEntries;		// the number of entries the user wants to generate
	
	// an array of the disaster types the user can select
	private static final String[] DIS_TYPES = {
			// Natural
			"Tornado", "Hurricane", /* "Earthquake", "WildFire",
			"Volcano", "Landslide", "Famine", "Drought",
			"Flood", "Tsunami", "Pandemic", */
			
			// Manmade
			"PlaneCrash", "VehicleCrash" /* , "ShipSinking",
			"Bombing", "Shooting", "War", "ChemicalSpill" */
	};

	public static void main(String[] args) {
		
		// Display welcome message
		System.out.println("Welcome to DisGen, the #1 disaster data generator for CMSC 508!\n");
		
		Scanner userIn = new Scanner(System.in);
		
		// let the user pick a disaster
		disType = selectDisType(userIn);
		disTypeStr = DIS_TYPES[disType];
		
		// let the user pick number of entries
		numEntries = selectNumEntries(userIn);
		
		userIn.close();
		
		genADisaster();

	}
	
	/**
	 * This method is used to collect user input to find what disaster type the user
	 * wants.
	 * 
	 * @param userIn - A scanner looking for user input
	 * @return An integer that is the index of the user selected disaster type from
	 *         the DIS_TYPES list.
	 */
	private static int selectDisType(Scanner userIn) {
		
		// Display instructions for user
		System.out.println("Please select a disaster type to get started (type the number and hit enter):");
		
		// display the options to the user
		displayDisTypes();
		
		// get the user input
		String disChoice = userIn.nextLine().trim();
		
		if(UserInputChecker.disTypeChoiceHasErrors(disChoice)) {
			return selectDisType(userIn);
		} else {
			return Integer.parseInt(disChoice)-1;
		}
		
	}
	
	/**
	 * This method is used to display all of the options for disaster types.
	 */
	private static void displayDisTypes() {
		
		final int OPTION_LEN = 20;
		int counter = 1;
		
		for(String disaster : DIS_TYPES) {
			String disOption;
			
			// create the string for this disaster option
			if(counter < 10) {
				disOption = "(" + counter + ")  ";
			} else {
				disOption = "(" + counter + ") ";
			}
			disOption += disaster;
			
			// add spacing to this option (each is OPTION_LEN chars long)
			for(int i = disOption.length(); i < OPTION_LEN; i++) {
				disOption += " ";
			}
			
			// display this option
			System.out.print(disOption + "\t\t");
			
			// only allow 4 items per line
			if(counter % 4 == 0) {
				System.out.println();
			}
			counter++;
		}
		
		System.out.print("\n\nYour selection: ");
		
	}
	
	/**
	 * This method allows the user to select how many entries they want to generate.
	 * 
	 * @param userIn - A scanner looking for user input
	 * @return The number of entries the user wants to generate
	 */
	private static int selectNumEntries(Scanner userIn) {
		
		System.out.println("How many " + disTypeStr + " data entries do you want to generate?");
		
		// get the user input
		String numEntries = userIn.nextLine().trim();
		
		if(UserInputChecker.numEntriesChoiceHasErrors(numEntries)) {
			return selectNumEntries(userIn);
		} else {
			return Integer.parseInt(numEntries);
		}
		
	}
	
	/**
	 * This method is used to generate a data set for the user specified disaster.
	 * This method directs the program to the proper disaster file.
	 */
	private static void genADisaster() {
		
		String[] numEntriesArr = {Integer.toString(numEntries)}; // convert to array to pass as main(args)
		
		// go to the proper java class
		switch(disTypeStr) {
			case "Tornado":
				Tornado.main(numEntriesArr);
				break;
			case "Hurricane":
				Hurricane.main(numEntriesArr);
				break;
			case "PlaneCrash":
				PlaneCrash.main(numEntriesArr);
				break;
			case "VehicleCrash":
				VehicleCrash.main(numEntriesArr);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + disTypeStr + "\" was not accounted for in the switch statement!");
		}
		
	}
	
	public static String[] getDisTypes() {
		
		return DIS_TYPES;
		
	}

}
