package generators;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entities.Location;
import error_checkers.UserInputChecker;
import utils.Tools;

/**
 * <p>
 * This class is used to generate a user selected number of hurricane data
 * entries. These entries are meant to be used for my CMSC 508 semester
 * project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, run the main/Gen_A_Disaster.java file in the command
 * prompt/terminal and follow the instructions that it gives you. When asked to
 * select a disaster, choose "Hurricane". After it runs, it will have generated
 * a .csv file with all of your newly generated hurricane data. Alternatively,
 * you could run this file with a number as a command line argument. This number
 * is the number of data entries you want to generate. Doing it this way will
 * achieve the same result.
 * </p>
 * 
 * @author Christian Babin
 * @version 3.1.0
 */
public class Hurricane {
	
	private static int ID = 2000000;			// the number part of the ID for the hurricane being generated
	private static int category;				// hurricane category 1-5
	private static int severity;				// set severity on a smaller scale than category
	private static int windSpeed;				// in mph
	private static int rainfall;				// in inches
	private static int flood_depth;				// in inches
	private static int diameter;				// in miles
	private static String startTime;			// when the hurricane made landfall
	private static String endTime;				// when the hurricane dissipated below a category 1
	private static String warningTime;			// When a warning when out about the disaster
	private static int duration;				// endTime - startTime (not included in .csv file)
	private static ArrayList<Location> rawPath;	// the path of locations without formatting
	private static String[] path;				// a list of localities affected by the hurricane
	private static double pathLen;				// in miles
	private static int totPopulation;			// the total population of all of the cities
	private static int numKilled;				// number of people killed
	private static int numPermInjuries;			// number of people permanently injured
	private static int numTempInjuries;			// number of people temporarily injured
	private static int buildingsDestroyed;		// number of buildings destroyed
	
	private static String allData;	// all of the entries put together as a string

	public static void main(String[] args) {
		
		// make sure command line arguments are proper
		UserInputChecker.numEntitiesHasErrors(args);
		
		// create the file header
		allData = "ID,Category,Wind Speed (mph),Rain Fall (in.),Flood Depth (in.),Diameter (mi.),Warning Time,Start Time (made landfall),End Time (< category 1),Path,Path Length (mi.),Deaths,Permanent Injuries,Less Severe Injuries,Buildings Destroyed\n";
		
		// loop for as many entries as the user wants
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			// set category
			category = Tools.genRand(1, 5);
			
			// set severity
			if(category <= 2) {
				severity = 0;
			} else if(category <= 4) {
				severity = 1;
			} else {
				severity = 3;
			}
			
			// set wind speed
			generateWind();
			
			// set rainfall
			generateRain();
			
			// set flood depth
			generateFlood();
			
			// set diameter
			generateDiameter();
			
			// set start time and end time
			generateDuration();
			
			// set the warning time
			generateWarning();
			
			// set path
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
			allData += "H-" + ID + "," + category + "," + windSpeed + "," + rainfall + "," + flood_depth + "," + diameter + ",\"" + warningTime + "\",\"" + startTime + "\",\"" + endTime + "\",\"";
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
		Tools.writeToFile(allData, "filler_hurricane_data.csv");

	}
	
	/**
	 * This method is used to generate a random wind speed for the hurricane entry
	 * being made. It is based off of the already generated category number.
	 */
	private static void generateWind() {
		
		switch(category) {
			case 1:
				windSpeed = Tools.genRand(74, 95);
				break;
			case 2:
				windSpeed = Tools.genRand(96, 110);
				break;
			case 3:
				windSpeed = Tools.genRand(111, 129);
				break;
			case 4:
				windSpeed = Tools.genRand(130, 156);
				break;
			case 5:
				windSpeed = Tools.genRand(157, 190);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + category + "\" is not a valid category number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random rainfall for the hurricane entry
	 * being made. It is based off of the already generated category number.
	 */
	private static void generateRain() {
		
		switch(category) {
			case 1:
				rainfall = Tools.genRand(5, 10);
				break;
			case 2:
				rainfall = Tools.genRand(7, 12);
				break;
			case 3:
				rainfall = Tools.genRand(9, 16);
				break;
			case 4:
				rainfall = Tools.genRand(13, 35);
				break;
			case 5:
				rainfall = Tools.genRand(25, 60);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + category + "\" is not a valid category number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random flood depth for the hurricane entry
	 * being made. It is based off of the already generated rainfall number.
	 */
	private static void generateFlood() {
		
		if(rainfall >= 5 && rainfall <= 10) {
			flood_depth = Tools.genRand(0, 24);
		} else if(rainfall <= 15) {
			flood_depth = Tools.genRand(12, 36);
		} else if(rainfall <= 20) {
			flood_depth = Tools.genRand(22, 72);
		} else if(rainfall <= 25) {
			flood_depth = Tools.genRand(48, 96);
		} else if(rainfall <= 30) {
			flood_depth = Tools.genRand(72, 120);
		} else if(rainfall <= 35) {
			flood_depth = Tools.genRand(96, 144);
		} else if(rainfall <= 40) {
			flood_depth = Tools.genRand(120, 168);
		} else if(rainfall <= 45) {
			flood_depth = Tools.genRand(144, 192);
		} else if(rainfall <= 50) {
			flood_depth = Tools.genRand(168, 216);
		} else if(rainfall <= 55) {
			flood_depth = Tools.genRand(192, 240);
		} else if(rainfall <= 60) {
			flood_depth = Tools.genRand(216, 264);
		} else {
			throw new IllegalArgumentException("ERROR: \"" + rainfall + "\" is not a valid rainfall value!");
		}
		
	}
	
	/**
	 * This method is used to generate a random diameter for the hurricane entry
	 * being made. It is based off of the already generated category number.
	 */
	private static void generateDiameter() {
		
		switch(category) {
			case 1:
				diameter = Tools.genRand(22, 100);
				break;
			case 2:
				diameter = Tools.genRand(75, 350);
				break;
			case 3:
				diameter = Tools.genRand(125, 375);
				break;
			case 4:
				diameter = Tools.genRand(200, 575);
				break;
			case 5:
				diameter = Tools.genRand(300, 1250);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + category + "\" is not a valid category number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random start and end time for the hurricane
	 * entry being made.
	 */
	private static void generateDuration() { 
		
		// in hours
		switch(category) {
			case 1:
				duration = Tools.genRand(12, 15);
				break;
			case 2:
				duration = Tools.genRand(14, 17);
				break;
			case 3:
				duration = Tools.genRand(16, 19);
				break;
			case 4:
				duration = Tools.genRand(18, 21);
				break;
			case 5:
				duration = Tools.genRand(20, 24);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + category + "\" is not a valid category number!");
		}
		
		String[] times = Tools.genTimes(0, duration, 0, 0);
		
		startTime = times[0];
		
		endTime = times[1];
		
	}
	
	/**
	 * This method is used to generate a random warning time for the hurricane entry
	 * being made.
	 */
	private static void generateWarning() { 
		
		// get the start time
		LocalDateTime dateTime = Tools.convertStartTime(startTime);
		
		// move back to generate a warning time
		dateTime = dateTime.minusDays((long) Tools.genRand(4, 10));
		dateTime = dateTime.minusHours((long) Tools.genRand(0, 23));
		dateTime = dateTime.minusMinutes((long) Tools.genRand(0, 59));
		dateTime = dateTime.minusSeconds(Tools.genRand(0, 59));

		// set warning time
		warningTime = Tools.convertWarningTime(startTime, dateTime);
		
	}
	
	/**
	 * This method is used to generate a random path for the hurricane entry being
	 * made.
	 */
	private static void generatePath() {
		
		rawPath = Tools.genLocations((int) (Tools.leastNumOfCities()/1.5));
		
		path = Tools.formatPath(rawPath);
		
	}
	
	/**
	 * This method is used to generate a random path length for the hurricane entry
	 * being made.
	 */
	private static void generatePathLen() {
		
		switch(category) {
			case 1:
				pathLen = Tools.genRand(100, 130);
				break;
			case 2:
				pathLen = Tools.genRand(120, 150);
				break;
			case 3:
				pathLen = Tools.genRand(140, 170);
				break;
			case 4:
				pathLen = Tools.genRand(160, 190);
				break;
			case 5:
				pathLen = Tools.genRand(180, 210);
				break;
			default:
				throw new IllegalArgumentException("ERROR: \"" + category + "\" is not a valid category number!");
		}
		
	}
	
	/**
	 * This method is used to generate a random number of deaths for the hurricane
	 * entry being made.
	 */
	private static void generateDeaths() {
		
		totPopulation = 0; 
		
		for(Location location : rawPath) {
			totPopulation += location.getPopulation();
		}
		
		numKilled = Tools.genDeaths(severity, totPopulation)/15;
		
	}
	
	/**
	 * This method is used to generate a random number of permanent and temporary
	 * injuries for the hurricane entry being made.
	 */
	private static void generateInjuries() {
		
		int survivors = totPopulation - numKilled;
		
		int[] injuries = Tools.genInjuries(severity, survivors);
		
		numPermInjuries = injuries[0];
		
		numTempInjuries = injuries[1];
		
	}
	
	/**
	 * This method is used to generate a random number of destroyed buildings for
	 * the hurricane entry being made.
	 */
	private static void generateBuildings() {
		
		buildingsDestroyed = Tools.genDeaths(severity, totPopulation*8)/30;
		
	}

}
