package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;

import entities.Location;

/**
 * This class is used to store various tools that the program needs to use.
 * Since these tools are used by multiple classes, they are stored in this
 * common file rather than in each files that needs them.
 * 
 * @author Christian Babin
 * @version 3.2.1
 */
public class Tools {
	
	// the length of the shortest list of cities
	private static int leastNumCities = -1;
	
	/**
	 * This method generates a random number between two integers (inclusively).
	 * 
	 * @param min - The lowest number that should be considered for the final result
	 * @param max - The highest number that should be considered for the final
	 *            result
	 * @return A random integer between min and max (inclusive)
	 */
	public static int genRand(int min, int max) {

		// Don't let min be greater than max
		if (min > max) {
			throw new IllegalArgumentException("ERROR: min must be smaller than max!");
		}

		// A random number between 0 and [max - min] (inclusive)
		double randDouble = Math.random() * (max - min + 1);

		// Casts that random number to an int for return
		int randInt = (int) randDouble;

		// Returns a random int between min and max (inclusive)
		return randInt + min;

	}
	
	/**
     * This method generates a random number between two decimals (inclusively).
     *
     * @param min - The lowest number that should be considered for the final result
     * @param max - The highest number that should be considered for the final
     *            result
     * @return A random decimal between min and max (inclusive)
     */
    public static double genRand(double min, double max) {

        // Don't let min be greater than max
        if (min > max) {
            throw new IllegalArgumentException("ERROR: min must be smaller than max!");
        }

        // A random number between 0 and [max - min] (inclusive)
        double randDouble = new Random().nextDouble() * (max - min);
        
        randDouble += min;
        
        if(randDouble < min) {
        	return min;
        } else if(randDouble > max) {
        	return max;
        }
        
        // Returns a random double between min and max (inclusive)
        return randDouble;

    }
	
	/**
	 * This method generates random numbers between two integers (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @param canRepeat  - Tells whether or not a number can be in the list twice
	 * @return A list of random integers between min and max (inclusive)
	 */
	public static int[] genRand(int min, int max, int numOutputs, boolean canRepeat) {

		// If there can be no repeats but there aren't enough numbers in the range to not repeat
		if(!canRepeat && numOutputs > (max - min)+1) {
			throw new IllegalArgumentException("ERROR: The range is not large enough to have no repeats!");
		}
		
		int[] randNums = new int[numOutputs];
		
		for(int i = 0; i < numOutputs; i++) {
			randNums[i] = -1;
		}
		
		int repCount = 0; //  number of times we need to repeat a call because of recurring nums
		
		// This loop fills the list with random numbers
		for(int i = 0; i < numOutputs; i++) {
			int randNum = genRand(min, max);
			
			// make sure there is no repetition if it is not allowed
			if(!canRepeat) {
				boolean mustRestart = false;
				for(int num : randNums) {
					mustRestart = false;
					
					if(randNum == num) {

						// if we repeat too many times, force a unique number to be picked
						if(repCount == 20) {
							randNum = uniqueNumFinder(randNums, min);
							repCount = 0;
							mustRestart = false;
							break;
						}
						
						i--;
						repCount++;
						mustRestart = true;
						break;
					}
				}
				
				if(!mustRestart) {
					repCount = 0;
				}
			}
			
			if(repCount > 0) {
				continue;
			}
			
			randNums[i] = randNum;
		}
		
		return randNums;

	}
	
	/**
	 * This method generates random numbers between two integers (inclusively). It
	 * generates as many random numbers as the user asks for. The user can also
	 * specify if numbers are allowed to repeat. It does all of this by calling to
	 * the genRand(int, int, int, boolean) method with the boolean automatically set
	 * to true. If the user does not specify if a number can or can't repeat, it is
	 * automatically set to be able to have repeating numbers.
	 * 
	 * @param min        - The lowest number that should be considered for the final
	 *                   result
	 * @param max        - The highest number that should be considered for the
	 *                   final result
	 * @param numOutputs - The amount of random numbers the user needs
	 * @return A list of random integers between min and max (inclusive)
	 */
	public static int[] genRand(int min, int max, int numOutputs) {
		
		return genRand(min, max, numOutputs, true);
		
	}
	
	/**
	 * This method recursively finds a number that is not in the list already.
	 * 
	 * @param compareList - The list of numbers generated so far
	 * @param start - The number to start comparing to the ones in the list
	 * @return A unique number not already in the list
	 */
	private static int uniqueNumFinder(int[] compareList, int start) {
		
		int uniqueNum = -1;
		
		for(int num : compareList) {
			if(num == start) {
				uniqueNum = uniqueNumFinder(compareList, start+1);
			} else {
				uniqueNum = start;
			}
		}
		
		return uniqueNum;
		
	}
	
	/**
     * This method rounds a number to a specified number of places.
     *
     * @param originalNum   - The number submitted to be rounded
     * @param places        - The number of digits past the decimal desired in the final result
     * @return The rounded number
     */
	public static double round(Double originalNum, int places) {

		String ogNum = "" + originalNum;
		String wholeNum = ogNum.substring(0, ogNum.indexOf("."));
		String decimal = ogNum.substring(ogNum.indexOf(".") + 1);

		if (decimal.length() <= places) {
			return originalNum;
		}

		decimal = decimal.substring(0, places);

		// make a string of zeros to see compare with later
		String zeros = "";
		for (int i = 0; i < places; i++) {
			zeros += "0";
		}

		// if the places after the decimal are nothing but zeros, just return the whole
		// number
		if (decimal.equals(zeros)) {
			ogNum = wholeNum + ".0";
		} else {
			ogNum = wholeNum + "." + decimal;
		}

		return Double.parseDouble(ogNum);

	}
	
	/**
	 * This method is used to generate a time for a disaster.
	 * 
	 * @return A time formatted like this example: Mon. Mar. 5, 2014 - 09:24:32.
	 */
	public static String genTime() {
		
		int month;
		int day;
		int year;
		int hour;
		int minute;
		int second;
		
		// generate start month
		month = genRand(1, 12);
		
		// generate start day
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day = genRand(1, 31);
		} else if(month == 4 || month == 6 || month == 9 || month == 11) {
			day = genRand(1, 30);
		} else {
			day = genRand(1, 28); // TODO this could be changed to accommodate for leap years
		}
		
		// generate start year
		year = genRand(1950, 2019); // current year not included as to not accidentally make a future entry
		
		// generate start hour
		hour = genRand(0, 23);
		
		// generate start minute
		minute = genRand(0, 59);
		
		// generate start second
		second = genRand(0, 59);
		
		return "" + monthStr(month) + " " + day + ", " + year + " - " + timeStr(hour, minute, second);
		
	}
	
	/**
	 * This method is used to generate a start and end time for a disaster.
	 * 
	 * @param minutes - How many minutes the disaster was happening
	 * @param seconds - How many seconds the disaster was happening
	 * @return An array with two elements. The first element is the start time and
	 *         the second is the end time.
	 */
	public static String[] genTimes(int minutes, int seconds) {
		
		String[] times = new String[2];
		
		int month;
		int day;
		int year;
		int hour;
		int minute;
		int second;
		
		// generate start month
		month = genRand(1, 12);
		
		// generate start day
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day = genRand(1, 31);
		} else if(month == 4 || month == 6 || month == 9 || month == 11) {
			day = genRand(1, 30);
		} else {
			day = genRand(1, 28); // TODO this could be changed to accommodate for leap years
		}
		
		// generate start year
		year = genRand(1865, 2019); // current year not included as to not accidentally make a future entry
		
		// generate start hour
		hour = genRand(0, 23);
		
		// generate start minute
		minute = genRand(0, 59);
		
		// generate start second
		second = genRand(0, 59);
		
		times[0] = "" + monthStr(month) + " " + day + ", " + year + " - " + timeStr(hour, minute, second);
		
		// START GENERATING END TIME
		
		// generate end second
		second += seconds;
		while(second >= 60) {
			minute++;
			second -= 60;
		}
		
		// generate end minute and end hour and end date by shifting time for the amount of duration
		minute += minutes;
		
		// set end minute
		while(minute >= 60) {
			hour++;
			minute -= 60;
		}
		
		// set end hour
		if(hour >= 24) {
			hour -= 24;
			day++;
		}
		
		// set end day
		if(day == 32) {
			month++;
			day = 1;
		} else if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
			month++;
			day = 1;
		} else if(day == 29 && month == 2) {
			month++;
			day = 1;
		}
		
		// set end month and end year
		if(month == 13) {
			year++;
			month = 1;
		}
		
		times[1] = "" + monthStr(month) + " " + day + ", " + year + " - " + timeStr(hour, minute, second);
		
		return times;
		
	}
	
	/**
	 * This method is used to generate a start and end time for a disaster.
	 * 
	 * @param days - How many days the disaster was happening
	 * @param hours - How many hours the disaster was happening
	 * @param minutes - How many minutes the disaster was happening
	 * @param seconds - How many seconds the disaster was happening
	 * @return An array with two elements. The first element is the start time and
	 *         the second is the end time.
	 */
	public static String[] genTimes(int days, int hours, int minutes, int seconds) {
		
		hours += (days * 24);
		
		minutes += (hours * 60);
		
		return genTimes(minutes, seconds);
		
	}
	
	/**
	 * This method is used to generate a start and end time for a disaster.
	 * 
	 * @param minutes - How many minutes the disaster was happening
	 * @return An array with two elements. The first element is the start time and
	 *         the second is the end time.
	 */
	public static String[] genTimes(int minutes) {
		
		return genTimes(minutes, 0);
		
	}
	
	/**
	 * This method converts month numbers into the word that they represent.
	 * 
	 * @param month - the number being converted into a month name
	 * @return The name of the month represented by the number month.
	 */
	private static String monthStr(int month) {
		
		switch(month) {
			case 1:
				return "Jan.";
			case 2:
				return "Feb.";
			case 3:
				return "Mar.";
			case 4:
				return "Apr.";
			case 5:
				return "May";
			case 6:
				return "Jun.";
			case 7:
				return "Jul.";
			case 8:
				return "Aug.";
			case 9:
				return "Sep.";
			case 10:
				return "Oct.";
			case 11:
				return "Nov.";
			case 12:
				return "Dec.";
			default:
				throw new IllegalArgumentException("ERROR: \"" + month + "\" is not a month!");
		}
		
	}
	
	/**
	 * This method converts the individual time components into a readable time
	 * string.
	 * 
	 * @param hour   - A number 0-23 to represent the hour on a 24 hour time format
	 * @param minute - A number 0-59 to represent the minute during the hour
	 * @param second - A number 0-59 to represent the second during the minute
	 * @return A readable string version of all of the time components.
	 */
	private static String timeStr(int hour, int minute, int second) {
		
		String time = "";
		
		// add the hour to the string
		if(hour < 10) {
			time += "0";
		}
		time += hour + ":";
		
		// add the minute to the string
		if(minute < 10) {
			time += "0";
		}
		time += minute + ":";
		
		// add the second to the string
		if(second < 10) {
			time += "0";
		}
		time += second;
		
		return time;
		
	}
	
	/**
	 * This method finds the formatter that is best to use on the conversion of the start and warning times.
	 * 
	 * @param startTime - The start time of the disaster 
	 * @return The formatter that matches the startTime string.
	 */
	private static DateTimeFormatter getFormatter(String startTime) {
		
		DateTimeFormatter formatter = null;
		LocalDateTime dateTime = null;
		
		// convert to see what works
		if(startTime.contains("May")) { // "May" does not have a dot after it since it is already three letters
			try {
				formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm:ss");
				dateTime = LocalDateTime.parse(startTime, formatter);
			} catch(DateTimeParseException e) {
				formatter = DateTimeFormatter.ofPattern("MMM d, yyyy - HH:mm:ss");
				dateTime = LocalDateTime.parse(startTime, formatter);
			}
		} else {
			try {
				formatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy - HH:mm:ss");
				dateTime = LocalDateTime.parse(startTime, formatter);
			} catch(DateTimeParseException e) {
				formatter = DateTimeFormatter.ofPattern("MMM. d, yyyy - HH:mm:ss");
				dateTime = LocalDateTime.parse(startTime, formatter);
			}
		}
		
		return formatter;
		
	}
	
	/**
	 * This method is used to convert the startTime string of a disaster into a
	 * LocalDateTime object so math can be easily done on it.
	 * 
	 * @param startTime - The start time of the disaster
	 * @return The startTime as s LocalDateTime object.
	 */
	public static LocalDateTime convertStartTime(String startTime) {
		
		// find the proper formatter
		DateTimeFormatter formatter = getFormatter(startTime);
		
		// convert
		LocalDateTime dateTime = LocalDateTime.parse(startTime, formatter);;
		
		return dateTime;
		
	}
	
	/**
	 * This method is used to convert the warningTime of a disaster into a
	 * String object so it can be displayed.
	 * 
	 * @param startTime - The start time of the disaster
	 * @param warningTime - The warning time as a LocalDateTime object
	 * @return The warningTime as a String object.
	 */
	public static String convertWarningTime(String startTime, LocalDateTime warningTime) {
		
		// find the proper formatter
		DateTimeFormatter formatter = getFormatter(startTime);
		
		// convert
		String dateTime = warningTime.format(formatter);
		
		return dateTime;
		
	}
	
	/**
	 * This method generates a list of locations for a disaster.
	 * 
	 * @param numLocations - the number of locations we need to generate
	 * @return An array list of locations.
	 */
	public static ArrayList<Location> genLocations(int numLocations) {
		
		// error checking
		if(numLocations < 0) {
			throw new IllegalArgumentException("ERROR: You must enter a positive number! \"" + numLocations + "\" is not a valid request for number of locations!");
		} else if(numLocations == 0) {
			System.out.print("Warning: Requesting 0 locations to be generated results in a null array to be returned.");
			return null;
		} else if(numLocations > leastNumOfCities()) {
			throw new IllegalArgumentException("ERROR: \"" + numLocations + "\" is too large of a request!");
		}
		
		// list of all locations
		ArrayList<ArrayList<Location>> locations = FDF.getLocations();
		
		// list of locations to be returned
		ArrayList<Location> locationsToReturn = new ArrayList<>();
		
		// pick a country
		int countryIndex = genRand(0, locations.size()-1);
		String country = locations.get(countryIndex).get(0).getCountry();

		// list of cities to choose from in this location
		ArrayList<Location> cites = locations.get(countryIndex);
		
		// pick random locations
		int[] cityIndices = genRand(0, cites.size()-1, numLocations, false);
		for(int i = 0; i < cityIndices.length; i++) {
			locationsToReturn.add(cites.get(cityIndices[i]));
		}
		
		return locationsToReturn;
		
	}
	
	/**
	 * This method generates a location for a disaster.
	 * 
	 * @param numLocations - the number of locations we need to generate
	 * @return A location for a disaster formatted as follows: Town, State/Country
	 */
	public static Location genLocation() {
		
		return genLocations(1).get(0);
		
	}
	
	/**
	 * This method is used to format the path of locations for a disaster.
	 * 
	 * @param rawPath - The unformatted path of locations
	 * @return An array of locations for a disaster with each location formatted as
	 *         follows: latitude, longitude
	 */
	public static String[] formatPath(ArrayList<Location> rawPath) {
		
		String[] path = new String[rawPath.size()];
		
		String country = rawPath.get(0).getCountry();
		
		for(int i = 0; i < rawPath.size(); i++) {
			double latitude = round(genRand(rawPath.get(i).getMinLatitude(), rawPath.get(i).getMaxLatitude()), 6);
			double longitude = round(genRand(rawPath.get(i).getMinLongitude(), rawPath.get(i).getMaxLongitude()), 6);
			path[i] = "" + latitude + ", " + longitude;
		}
		
		return path;
		
	}

	/**
	 * This method goes through the lists of cites to see which one is the shortest.
	 * 
	 * @return The number of cities in the shortest list.
	 */
	public static int leastNumOfCities() {
		
		// no need to recalculate if it's been done already
		if(leastNumCities != -1) {
			return leastNumCities;
		}
		
		int min = Integer.MAX_VALUE;
		
		for(ArrayList<Location> cityList : FDF.getLocations()) {
			if(cityList.size() < min) {
				min = cityList.size();
			}
		}
		
		leastNumCities = min;
		
		return min;
		
	}
	
	/**
	 * <p>
	 * This method is used to generate a random number of people killed for the
	 * crash/wreck disaster entry being made.
	 * </p>
	 * 
	 * <p>
	 * This method can also be used to calculate deaths from a natural disaster. To
	 * do that, only use 0-2 for severity and put the total of all locations'
	 * populations for numPassengers.
	 * </p>
	 * 
	 * @param severity      - how bad the crash was on a scale of 0-5 with 0 being
	 *                      not that bad and 5 being horrible
	 * @param numPassengers - how many people were in the vehicle (excluding the
	 *                      driver)
	 * @return The number of people who were killed in the crash.
	 */
	public static int genDeaths(int severity, int numPassengers) {
		
		switch(severity) {
			case 0:
				return Tools.genRand((int) (numPassengers * .02), (int) (numPassengers * .03));	
			case 1:
				return Tools.genRand((int) (numPassengers * .08), (int) (numPassengers * .11));
			case 2:
				return Tools.genRand((int) (numPassengers * .15), (int) (numPassengers * .25));
			case 3:
				return Tools.genRand((int) (numPassengers * .3), (int) (numPassengers * .41));
			case 4:
				return Tools.genRand((int) (numPassengers * .5), (int) (numPassengers * .65));
			case 5:
				return Tools.genRand((int) (numPassengers * .75), numPassengers);
			default:
				throw new IllegalStateException("ERROR: \"" + severity + "\" is not a valid severity value!");
		}
		
	}
	
	/**
	 * <p>
	 * This method is used to generate a random number of people injured permanently
	 * and temporarily for the crash/wreck disaster entry being made.
	 * </p>
	 * 
	 * <p>
	 * This method can also be used to calculate injuries from a natural disaster.
	 * To do that, only use 0-2 for severity and put the total of all locations'
	 * populations minus their deaths for numSurvivors.
	 * </p>
	 * 
	 * @param severity     - how bad the crash was on a scale of 0-5 with 0 being
	 *                     not that bad and 5 being horrible
	 * @param numSurvivors - how many people lived through the crash
	 * @return An array of two elements which are the number of people with
	 *         permanent, life altering injuries and the number of people with less
	 *         severe injuries that will heal.
	 */
	public static int[] genInjuries(int severity, int numSurvivors) {
		
		int numPermInjuries;
		int numTempInjuries;
		
		switch(severity) {
			case 0:
				numPermInjuries = 0;
				numTempInjuries = Tools.genRand((int) (numSurvivors * .02), (int) (numSurvivors * .03));
				break;
			case 1:
				numPermInjuries = Tools.genRand((int) (numSurvivors * .02), (int) (numSurvivors * .03));
				numTempInjuries = Tools.genRand((int) (numSurvivors * .08), (int) (numSurvivors * .15));
				break;
			case 2:
				numPermInjuries = Tools.genRand((int) (numSurvivors * .08), (int) (numSurvivors * .18));
				numTempInjuries = Tools.genRand((int) (numSurvivors * .2), (int) (numSurvivors * .34));
				break;
			case 3:
				numPermInjuries = Tools.genRand((int) (numSurvivors * .31), (int) (numSurvivors * .4));
				numTempInjuries = Tools.genRand((int) (numSurvivors * .4), (int) (numSurvivors * .6));
				break;
			case 4:
				numPermInjuries = Tools.genRand((int) (numSurvivors * .44), (int) (numSurvivors * .54));
				numTempInjuries = Tools.genRand((int) (numSurvivors * .4), (int) (numSurvivors * .66));
				break;
			case 5:
				numPermInjuries = Tools.genRand((int) (numSurvivors * .75), numSurvivors);
				numTempInjuries = numSurvivors - numPermInjuries;
				break;
			default:
				throw new IllegalStateException("ERROR: \"" + severity + "\" is not a valid severity value!");
		}
		
		int[] injuries = {numPermInjuries, numTempInjuries};
		
		return injuries;
	
	}
	
	/**
	 * This method is used to generate a random number of people injured permanently
	 * and temporarily for the crash/wreck disaster entry being made.
	 * 
	 * @param severity      - how bad the crash was on a scale of 0-5 with 0 being
	 *                      not that bad and 5 being horrible
	 * @param numPassengers - how many people were in the vehicle (excluding the
	 *                      driver)
	 * @param numKilled     - how many people were killed in the crash
	 * @return An array of two elements which are the number of people with
	 *         permanent, life altering injuries and the number of people with less
	 *         severe injuries that will heal.
	 */
	public static int[] genInjuries(int severity, int numPassengers, int numKilled) {
		
		int numSurvivors = numPassengers - numKilled;
		
		return genInjuries(severity, numSurvivors);
		
	}
	
	/**
	 * This method is used to determine whether or not a disaster was an act of terrorism.
	 * 
	 * @return True if it was terrorism.
	 */
	public static boolean wasTerrorism() {
		
		int probability = genRand(0, 150);
		
		return probability == 13;
		
	}

	/**
	 * This method is used to generate a random cause for the disaster entry being
	 * made.
	 */
	public static String generateCause() {
		
		if(Tools.wasTerrorism()) {
			return "Terrorism";
		} else {
			return FDF.CRASH_CAUSES[Tools.genRand(0, FDF.CRASH_CAUSES.length-1)];
		}
		
	}
	
	/**
	 * This method writes the generated data entries to a file.
	 * 
	 * @param allData - The generated data entries
	 * @param fileName - The name of the file being written
	 */
	public static void writeToFile(String allData, String fileName) {
		
		// make the file so it can be written to
		File data_file = new File(fileName);
		FileOutputStream oFile = null;
		try {
			data_file.createNewFile();
			oFile = new FileOutputStream(data_file, false);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File could not be found to write to!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: Could not create file!");
			e.printStackTrace();
		}
		
		// convert the string into a writable format
		byte[] bytesArray = allData.getBytes();

		// write to the file
		try {
			oFile.write(bytesArray);
			oFile.flush();
		} catch (IOException e) {
			System.out.println("ERROR: Could not write to file!");
			e.printStackTrace();
		}
	
	}

}
