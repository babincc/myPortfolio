package entities;

/**
 * This class is for disaster relief item objects that were actually used in
 * relief campaigns.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class SupplyItem {
	
	private String item_ID;
	private String name;
	private String price; // USD
	private int quantity;
	private String campaign_ID;
	
	public SupplyItem(String item_ID, String campaign_ID) {
		
		this.item_ID = item_ID;
		this.campaign_ID = campaign_ID;
		
	}
	
	public SupplyItem(String item_ID, String name, String price, int quantity, String campaign_ID) {
		
		this(item_ID, campaign_ID);
		
		setName(name);
		setPrice(price);
		setQuantity(quantity);
		
	}
	
	public void setName(String name) {
	
		this.name = name;
	
	}

	public void setPrice(String price) {
	
		this.price = price;
	
	}

	public void setQuantity(int quantity) {
	
		this.quantity = quantity;
	
	}

	public String getItem_ID() {
	
		return item_ID;
	
	}

	public String getName() {
	
		return name;
	
	}

	public String getPrice() {
	
		return price;
	
	}

	public int getQuantity() {
	
		return quantity;
	
	}

	public String getCampaign_ID() {
	
		return campaign_ID;
	
	}

}
