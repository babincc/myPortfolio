package entities;

/**
 * This class is used to create location objects. They are used to keep track of
 * each location to add unique coordinates to each one.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Location {

	private String city;		// the smallest identifier for location
	private String country;		// the country the city is in (or state if it's America)
	private int population;		// how many people live in this city
	private double minLatitude;	// this location's minimum latitude (decimal form)
	private double maxLatitude;	// this location's maximum latitude (decimal form)
	private double minLongitude;// this location's minimum longitude (decimal form)
	private double maxLongitude;// this location's maximum longitude (decimal form)
	
	public Location(String city, String country, int population) {
		
		setCity(city);
		setCountry(country);
		setPopulation(population);
		
	}
	
	public void setCity(String city) {
		
		this.city = city;
	
	}

	public void setCountry(String country) {
	
		this.country = country;
	
	}

	public void setPopulation(int population) {
	
		this.population = population;
	
	}
	
	public void setMinLatitude(double minLatitude) {
		
		this.minLatitude = minLatitude;
		
	}
	
	public void setMaxLatitude(double latitude) {
		
		this.maxLatitude = latitude;
		
	}
	
	public void setMinLongitude(double minLongitude) {
		
		this.minLongitude = minLongitude;
		
	}
	
	public void setMaxLongitude(double maxLongitude) {
		
		this.maxLongitude = maxLongitude;
		
	}

	public String getCity() {
		
		return city;
	
	}

	public String getCountry() {
	
		return country;
	
	}

	public int getPopulation() {
	
		return population;
	
	}
	
	public double getMinLatitude() {
		
		return minLatitude;
	
	}
	
	public double getMaxLatitude() {
		
		return maxLatitude;
	
	}
	
	public double getMinLongitude() {
		
		return minLongitude;
	
	}
	
	public double getMaxLongitude() {
		
		return maxLongitude;
	
	}
	
	/**
	 * This method converts this location object into a string to be added to the
	 * .csv file.
	 * 
	 * @return The .csv optimized version of this location.
	 */
	@Override
	public String toString() {
		
		return "" + city + "," + country + "," + population + "," + minLatitude + "," + maxLatitude + "," + minLongitude + "," + maxLongitude + "\n";
		
	}
	
}
