package entities;

import java.time.LocalDateTime;

import utils.Tools;

/**
 * This class is for disaster relief campaign objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Campaign {

	private String campaign_ID;
	private String agency_ID;
	private String disaster_ID;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Campaign(String campaign_ID) {
		
		this.campaign_ID = campaign_ID;
		
	}
	
	public Campaign(String campaign_ID, String agency_ID, String disaster_ID, String startTime, String endTime) {
		
		this(campaign_ID);
		
		setAgency_ID(agency_ID);
		setDisaster_ID(disaster_ID);
		setStartTime(startTime); // string time
		setEndTime(endTime); // string time
		
	}
	
	public Campaign(String campaign_ID, String agency_ID, String disaster_ID, LocalDateTime startTime, LocalDateTime endTIme) {
		
		this(campaign_ID);
		
		setAgency_ID(agency_ID);
		setDisaster_ID(disaster_ID);
		setStartTime(startTime); // real time
		setEndTime(endTime); // real time
		
	}

	public void setAgency_ID(String agency_ID) {
	
		this.agency_ID = agency_ID;
	
	}

	public void setDisaster_ID(String disaster_ID) {
	
		this.disaster_ID = disaster_ID;
	
	}

	public void setStartTime(LocalDateTime startTime) {
	
		this.startTime = startTime;
	
	}
	
	// this sets start time when a string is provided
	public void setStartTime(String startTime) {
		
		this.startTime = Tools.convertTime(startTime);
	
	}

	public void setEndTime(LocalDateTime endTime) {
	
		this.endTime = endTime;
	
	}
	
	// this sets end time when a string is provided
	public void setEndTime(String endTime) {
		
		this.endTime = Tools.convertTime(endTime);
	
	}
	
	public String getCampaign_ID() {
	
		return campaign_ID;
	
	}

	public String getAgency_ID() {
	
		return agency_ID;
	
	}

	public String getDisaster_ID() {
	
		return disaster_ID;
	
	}

	public LocalDateTime getStartTime() {
	
		return startTime;
	
	}

	public LocalDateTime getEndTime() {
	
		return endTime;
	
	}
	
}
