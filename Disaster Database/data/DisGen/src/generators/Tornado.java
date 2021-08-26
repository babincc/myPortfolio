package generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import entities.Location;
import error_checkers.UserInputChecker;
import utils.Tools;

/**
 * <p>
 * This class is used to generate a user selected number of tornado data
 * entries. These entries are meant to be used for my CMSC 508 semester
 * project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, run the main/Gen_A_Disaster.java file in the command
 * prompt/terminal and follow the instructions that it gives you. When asked to
 * select a disaster, choose "Tornado". After it runs, it will have generated a
 * .csv file with all of your newly generated tornado data. Alternatively, you
 * could run this file with a number as a command line argument. This number is
 * the number of data entries you want to generate. Doing it this way will
 * achieve the same result.
 * </p>
 * 
 * @author Christian Babin
 * @version 3.1.0
 */
public class Tornado {
	
	private static int ID = 1000000;			// the number part of the ID for the tornado being generated
	private static int ef;						// Enhanced Fujita (EF) scale measurement
	private static int severity;				// set severity on a smaller scale than EF
	private static int windSpeed;				// in mph
	private static int diameter;				// in meters
	private static String startTime;			// when the tornado started
	private static String endTime;				// when the tornado ended
	private static String warningTime;			// When a warning when out about the disaster
	private static int duration;				// endTime - startTime (not included in .csv file)
	private static ArrayList<Location> rawPath;	// the path of locations without formatting
	private static String[] path;				// a list of localities affected by the tornado
	private static double pathLen;				// in miles
	private static int totPopulation;			// the total population of all of the cities
	private static int numKilled;				// number of people killed
	private static int numPermInjuries;			// number of people permanently injured
	private static int numTempInjuries;			// number of people temporarily injured
	private static int buildingsDestroyed;		// number of buildings destroyed
	
	private static String allData;		// all of the entries put together as a string

	public static void main(String[] args) {
		
		// make sure command line arguments are proper
		UserInputChecker.numEntitiesHasErrors(args);
		
		// create the file header
		allData = "ID,EF,Wind Speed (mph),Diameter (m),Warning Time,Start Time,End Time,Path,Path Length (mi.),Deaths,Permanent Injuries,Less Severe Injuries,Buildings Destroyed\n";
		
		// loop for as many entries as the user wants
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			// set EF
			ef = Tools.genRand(0, 5);
			
			// set severity
			if(ef <= 1) {
				severity = 0;
			} else if(ef <= 3) {
				severity = 1;
			} else {
				severity = 3;
			}
			
			// set wind speed
			generateWind();
			
			// set diameter
			generateDiameter();
			
			// set start time and end time
			generateDuration();
			
			// set the warning time
			generateWarning();
			
			// set the path
			generatePath();
			
			// set path length
			generatePathLen();
			
			// set number of people killed
			generateDeaths();
			
			// set injuries
			generateInjuries();
			
			// set buildings destroyed
			generateBuildings();
			
			// add this location to the final string
			allData += "T-" + ID + "," + ef + "," + windSpeed + "," + diameter + ",\"" + warningTime + "\",\"" + startTime + "\",\"" + endTime + "\",\"";
			for(int j = 0; j < path.length; j++) {
				if(j == path.length-1) {
					allData += path[j];
				} else {
					allData += path[j] + " -> ";
				}
			}
			allData += "\"," + pathLen + "," + numKilled + "," + numPermInjuries + "," + numTempInjuries + "," + buildingsDestroyed + "\n";
			
			// increment the ID
			ID++;
		}
		
		// write the list of tornadoes to a file
		Tools.writeToFile(allData, "filler_tornado_data.csv");

	}
	
	/**
	 * This method is used to generate a random wind speed for the tornado entry
	 * being made. It is based off of the already generated EF number.
	 */
	private static void generateWind() {
		
		switch(ef) {
			case 0:
				windSpeed = Tools.genRand(65, 85);
				break;
			case 1:
				windSpeed = Tools.genRand(86, 110);
				break;
			case 2:
				windSpeed = Tools.genRand(111, 135);
				break;
			case 3:
				windSpeed = Tools.genRand(136, 165);
				break;
			case 4:
				windSpeed = Tools.genRand(166, 200);
				break;
			case 5:
				windSpeed = Tools.genRand(201, 301);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + ef + "\" is not a valid EF number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random diameter for the tornado entry being
	 * made. It is based off of the already generated EF number.
	 */
	private static void generateDiameter() { // TODO this can be changed to make more realistic measurements
		
		switch(ef) {
			case 0:
				diameter = Tools.genRand(2, 100);
				break;
			case 1:
				diameter = Tools.genRand(50, 150);
				break;
			case 2:
				diameter = Tools.genRand(100, 200);
				break;
			case 3:
				diameter = Tools.genRand(150, 250);
				break;
			case 4:
				diameter = Tools.genRand(200, 300);
				break;
			case 5:
				diameter = Tools.genRand(250, 350);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + ef + "\" is not a valid EF number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random start and end time for the tornado
	 * entry being made.
	 */
	private static void generateDuration() { 
		
		duration = Math.min(Tools.genRand(1, 120), Tools.genRand(1, 120)); // in minutes
		
		String[] times = Tools.genTimes(duration);
		
		startTime = times[0];
		
		endTime = times[1];
		
	}
	
	/**
	 * This method is used to generate a random warning time for the tornado entry
	 * being made.
	 */
	private static void generateWarning() { 
		
		// get the start time
		LocalDateTime dateTime = Tools.convertStartTime(startTime);
		
		// move back to generate a warning time
		dateTime = dateTime.minusMinutes((long) Tools.genRand(5, 90));
		dateTime = dateTime.minusSeconds(Tools.genRand(0, 59));

		// set warning time
		warningTime = Tools.convertWarningTime(startTime, dateTime);
		
	}
	
	/**
	 * This method is used to generate a random path for the tornado entry being
	 * made.
	 */
	private static void generatePath() {
		
		// get a number larger than 1 based on the EF of this tornado
		int maxNumLocations = ef*3;
		maxNumLocations = Math.min(maxNumLocations, Tools.leastNumOfCities());
		
		// get a number larger than 1 based on the duration of the tornado
		int travelSpeed = Math.min(Tools.genRand(10, 60), Tools.genRand(10, 60)); // mph
		int milesTraveled = (int) (travelSpeed * (duration / 60.0));
		maxNumLocations = Math.max(2, Math.min(maxNumLocations, milesTraveled/10));
		
		rawPath = Tools.genLocations(Tools.genRand(1, maxNumLocations));
		
		path = Tools.formatPath(rawPath);
		
	}
	
	/**
	 * This method is used to generate a random path length for the tornado entry
	 * being made.
	 */
	private static void generatePathLen() {
		
		int numCities = path.length;
		
		double minMiles = numCities * .25 + Math.max(0, numCities-2);
		double maxMiles = numCities * 10.0;
		
		pathLen = Tools.genRand(minMiles, maxMiles);
		
		pathLen = Tools.round(pathLen, 2);
		
	}
	
	/**
	 * This method is used to generate a random number of deaths for the tornado
	 * entry being made.
	 */
	private static void generateDeaths() {
		
		totPopulation = 0; 
		
		for(Location location : rawPath) {
			totPopulation += location.getPopulation();
		}
		
		numKilled = Tools.genDeaths(severity, totPopulation)/25;
		
	}
	
	/**
	 * This method is used to generate a random number of permanent and temporary
	 * injuries for the tornado entry being made.
	 */
	private static void generateInjuries() {
		
		int survivors = totPopulation - numKilled;
		
		int[] injuries = Tools.genInjuries(severity, survivors);
		
		numPermInjuries = injuries[0];
		
		numTempInjuries = injuries[1];
		
	}
	
	/**
	 * This method is used to generate a random number of destroyed buildings for
	 * the tornado entry being made.
	 */
	private static void generateBuildings() {
		
		buildingsDestroyed = Tools.genDeaths(severity, totPopulation*8)/25;
		
	}

}
