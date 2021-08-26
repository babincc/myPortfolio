package generators;

import entities.Location;
import error_checkers.UserInputChecker;
import utils.FDF;
import utils.Tools;

/**
 * <p>
 * This class is used to generate a user selected number of plane crash data
 * entries. These entries are meant to be used for my CMSC 508 semester
 * project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, run the main/Gen_A_Disaster.java file in the command
 * prompt/terminal and follow the instructions that it gives you. When asked to
 * select a disaster, choose "PlaneCrash". After it runs, it will have generated
 * a .csv file with all of your newly generated plane crash data. Alternatively,
 * you could run this file with a number as a command line argument. This number
 * is the number of data entries you want to generate. Doing it this way will
 * achieve the same result.
 * </p>
 * 
 * @author Christian Babin
 * @version 3.2.0
 */
public class PlaneCrash {

	private static int ID = 3000000;		// the number part of the ID for the plane crash being generated
	private static String planeType;		// the type of plane that crashed
	private static int numSouls;			// how many passengers were onboard
	private static int severity;			// 0-5 how severe the crash was (not included in output)
	private static int numKilled;			// how many passengers died
	private static int numPermInjuries;		// how many passengers got injuries that will affect the rest of their life
	private static int numTempInjuries; 	// how many passengers got only minor injuries
	private static String time;				// time of crash
	private static Location rawLocation;	// the unformatted location
	private static String location;			// where the crash happened
	private static String weather;			// what was the weather at the time of the crash
	private static String cause;			// why the crash happened

	private static String allData;	// all of the entries put together as a string
	
	public static void main(String[] args) {
		
		// make sure command line arguments are proper
		UserInputChecker.numEntitiesHasErrors(args);
		
		// create the file header
		allData = "ID,Plane Type,Souls Onboard,Killed,Permanent Injury,Less Severe Injury,Date - Time,Location,Weather Conditions,Cause\n";

		// loop for as many entries as the user wants
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			// choose and fill plane
			genPlaneAndSouls();
			
			// generate severity
			severity = Tools.genRand(0, 5);
			
			// generate number of people killed
			numKilled = Tools.genDeaths(severity, numSouls);
			
			// generate number of injury types
			genInjuries();

			// generate a time
			time = Tools.genTime();
			
			// generate a location
			rawLocation = Tools.genLocation();
			double latitude = Tools.round(Tools.genRand(rawLocation.getMinLatitude(), rawLocation.getMaxLatitude()), 6);
			double longitude = Tools.round(Tools.genRand(rawLocation.getMinLongitude(), rawLocation.getMaxLongitude()), 6);
			location = "" + latitude + ", " + longitude;
			
			// generate weather conditions
			weather = FDF.WEATHER_CONDITIONS[Tools.genRand(0, FDF.WEATHER_CONDITIONS.length-1)];
			
			// generate cause
			cause = Tools.generateCause();

			// add this location to the final string
			allData += "PC-" + ID + "," + planeType + "," + numSouls + "," + numKilled + "," + numPermInjuries + "," + numTempInjuries + ",\"" + time + "\",\"" + location + "\"," + weather + "," + cause + "\n";
		
			// increment the ID
			ID++;
		}
		
		// write the list of tornadoes to a file
		Tools.writeToFile(allData, "filler_plane-crash_data.csv");
		
	}
	
	/**
	 * This method is used to generate a random plane type and number of souls for
	 * the plane crash entry being made.
	 */
	private static void genPlaneAndSouls() {
		
		// pick a plane
		String[] plane = FDF.PLANE_COLLECTION[Tools.genRand(0, FDF.PLANE_COLLECTION.length-1)];
		
		// extract the data
		planeType = plane[0];
		int maxSouls = Integer.parseInt(plane[1]);
		
		// set the number of passengers on the plane
		if(maxSouls < 100) {
			numSouls = Tools.genRand(45, maxSouls);
		} else if(maxSouls < 150) {
			numSouls = Tools.genRand(65, maxSouls);
		} else if(maxSouls < 600) {
			numSouls = Tools.genRand(maxSouls-110, maxSouls);
		} else if(maxSouls < 700) {
			numSouls = Tools.genRand(maxSouls-120, maxSouls);
		} else if(maxSouls < 800) {
			numSouls = Tools.genRand(maxSouls-130, maxSouls);
		} else if(maxSouls < 900) {
			numSouls = Tools.genRand(maxSouls-140, maxSouls);
		} else if(maxSouls < 1000) {
			numSouls = Tools.genRand(maxSouls-150, maxSouls);
		} else {
			numSouls = Tools.genRand(maxSouls-160, maxSouls);
		}
		
	}
	
	/**
	 * This method is used to generate a random number of people injured permanently
	 * and temporarily for the plane crash entry being made.
	 */
	private static void genInjuries() {
		
		int[] injuries = Tools.genInjuries(severity, numSouls, numKilled);
		
		numPermInjuries = injuries[0];
		
		numTempInjuries = injuries[1];
		
	}

}
