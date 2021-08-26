package entities;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * This class is used as an object to represent the necessary data from each
 * supply item.
 * 
 * @author Christian Babin
 * @version 1.0.0
 */
public class SupplyItem {

	private String id;		// the ID of the item
	private String name;	// the common name/descriptor of the item
	private double price;	// the price of the item
	
	public SupplyItem(String id, String name, String price) {
		
		setId(id);
		setName(name);
		setPrice(price);
		
	}

	public void setId(String id) {
	
		this.id = id;
	
	}

	public void setName(String name) {
	
		this.name = name;
	
	}

	public void setPrice(double price) {
	
		this.price = price;
	
	}
	
	// set the price when provided with a string
	public void setPrice(String price) {
		
		// get rid of dollar signs and commas
		NumberFormat format = NumberFormat.getCurrencyInstance();
		Number number = null;
		try {
			number = format.parse(price);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		price = number.toString();
		
		// set the price by converting the new string to a double
		this.price = Double.parseDouble(price);
	
	}
	
	public String getId() {
	
		return id;
	
	}

	public String getName() {
	
		return name;
	
	}

	public double getPrice() {
	
		return price;
	
	}
	
}
