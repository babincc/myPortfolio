package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Location;

/**
 * <p>
 * FDF == Fake Data Fillers
 * </p>
 * 
 * <p>
 * This class is used to store various arrays of filler data.
 * </p>
 * 
 * @author Christian Babin
 * @version 3.2.0
 */
public class FDF {
	
	// the locations file name
	private static final String LOCATION_FILE = "mixed_location_data.csv";
	
	// list of all locations
	private static ArrayList<ArrayList<Location>> locations;

	// list of planes and their passenger capacity - format: {plane_type, capacity}
	public static final String[][] PLANE_COLLECTION = {
			{"737-700", "126"},
			{"a319", "160"},
			{"757-200", "200"},
			{"767-300", "269"},
			{"MD-11", "350"},
			{"a340-600", "419"},
			{"a340-300", "440"},
			{"777-200", "440"},
			{"747-400", "524"},
			{"777-300ER", "550"},
			{"747-8i", "605"},
			{"a380-800", "868"}
	};
	
	// list of vehicles and their max passenger count (not including driver) - format: {vehicle_type, maxPassengers}
	public static final String[][] VEHICLE_COLLECTION = {
			{"Pickup Truck", "4"},
			{"Motorcycle", "1"},
			{"Sedan", "3"},
			{"Coupe", "3"},
			{"Sports Car", "1"},
			{"Station Wagon", "4"},
			{"Hatchback", "4"},
			{"Convertible", "1"},
			{"SUV", "6"},
			{"Micro Car", "1"},
			{"Supercar", "1"},
			{"Minivan", "6"},
			{"Van", "15"},
			{"18-Wheeler", "1"},
			{"School Bus", "90"},
			{"RV", "7"}
	};
	
	// a list of weather conditions
	public static final String[] WEATHER_CONDITIONS = {"clear", "rainy", "cloudy", "foggy", "stormy"};
	
	// list of crash causes
	public static final String[] CRASH_CAUSES = {"Humnan negligence", "Mechanical failure", "Unforeseeable accident"};
	
	public static ArrayList<ArrayList<Location>> getLocations() {
		
		// if the locations haven't been read in yet, read them in
		if(locations == null) {
			readLocations();
		}
		
		return locations;
		
	}
	
	/**
	 * This method reads in all of the locations from the locations file.
	 */
	private static void readLocations() {
		
		// the list of each line of the .csv file
		ArrayList<List<String>> allLines = CSV_Reader.read(LOCATION_FILE);
		
		// initialize the array list
		locations = new ArrayList<>();
		
		// add each line to the array list once it's converted into a location
		for(List<String> location : allLines) {
			Location currLocation = new Location(location.get(0), location.get(1), Integer.parseInt(location.get(2)), Double.parseDouble(location.get(3)), Double.parseDouble(location.get(4)), Double.parseDouble(location.get(5)), Double.parseDouble(location.get(6)));
			
			// add the location to the array list based on country
			boolean added = false;
			for(ArrayList<Location> countryGroup : locations) {
				if(countryGroup.get(0).getCountry().equals(location.get(1))) {
					countryGroup.add(currLocation);
					added = true;
					break;
				}
			}
			if(!added) {
				ArrayList<Location> temp = new ArrayList<>();
				temp.add(currLocation);
				locations.add(temp);
			}
		}
		
	}
	
}
