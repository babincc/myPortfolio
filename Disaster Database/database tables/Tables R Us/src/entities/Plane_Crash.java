package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is for plane crash disaster objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Plane_Crash extends Disaster {
	
	private String planeType;
	private int souls; // num people on plane
	private String weather;
	private String cause;
	
	public Plane_Crash(String ID) {
		
		super(ID);
		
	}
	
	public Plane_Crash(String ID, int deaths, int permInjuries, int tempInjuries, String time, ArrayList<double[]> path) {
		
		// string time
		super(ID, time, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Plane_Crash(String ID, int deaths, int permInjuries, int tempInjuries, LocalDateTime time, ArrayList<double[]> path) {
		
		// real time
		super(ID, time, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Plane_Crash(String ID, String planeType, int souls, int deaths, int permInjuries, int tempInjuries, String time, double[] location, String weather, String cause) {
		
		// string time
		this(ID, deaths, permInjuries, tempInjuries, time, null);
		
		setPlaneType(planeType);
		setSouls(souls);
		setLocation(location);
		setWeather(weather);
		setCause(cause);
		
	}
	
	public Plane_Crash(String ID, String planeType, int souls, int deaths, int permInjuries, int tempInjuries, LocalDateTime time, double[] location, String weather, String cause) {
		
		// real time
		this(ID, deaths, permInjuries, tempInjuries, time, null);
		
		setPlaneType(planeType);
		setSouls(souls);
		setLocation(location);
		setWeather(weather);
		setCause(cause);
		
	}
	
	public void setPlaneType(String planeType) {
		
		this.planeType = planeType;
	
	}

	public void setSouls(int souls) {
	
		this.souls = souls;
	
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
	
	public String getPlaneType() {
	
		return planeType;
	
	}

	public int getSouls() {
	
		return souls;
	
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
