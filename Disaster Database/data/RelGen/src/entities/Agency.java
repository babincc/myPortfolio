package entities;

import java.time.LocalDateTime;

import utils.Tools;

/**
 * This class is used as an object to represent the necessary data from each
 * disaster relief agency.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Agency {
	
	private int id;
	private LocalDateTime founded;
	
	public Agency(int id, String founded) {
		
		setId(id);
		setFounded(founded);
		
	}
	
	public void setId(int id) {
	
		this.id = id;
	
	}

	public void setFounded(LocalDateTime founded) {
	
		this.founded = founded;
	
	}
	
	// set founded when provided with a string
	public void setFounded(String founded) {
		
		founded = "Dec. 31, " + founded + " - 23:59:59";
		
		this.founded = Tools.convertTime(founded);
	
	}

	public int getId() {
	
		return id;
	
	}

	public LocalDateTime getFounded() {
	
		return founded;
	
	}

}
