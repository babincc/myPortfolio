package main;

import java.util.ArrayList;
import java.util.List;

import entities.Location;
import utils.CSV_Reader;
import utils.Country_Boundries;
import utils.Tools;

/**
 * <p>
 * This class is used to generate longitude and latitudes for each location. It
 * will go though list of locations and generate a unique longitude and latitude
 * pair for each of them. These entries are meant to be used for my CMSC 508
 * semester project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, you must first make sure you have the locations file you
 * want to read from in the "FILE_NAME" string below; the file must also be in
 * the "src" folder. Once that is done, run this file in the command
 * prompt/terminal. After it runs, it will have generated a new .csv file with
 * all of the locations and their new coordinates. NOTE: If the input file name
 * is "mixed_location_data.csv", it will be overridden by the file that this
 * program produces.
 * </p>
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Gen_A_Tude {
	
	// the name of the location file having coordinates added to it
	private static final String FILE_NAME = "frag_real_location_data.csv";
	
	// The column headers for the .csv file
	private static final String FILE_HEADER = "City,Country,Population,Min. Latitude,Max. Latitude,Min. Longitude,Max. Longitude\n";
	
	// A list of all the locations that have been read in, organized by country
	private static ArrayList<ArrayList<Location>> locations;

	public static void main(String[] args) {
		
		// initialize the array list
		locations = new ArrayList<>();
		
		// read the relief agency files
		readLocFile();
		
		// go through the locations and add the coordinates
		addCoordinates();
		
		// build the string that will be written to the file
		String allData = FILE_HEADER;
		for(ArrayList<Location> countryGroup : locations) {
			for(Location city : countryGroup) {
				allData += city.toString();
			}
		}
		
		// write to file
		Tools.writeLocFile(allData, "mixed_location_data.csv");

	}
	
	/**
	 * This method is used to read the location data into the program.
	 */
	private static void readLocFile() {
		
		// the list of each line of the .csv file
		ArrayList<List<String>> allLines = CSV_Reader.read(FILE_NAME);
		
		// add each line to the array list once it's converted into a location
		for(List<String> location : allLines) {
			Location currLocation = new Location(location.get(0), location.get(1), Integer.parseInt(location.get(2)));
			
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
	
	/**
	 * This method is used to add the coordinate ranges to each location.
	 */
	private static void addCoordinates() {
		
		// go through each country group and add the locations to each city in that country
		int index = 0;
		for(ArrayList<Location> countryGroup : locations) {
			
			// number of cities in this country
			int numCities = countryGroup.size();
			
			// the coordinate range of this country
			double[] countryRange = Country_Boundries.COUNTRIES[index];
			
			// a list of ranges that divides the country up into city segments
			double[][] cityRanges = genCityRanges(numCities, countryRange);
			
			// add the coordinate ranges to each city in this country
			int counter = 0;
			for(Location city : countryGroup) {
				city.setMinLatitude(Tools.round(cityRanges[counter][0], 6));
				city.setMaxLatitude(Tools.round(cityRanges[counter][1], 6));
				city.setMinLongitude(Tools.round(cityRanges[counter][2], 6));
				city.setMaxLongitude(Tools.round(cityRanges[counter][3], 6));
				
				// increment the counter to get the next city's coordinate range
				counter++;
			}
			
			// prepare for the next country
			index++;
		}
		
	}

	/**
	 * This method generates a list of ranges inside of the country range. It makes
	 * enough ranges for every city to have one.
	 * 
	 * @param numCities    - The number of cities in this country
	 * @param countryRange - // the coordinate range of each country, formatted:
	 *                     {latMin, latMax, longMin, longMax}
	 * @return A list (the size of numCities) of coordinate ranges that fit in this
	 *         country range. Each row formatted: {minLat, maxLat, minLong, maxLong}
	 */
	private static double[][] genCityRanges(int numCities, double[] countryRange) {
		
		double[][] cityRanges = new double[numCities][4];
		
		// the country range broken down
		double countryMinLat = countryRange[0];
		double countryMaxLat = countryRange[1];
		double countryMinLong = countryRange[2];
		double countryMaxLong = countryRange[3];
		
		// this will help disperse the ranges more evenly
		int across = ((int) Math.sqrt(numCities)) + 1;
		int down = across - 1;
		while(across * down < numCities) {
			across++;
		}
		
		// list of latitude ranges - every two values is a pair: {min1, max1, min2, max2, min3, max3, ...}
		ArrayList<Double> lats = new ArrayList<>();
		
		// list of longitude ranges - every two values is a pair: {min1, max1, min2, max2, min3, max3, ...}
		ArrayList<Double> longs = new ArrayList<>();
		
		// loop through each "column" of cities as they will be laid out in the country
		// and calculate the longitude ranges
		double spacing = (Math.abs(countryMaxLong-countryMinLong))/across; // width of each city
		for(int i = 0; i < across; i++) {
			
			// get this city's min long
			double cityMinLong = (i * spacing) + countryMinLong;
			
			// get the city's max long
			double cityMaxLong;
			if(i == across-1) {
				cityMaxLong = countryMaxLong;
			} else {
				cityMaxLong = (cityMinLong+spacing) - 0.000001;
			}
			
			longs.add(cityMinLong);
			longs.add(cityMaxLong);
		}
		
		// loop through each "row" of cities as they will be laid out in the country
		// and calculate the latitude ranges
		spacing = (Math.abs(countryMaxLat-countryMinLat))/down; // height of each city
		for(int i = 0; i < down; i++) {
			
			// get this city's min lat
			double cityMinLat = (i * spacing) + countryMinLat;
			
			// get the city's max lat
			double cityMaxLat;
			if(i == down-1) {
				cityMaxLat = countryMaxLat;
			} else {
				cityMaxLat = (cityMinLat+spacing) - 0.000001;
			}
			
			lats.add(cityMinLat);
			lats.add(cityMaxLat);
		}

		// loop through each city range
		int latPairIndex = 0; // goes through lats arrays twice as fast as this loop
		int longPairIndex = 0; // goes through longs arrays twice as fast as this loop
		for(int i = 0; i < numCities; i++) {
			
			// do min lat and min long
			cityRanges[i][0] = lats.get(latPairIndex);
			cityRanges[i][2] = longs.get(longPairIndex);
			
			// increase the pair index to get max value for this pair
			latPairIndex++;
			longPairIndex++;
			
			// do max lat and max long
			cityRanges[i][1] = lats.get(latPairIndex);
			cityRanges[i][3] = longs.get(longPairIndex);
			
			// increase the pair index to get min value for next pair
			if((latPairIndex+1) % (down*2) == 0) {
				latPairIndex = 0;
			} else {
				latPairIndex++;
			}
			if((longPairIndex+1) % (across*2) == 0) {
				longPairIndex = 0;
			} else {
				longPairIndex++;
			}
		}
		
		return cityRanges;
		
	}

}
