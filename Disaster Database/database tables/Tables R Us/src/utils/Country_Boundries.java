package utils;

/**
 * <h1>DEV NOTE: Be very careful editing this file. The order of country names
 * matters. Make sure you understand how the country name order works before
 * making changes.</h1>
 * 
 * <p>
 * This class is used to get the edges of the country in question. For the
 * purposes of this program, countries are considered to be rectangles.
 * </p>
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Country_Boundries {

	// the coordinate range of each country, formatted: {latMin, latMax, longMin, longMax}
	public static final double[] RUSSIA = {55.608334, 77.426304, 36.988038, 177.509152};
	public static final double[] CANADA = {49.648233, 69.861323, -140.825624, -56.386217};
	public static final double[] CHINA = {21.807656, 38.858775, 87.513403, 120.379473};
	public static final double[] UNITED_STATES = {30.599902, 49.648232, -123.986137, -70.726330};
	public static final double[] BRAZIL = {-21.866168, 3.690884, -73.063752, -35.185022};
	public static final double[] AUSTRALIA = {-38.965495, -11.186081, 113.680199, 152.703636};
	public static final double[] INDIA = {8.446027, 30.657691, 68.331320, 87.513402};
	public static final double[] ARGENTINA = {-51.131661, -21.866167, -69.935288, -55.299365};
	public static final double[] KAZAKHSTAN = {41.341187, 55.608333, 47.002501, 86.806708};
	public static final double[] ALGERIA = {19.185940, 36.808659, -8.588718, 11.626126};
	public static final double[] DR_CONGO = {-13.250998, 4.731212, 12.729829, 28.969402};
	public static final double[] GREENLAND = {60.169665, 83.095424, -56.386218, -20.427299};
	public static final double[] SAUDI_ARABIA = {17.610916, 31.060822, 35.470760, 54.015682};
	public static final double[] MEXICO = {15.393371, 30.599901, -116.004383, -87.521698};
	public static final double[] INDONESIA = {-10.068440, 8.446025, 95.908351, 140.645509};
	
	// all of the above arrays ---- DEV NOTE: THESE MUST BE IN THE ORDER IN WHICH THEY APPEAR ON THE LOCATIONS FILE
	public static final double[][] COUNTRIES = {RUSSIA, CANADA, CHINA, UNITED_STATES, BRAZIL, AUSTRALIA, INDIA, ARGENTINA, KAZAKHSTAN, ALGERIA, DR_CONGO, GREENLAND, SAUDI_ARABIA, MEXICO, INDONESIA};
	public static final String[] COUNTRY_NAMES = {"Russia", "Canada", "China", "United States", "Brazil", "Australia", "India", "Argentina", "Kazakhstan", "Algeria", "DR Congo", "Greenland", "Saudi Arabia", "Mexico", "Indonesia"};

	/**
	 * This method takes a coordinate pair and finds what country it is in.
	 * 
	 * @param location - coordinate pair - {lat, long}
	 * @return The name of the country where these coordinates can be found.
	 */
	public static String getCountry(double[] location) {
		
		double locLat = location[0];
		double locLong = location[1];
		
		int counter = 0;
		
		for(double[] range : COUNTRIES) {
			double minLat = range[0];
			double maxLat = range[1];
			double minLong = range[2];
			double maxLong = range[3];
			
			if(locLat >= minLat && locLat <= maxLat) {
				if(locLong >= minLong && locLong <= maxLong) {
					break;
				}
			}
			
			counter++;
		}
		
		// see if a country was found for the location
		if(counter >= COUNTRIES.length) {
			throw new IllegalStateException("ERROR: \"" + locLat + ", " + locLong + "\" not found in any country range!");
		}
		
		return COUNTRY_NAMES[counter];
		
	}
	
}
