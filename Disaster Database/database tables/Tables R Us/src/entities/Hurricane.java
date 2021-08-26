package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.Tools;

/**
 * This class is for hurricane disaster objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Hurricane extends Disaster {
	
	private int category;
	private int windSpeed; //mph
	private int rainFall; // inches
	private int floodDepth; // inches
	private int diameter; // miles
	private LocalDateTime warningTime;
	private LocalDateTime endTime;
	private double pathLength; // miles
	private int buildingsDestroyed;
	
	public Hurricane(String ID) {
		
		super(ID);
		
	}
	
	public Hurricane(String ID, String startTime, ArrayList<double[]> path, int deaths, int permInjuries, int tempInjuries) {
		
		// string time
		super(ID, startTime, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Hurricane(String ID, LocalDateTime startTime, ArrayList<double[]> path, int deaths, int permInjuries, int tempInjuries) {
		
		// real time
		super(ID, startTime, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Hurricane(String ID, int category, int windSpeed, int rainFall, int floodDepth, int diameter, String warningTime, String startTime, String endTime, ArrayList<double[]> path, double pathLength, int deaths, int permInjuries, int tempInjuries, int buildingsDestroyed) {
		
		// string time
		this(ID, startTime, path, deaths, permInjuries, tempInjuries);
		
		setCategory(category);
		setWindSpeed(windSpeed);
		setRainFall(rainFall);
		setFloodDepth(floodDepth);
		setDiameter(diameter);
		setWarningTime(warningTime); // string time
		setEndTime(endTime); // string time
		setPathLength(pathLength);
		setBuildingsDestroyed(buildingsDestroyed);
		
	}
	
	public Hurricane(String ID, int category, int windSpeed, int rainFall, int floodDepth, int diameter, LocalDateTime warningTime, LocalDateTime startTime, LocalDateTime endTime, ArrayList<double[]> path, double pathLength, int deaths, int permInjuries, int tempInjuries, int buildingsDestroyed) {
		
		// real time
		this(ID, startTime, path, deaths, permInjuries, tempInjuries);
		
		setCategory(category);
		setWindSpeed(windSpeed);
		setRainFall(rainFall);
		setFloodDepth(floodDepth);
		setDiameter(diameter);
		setWarningTime(warningTime); // real time
		setEndTime(endTime); // real time
		setPathLength(pathLength);
		setBuildingsDestroyed(buildingsDestroyed);
		
	}
	
	
	public void setCategory(int category) {
		
		this.category = category;

	}

	public void setWindSpeed(int windSpeed) {
	
		this.windSpeed = windSpeed;
	
	}

	public void setRainFall(int rainFall) {
	
		this.rainFall = rainFall;
	
	}

	public void setFloodDepth(int floodDepth) {
	
		this.floodDepth = floodDepth;
	
	}

	public void setDiameter(int diameter) {
	
		this.diameter = diameter;
	
	}

	public void setWarningTime(LocalDateTime warningTime) {
	
		this.warningTime = warningTime;
	
	}
	
	// this sets warning time when a string is provided
	public void setWarningTime(String warningTime) {
		
		this.warningTime = Tools.convertTime(warningTime);
	
	}

	public void setEndTime(LocalDateTime endTime) {
	
		this.endTime = endTime;
	
	}
	
	// this sets end time when a string is provided
	public void setEndTime(String endTime) {
		
		this.endTime = Tools.convertTime(endTime);
	
	}

	public void setPathLength(double pathLength) {
	
		this.pathLength = pathLength;
	
	}

	public void setBuildingsDestroyed(int buildingsDestroyed) {
	
		this.buildingsDestroyed = buildingsDestroyed;
	
	}

	public int getCategory() {
	
		return category;
	
	}

	public int getWindSpeed() {
	
		return windSpeed;
	
	}

	public int getRainFall() {
	
		return rainFall;
	
	}

	public int getFloodDepth() {

		return floodDepth;
	
	}

	public int getDiameter() {
	
		return diameter;
	
	}

	public LocalDateTime getWarningTime() {
	
		return warningTime;
	
	}

	public LocalDateTime getEndTime() {
	
		return endTime;
	
	}

	public double getPathLength() {
	
		return pathLength;
	
	}

	public int getBuildingsDestroyed() {
	
		return buildingsDestroyed;

	}

}
