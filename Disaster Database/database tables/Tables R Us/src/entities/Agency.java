package entities;

/**
 * This class is for disaster relief agency objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Agency {
	
	private String ID;
	private String name;
	private int founded; // year
	private boolean forProfit;
	private boolean govRun;
	
	public Agency(String ID) {
		
		this.ID = ID;
		
	}
	
	public Agency(String ID, String name, int founded, boolean forProfit, boolean govRun) {
		
		this(ID);
		
		setName(name);
		setFounded(founded);
		setForProfit(forProfit);
		setGovRun(govRun);
		
	}

	public void setName(String name) {
	
		this.name = name;
	
	}

	public void setFounded(int founded) {
	
		this.founded = founded;
	
	}

	public void setForProfit(boolean forProfit) {
	
		this.forProfit = forProfit;
	
	}

	public void setGovRun(boolean govRun) {
	
		this.govRun = govRun;
	
	}
	
	public String getID() {
	
		return ID;
	
	}

	public String getName() {

		return name;

	}

	public int getFounded() {
	
		return founded;
	
	}

	public boolean isForProfit() {
	
		return forProfit;
	
	}

	public boolean isGovRun() {
	
		return govRun;
	
	}

}
