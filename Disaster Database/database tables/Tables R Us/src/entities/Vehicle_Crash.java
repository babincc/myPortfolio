package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is for vehicle crash disaster objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Vehicle_Crash extends Disaster {
	
	private String vehicleType;
	private int passengers; // num people in vehicle (minus driver)
	private String weather;
	private String cause;
	
	public Vehicle_Crash(String ID) {
		
		super(ID);
		
	}
	
	public Vehicle_Crash(String ID, int deaths, int permInjuries, int tempInjuries, String time, ArrayList<double[]> path) {
		
		// string time
		super(ID, time, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Vehicle_Crash(String ID, int deaths, int permInjuries, int tempInjuries, LocalDateTime time, ArrayList<double[]> path) {
		
		// real time
		super(ID, time, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Vehicle_Crash(String ID, String vehicleType, int passengers, int deaths, int permInjuries, int tempInjuries, String time, double[] location, String weather, String cause) {
		
		// string time
		this(ID, deaths, permInjuries, tempInjuries, time, null);
		
		setVehicleType(vehicleType);
		setPassengers(passengers);
		setLocation(location);
		setWeather(weather);
		setCause(cause);
		
	}
	
	public Vehicle_Crash(String ID, String vehicleType, int passengers, int deaths, int permInjuries, int tempInjuries, LocalDateTime time, double[] location, String weather, String cause) {
		
		// real time
		this(ID, deaths, permInjuries, tempInjuries, time, null);
		
		setVehicleType(vehicleType);
		setPassengers(passengers);
		setLocation(location);
		setWeather(weather);
		setCause(cause);
		
	}
	
	public void setVehicleType(String vehicleType) {
		
		this.vehicleType = vehicleType;
	
	}

	public void setPassengers(int passengers) {
	
		this.passengers = passengers;
	
	}

	public void setLocation(double[] location) {
	
		ArrayList<double[]> path = new ArrayList<>();
		
		path.add(location);
		
		super.setPath(path);
	
	}

	public void setWeather(String weather) {
	
		this.weather = weather;
	
	}

	public void setCause(String cause) {
	
		this.cause = cause;
	
	}
	
	public void setTime(LocalDateTime time) {
		
		super.setStartTime(time);
		
	}
	
	// this is for time provided as a string
	public void setTime(String time) {
		
		super.setStartTime(time);
		
	}
	
	public String getVehicleType() {
	
		return vehicleType;
	
	}

	public int getPassengers() {
	
		return passengers;
	
	}

	public double[] getLocation() {
	
		return super.getPath().get(0);
	
	}

	public String getWeather() {
	
		return weather;
	
	}

	public String getCause() {
	
		return cause;
	
	}
	
	public LocalDateTime getTime() {
		
		return super.getStartTime();
		
	}

}
