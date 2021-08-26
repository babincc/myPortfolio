package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.Tools;

/**
 * This class is used as an object to represent the universal data from each
 * disaster. Each disaster is then broken into its own class to have its own
 * specific data.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Disaster {

	private String disasterID;			// The unique ID of the disaster
	private LocalDateTime startTime;	// When the disaster started
	private int deaths;
	private int permInjuries;
	private int tempInjuries;
	private ArrayList<double[]> path;	// latitude and longitude path
	
	public Disaster(String id) {
		
		this.disasterID = id;
		
		path = new ArrayList<>();
		
	}
	
	private Disaster(String id, int deaths, int permInjuries, int tempInjuries, ArrayList<double[]> path) {
		
		this(id);
		
		setDeaths(deaths);
		setPermInjuries(permInjuries);
		setTempInjuries(tempInjuries);
		setPath(path);
		
	}
	
	public Disaster(String id, String startTime, int deaths, int permInjuries, int tempInjuries, ArrayList<double[]> path) {
		
		this(id, deaths, permInjuries, tempInjuries, path);
		
		// String objects
		setStartTime(startTime);
		
	}
	
	public Disaster(String id, LocalDateTime startTime, int deaths, int permInjuries, int tempInjuries, ArrayList<double[]> path) {
		
		this(id, deaths, permInjuries, tempInjuries, path);
		
		// LocalDateTime objects
		setStartTime(startTime);
		
	}
	
	public void setStartTime(LocalDateTime startTime) {
	
		this.startTime = startTime;
	
	}
	
	// this sets start time when a string is provided
	public void setStartTime(String startTime) {
		
		this.startTime = Tools.convertTime(startTime);
	
	}
	
	public void setDeaths(int deaths) {
		
		this.deaths = deaths;
	
	}

	public void setPermInjuries(int permInjuries) {
	
		this.permInjuries = permInjuries;

	}

	public void setTempInjuries(int tempInjuries) {
	
		this.tempInjuries = tempInjuries;
	
	}
	
	public void setPath(ArrayList<double[]> path) {
		
		this.path = path;
		
	}
	
	public String getId() {
	
		return disasterID;
	
	}
	
	public LocalDateTime getStartTime() {
	
		return startTime;
	
	}
	
	public int getDeaths() {
		
		return deaths;
	
	}

	public int getPermInjuries() {
	
		return permInjuries;
	
	}

	public int getTempInjuries() {
	
		return tempInjuries;
	
	}
	
	public ArrayList<double[]> getPath() {
		
		return path;
		
	}
	
}
