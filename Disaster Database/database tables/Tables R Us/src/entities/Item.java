package entities;

/**
 * This class is for disaster relief item objects.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class Item {
	
	private String ID;
	private String name;
	private String price; // USD
	
	public Item(String ID) {
		
		this.ID = ID;
		
	}
	
	public Item(String ID, String name, String price) {
		
		this(ID);
		
		setName(name);
		setPrice(price);
		
	}

	public void setName(String name) {
	
		this.name = name;
	
	}

	public void setPrice(String price) {
	
		this.price = price;
	
	}
	
	public String getID() {
	
		return ID;
	
	}

	public String getName() {
	
		return name;
	
	}

	public String getPrice() {
	
		return price;
	
	}

}
