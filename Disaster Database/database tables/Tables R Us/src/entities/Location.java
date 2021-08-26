package entities;

/**
 * This class is for location objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Location {

	private String city;
	private String country;
	private int population;
	private double minLat;
	private double maxLat;
	private double minLong;
	private double maxLong;
	
	public Location(String city, String country, int population, double minLat, double maxLat, double minLong, double maxLong) {

		this.city = city;
		this.country = country;
		this.population = population;
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLong = minLong;
		this.maxLong = maxLong;

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

	public double getMinLat() {
	
		return minLat;
	
	}

	public double getMaxLat() {
	
		return maxLat;
	
	}

	public double getMinLong() {
	
		return minLong;
	
	}

	public double getMaxLong() {
	
		return maxLong;
	
	}
	
}
