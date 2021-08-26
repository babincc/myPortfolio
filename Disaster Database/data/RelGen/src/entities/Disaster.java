package entities;

import java.time.LocalDateTime;

import utils.Tools;

/**
 * This class is used as an object to represent the necessary data from each
 * disaster.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Disaster {

	private String disasterID;		// The unique ID of the disaster
	private LocalDateTime start;	// When the disaster started
	private LocalDateTime end;		// When the disaster ended
	private int severity;			// How bad the disaster was 0-5
	
	public Disaster(String id, int severity) {
		
		setId(id);
		setSeverity(severity);
		
	}
	
	public Disaster(String id, String start, String end, int severity) {
		
		this(id, severity);
		
		// String objects
		setStart(start);
		setEnd(end);
		
	}
	
	public Disaster(String id, LocalDateTime start, LocalDateTime end, int severity) {
		
		this(id, severity);
		
		// LocalDateTime objects
		setStart(start);
		setEnd(end);
		
	}
	
	public void setId(String disasterID) {
	
		this.disasterID = disasterID;
	
	}
	
	public void setStart(LocalDateTime start) {
	
		this.start = start;
	
	}
	
	// this sets start time when a string is provided
	public void setStart(String start) {
		
		this.start = Tools.convertTime(start);
	
	}
	
	public void setEnd(LocalDateTime end) {
	
		this.end = end;
	
	}
	
	// this sets end time when a string is provided
	public void setEnd(String end) {
	
		this.end = Tools.convertTime(end);
	
	}
	
	public void setSeverity(int severity) {
	
		this.severity = severity;
	
	}
	
	public String getId() {
	
		return disasterID;
	
	}
	
	public LocalDateTime getStart() {
	
		return start;
	
	}
	
	public LocalDateTime getEnd() {
	
		return end;
	
	}
	
	public int getSeverity() {
	
		return severity;
	
	}
	
}
