package generators;

import entities.Location;
import error_checkers.UserInputChecker;
import utils.FDF;
import utils.Tools;

/**
 * <p>
 * This class is used to generate a user selected number of vehicle crash data
 * entries. These entries are meant to be used for my CMSC 508 semester
 * project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, run the main/Gen_A_Disaster.java file in the command
 * prompt/terminal and follow the instructions that it gives you. When asked to
 * select a disaster, choose "VehicleCrash". After it runs, it will have
 * generated a .csv file with all of your newly generated vehicle crash data.
 * Alternatively, you could run this file with a number as a command line
 * argument. This number is the number of data entries you want to generate.
 * Doing it this way will achieve the same result.
 * </p>
 * 
 * @author Christian Babin
 * @version 3.2.0
 */
public class VehicleCrash {

	private static int ID = 4000000;		// the number part of the ID for the vehicle crash being generated
	private static String vehicleType;		// the type of vehicle that crashed
	private static int numPassengers;		// how many passengers were onboard
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
		allData = "ID,Vehicle Type,Passengers (excluding driver),Killed,Permanent Injury,Less Severe Injury,Date - Time,Location,Weather Conditions,Cause\n";

		// loop for as many entries as the user wants
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			// choose and fill vehicle
			genVehicleAndPassengers();
			
			// generate severity
			severity = Tools.genRand(0, 5);
			
			// generate number of people killed
			numKilled = Tools.genDeaths(severity, numPassengers+1);
			
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
			allData += "VC-" + ID + "," + vehicleType + "," + numPassengers + "," + numKilled + "," + numPermInjuries + "," + numTempInjuries + ",\"" + time + "\",\"" + location + "\"," + weather + "," + cause + "\n";
		
			// increment the ID
			ID++;
		}
		
		// write the list of tornadoes to a file
		Tools.writeToFile(allData, "filler_vehicle-crash_data.csv");
					
	}
	
	/**
	 * This method is used to generate a random vehicle type and number of
	 * passengers for the vehicle crash entry being made.
	 */
	private static void genVehicleAndPassengers() {
		
		// pick a vehicle
		String[] vehicle = FDF.VEHICLE_COLLECTION[Tools.genRand(0, FDF.VEHICLE_COLLECTION.length-1)];
		
		// extract the data
		vehicleType = vehicle[0];
		int maxPassengers = Integer.parseInt(vehicle[1]);
		
		// set the number of passengers in the vehicle
		numPassengers = Tools.genRand(0, maxPassengers);
		
		// exception for school bus since it is so big
		if(vehicleType.equals("School Bus") && Tools.genRand(0, 1) == 1) {
			numPassengers = Tools.genRand(15, maxPassengers);
		}
		
	}
	
	/**
	 * This method is used to generate a random number of people injured permanently
	 * and temporarily for the vehicle crash entry being made.
	 */
	private static void genInjuries() {
		
		int[] injuries = Tools.genInjuries(severity, numPassengers+1, numKilled);
		
		numPermInjuries = injuries[0];
		
		numTempInjuries = injuries[1];
		
	}

}
