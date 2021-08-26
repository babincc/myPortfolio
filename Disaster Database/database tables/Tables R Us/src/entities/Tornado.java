package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.Tools;

/**
 * This class is for tornado disaster objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Tornado extends Disaster {
	
	private int EF;
	private int windSpeed; //mph
	private int diameter; // meters
	private LocalDateTime warningTime;
	private LocalDateTime endTime;
	private double pathLength; // miles
	private int buildingsDestroyed;
	
	public Tornado(String ID) {
		
		super(ID);
		
	}
	
	public Tornado(String ID, String startTime, ArrayList<double[]> path, int deaths, int permInjuries, int tempInjuries) {
		
		// string time
		super(ID, startTime, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Tornado(String ID, LocalDateTime startTime, ArrayList<double[]> path, int deaths, int permInjuries, int tempInjuries) {
		
		// real time
		super(ID, startTime, deaths, permInjuries, tempInjuries, path);
		
	}
	
	public Tornado(String ID, int EF, int windSpeed, int diameter, LocalDateTime warningTime, LocalDateTime startTime, LocalDateTime endTime, ArrayList<double[]> path, double pathLength, int deaths, int permInjuries, int tempInjuries, int buildingsDestroyed) {
		
		// string time
		this(ID, startTime, path, deaths, permInjuries, tempInjuries);
		
		setEF(EF);
		setWindSpeed(windSpeed);
		setDiameter(diameter);
		setWarningTime(warningTime); // string time
		setEndTime(endTime); // string time
		setPathLength(pathLength);
		setBuildingsDestroyed(buildingsDestroyed);
		
	}
	
	public Tornado(String ID, int EF, int windSpeed, int diameter, String warningTime, String startTime, String endTime, ArrayList<double[]> path, double pathLength, int deaths, int permInjuries, int tempInjuries, int buildingsDestroyed) {
		
		// real time
		this(ID, startTime, path, deaths, permInjuries, tempInjuries);
		
		setEF(EF);
		setWindSpeed(windSpeed);
		setDiameter(diameter);
		setWarningTime(warningTime); // real time
		setEndTime(endTime); // real time
		setPathLength(pathLength);
		setBuildingsDestroyed(buildingsDestroyed);
		
	}
	
	public void setEF(int EF) {
		
		this.EF = EF;

	}

	public void setWindSpeed(int windSpeed) {
	
		this.windSpeed = windSpeed;
	
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

	public int getEF() {
	
		return EF;
	
	}

	public int getWindSpeed() {
	
		return windSpeed;
	
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
