package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.*;
import utils.CSV_Reader;
import utils.Tools;

/**
 * <p>
 * This class is used to generate relief efforts by different relief agencies.
 * It will go though lists of disasters and generate different relief effort
 * data entries for them. These entries are meant to be used for my CMSC 508
 * semester project and are not real data.
 * </p>
 * 
 * <p>
 * To use this program, you must first make sure you have all of the files you
 * want to read from in the "FILES" array below; the files must also be in the
 * "src" folder. You will also need to put the most up-to-date relief agency
 * file and supply items file in the "src" folder. Once that is done, run this
 * file in the command prompt/terminal and follow the instructions that it gives
 * you. After it runs, it will have generated two .csv files with all of your
 * newly generated disaster relief data for the disasters provided to it and the
 * supplies used in those relief efforts.
 * </p>
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Gen_A_Relief {
	
	// all of the disaster files that can be read from
	private static final String[] FILES = {
			"filler_hurricane_data.csv",
			"filler_plane-crash_data.csv",
			"filler_tornado_data.csv",
			"filler_vehicle-crash_data.csv"
	};
	
	// the file name for the relief agencies
	private static final String AGENCY_FILE = "real_relief-agency_data.csv";
	
	// the file name for the supply items
	private static final String SUPPLY_FILE = "real_supply-items_data.csv";
	
	// The column headers for the .csv files
	private static final String CAMPAIGN_FILE_HEADER = "Campaign ID,Agency ID,Disaster ID,Relief Start Date,Relief End Date\n";
	private static final String SUPPLY_FILE_HEADER = "Item ID,Name,Cost (per unit),Quantity,Campaign ID\n";
	
	// A list of all the relief agencies that have been read in
	private static ArrayList<Agency> agencies;
	
	// A list of all the relief items that have been read in (sorted by category)
	private static ArrayList<ArrayList<SupplyItem>> supplyItems;
	
	// A list of all the disasters that have been read in
	private static ArrayList<Disaster> disasters;
	
	// The string that will be written to the campaign file
	private static String allCampaignData;
	
	// The string that will be written to the supply file
	private static String allSupplyData;

	public static void main(String[] args) {
		
		// initialize the array lists
		agencies = new ArrayList<>();
		supplyItems = new ArrayList<>();
		disasters = new ArrayList<>();
		
		// initialize the 'all data' files
		allCampaignData = CAMPAIGN_FILE_HEADER;
		allSupplyData = SUPPLY_FILE_HEADER;
		
		// read the relief agency file
		readRelFile();
		
		// read the supply item file
		readSupFile();
		
		// read the disaster files
		readDisFiles();
		
		// generate the campaigns
		genCampaigns();
		
		// write the two .csv files
		Tools.writeLocFile(allCampaignData, "filler_relief-campaign_data.csv");
		Tools.writeLocFile(allSupplyData, "filler_relief-supply_data.csv");

	}
	
	/**
	 * This method is used to read the relief agency data into the program.
	 */
	private static void readRelFile() {
		
		// the list of each line of the .csv file
		ArrayList<List<String>> allLines = CSV_Reader.read(AGENCY_FILE);
		
		// add each agency ID to the agencies array list
		allLines.remove(0); // get rid of the header row
		for(List<String> agency : allLines) {
			Agency temp = new Agency(Integer.parseInt(agency.get(0)), agency.get(2));
			
			agencies.add(temp);
		}
		
	}
	
	/**
	 * This method is used to read the relief supply data into the program.
	 */
	private static void readSupFile() {
		
		// the list of each line of the .csv file
		ArrayList<List<String>> allLines = CSV_Reader.read(SUPPLY_FILE);
		
		// add each supply item to the array list
		allLines.remove(0); // get rid of the header row
		for(List<String> item : allLines) {
			SupplyItem currItem = new SupplyItem(item.get(0), item.get(1), item.get(2));
			
			// add the item to the array list based on category
			boolean added = false;
			for(ArrayList<SupplyItem> category : supplyItems) {
				
				// category code of this category
				String catCode = category.get(0).getId().substring(10);
				
				// check to see if the item belongs in this category
				if(catCode.equals(item.get(0).substring(10))) {
					category.add(currItem);
					added = true;
					break;
				}
			}
			if(!added) {
				ArrayList<SupplyItem> temp = new ArrayList<>();
				temp.add(currItem);
				supplyItems.add(temp);
			}
		}
		
	}
	
	/**
	 * This method is used to read the disaster data into the program.
	 */
	private static void readDisFiles() {
		
		for(String file : FILES) {
			
			// the list of each line of the .csv file
			ArrayList<List<String>> allLines = CSV_Reader.read(file);
			
			// important column 
			int ID_Col = -1;
			int start_Col = -1;
			int end_Col = -1;
			int severity_Col = -1;
			
			// got through each column title and find the important ones
			int counter = 0;
			for(String column : allLines.get(0)) {
				if(column.equals("ID")) {
					ID_Col = counter;
				} else if(column.contains("Start Time")) {
					start_Col = counter;
				} else if(column.contains("End Time")) {
					end_Col = counter;
				} else if(column.equals("Category") || column.equals("EF")) {
					severity_Col = counter;
				} else if(column.equals("Date - Time")) {
					start_Col = counter;
					end_Col = counter;
				}
				
				counter++;
			}
			
			// error checking
			if(ID_Col == -1 || start_Col == -1 || end_Col == -1) {
				throw new IllegalStateException("ERROR: None of these are allowed to be -1; however, these are the values:\n"
													+ "	idColIndex = " + ID_Col + "\n"
													+ "	startTimeColIndex = " + start_Col + "\n"
													+ "	endTimeColIndex = " + end_Col + "\n");
			}
			
			// add each line to the array list once it's converted into a disaster
			allLines.remove(0); // get rid of the header row
			for(List<String> disaster : allLines) {
				Disaster currDisaster;
				if(severity_Col == -1) {
					currDisaster = new Disaster(disaster.get(ID_Col), disaster.get(start_Col), disaster.get(end_Col), -1);
				} else {
					currDisaster = new Disaster(disaster.get(ID_Col), disaster.get(start_Col), disaster.get(end_Col), Integer.parseInt(disaster.get(severity_Col)));
				}
				
				// add the disaster to the disasters array list
				disasters.add(currDisaster);
			}
		}
		
	}
	
	/**
	 * This method goes through the disasters and generates relief campaigns for
	 * them.
	 */
	private static void genCampaigns() {
		
		// make a copy of the disasters list that can be destroyed
		ArrayList<Disaster> disastersTemp = new ArrayList<>();
		for(Disaster disaster : disasters) {
			String disID = disaster.getId();
			int index = disaster.getId().indexOf('-');
			
			if(disID.substring(0, disID.indexOf('-')).equals("H") || disID.substring(0, disID.indexOf('-')).equals("T")) {
				disastersTemp.add(disaster);
			}
		}
		int numDisasters = disastersTemp.size();
		int numAgencies = agencies.size();
		
		// campaign info
		int camp_idNum = 1100000;	// the number part of the campaign ID
		LocalDateTime start;		// the start time of the campaign
		LocalDateTime end;			// the end time of of the campaign
		
		// supply item info
		int quantity;				// how many of this item were bought in this campaign
		
		// how many disasters each agency should have
		int dividedUp = numDisasters / numAgencies;
		
		// go through each agency and add the campaigns
		for(Agency agency : agencies) {
			int numToCover = Tools.genRand(Math.max(0, dividedUp-100), Math.min(numDisasters, dividedUp+100));
			
			// indices of the disasters being helped by this agency
			int[] disIndices = Tools.genRand(0, disastersTemp.size()-1, Math.min(numToCover, disastersTemp.size()), false);
			
			// go through the randomly chosen disasters and add a campaign
			for(int index : disIndices) {
				Disaster temp = disastersTemp.get(index);
				
				// skip this disaster if it happened before the agency was founded
				if(agency.getFounded().isAfter(temp.getEnd())) {
					continue;
				}
				
				// generate start and end time
				start = temp.getEnd().plusDays(Tools.genRand(0, 7)).plusHours(Tools.genRand(0, 23)).plusMinutes(Tools.genRand(0, 59)).plusSeconds(Tools.genRand(0, 59));
				end = start.plusDays(Tools.genRand(5, 60)).plusHours(Tools.genRand(0, 23)).plusMinutes(Tools.genRand(0, 59)).plusSeconds(Tools.genRand(0, 59));
				
				// format start and end time for easy reading
				String startStr = Tools.convertTimeToStr(start);
				String endStr = Tools.convertTimeToStr(end);
				
				// Campaign ID,Agency ID,Disaster ID,Relief Start Date,Relief End Date
				allCampaignData += "RC-" + camp_idNum + "," + agency.getId() + "," + temp.getId() + ",\"" + startStr + "\",\"" + endStr + "\"\n";
				
				// make the items for this campaign to supply
				int[] catIndices = Tools.genRand(0, supplyItems.size()-1, Tools.genRand(1, supplyItems.size()), false);
				for(int i : catIndices) {
					int[] itemIndices = Tools.genRand(0, supplyItems.get(i).size()-1, Tools.genRand(1, supplyItems.get(i).size()), false);
					for(int j : itemIndices) {
						SupplyItem tempA = supplyItems.get(i).get(j);
						
						quantity = Tools.genRand(1, 30);
						
						// Item ID,Name,Cost (per unit),Quantity,Campaign ID
						allSupplyData += "" + tempA.getId() + ",\"" + tempA.getName() + "\",$" + tempA.getPrice() + "," + quantity + ",RC-" + camp_idNum + "\n";
					}
				}
				
				// increment to the next campaign
				camp_idNum++;
			}
			
			// loop back through the disasters we just went through and randomly delete most of them
			for(int index = disIndices.length-1; index >= 0; index--) {
				int delete = Tools.genRand(0, 10);
				
				if(delete != 1) {
					disastersTemp.remove(index);
				}
			}
		}
		
	}

}
