//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 6
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.io.Serializable;

/**
 * A class that represents and contains information regarding an item
 */
public class Item implements Serializable
{
	private String itemCode;
	private String name;
	private int qtyInStore;
	private int averageSalesPerDay;
	private int onOrder;
	private int arrivalDay;
	private double price;
	
	/**
	 * A constructor that constructs the item object
	 * @param itemC
	 * 		  the specified item code
	 * @param itemN
	 * 		  the specified item name
	 * @param qty
	 * 		  the specified item quantity
	 * @param avg
	 * 		  the specified average sales per day
	 * @param order
	 * 		  the specified number ordered for restocking
	 * @param day
	 * 		  the specified arrival day
	 * @param prce
	 * 		  the specified price
	 * 		  
	 */
	public Item(String itemC, String itemN, int quantity,int avg, int order, double prce)
	{
		itemCode = itemC;
		name = itemN;
		averageSalesPerDay = avg;
		qtyInStore = quantity;
		onOrder = order;
		price = prce;
	}
	
	/**
	 * A method that gets the item code of an item
	 * @return
	 * 	      the item code of an item
	 */
	public String getItemCode()
	{
		return itemCode;
	}
	
	/**
	 * A method that gets the average sales per day of an item
	 * @return
	 * 		  the average sales per day of an item
	 */
	public int getAverageSales()
	{
		return averageSalesPerDay;
	}
	
	/**
	 * A method that gets the number of ordered items
	 * @return
	 * 		  the number of items ordered for restock
	 */
	public int getOrders()
	{
		return onOrder;
	}
	
	/**
	 * A method that gets the name of the item
	 * @return
	 * 		 the name of the item
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * A method that will set the number of restocked items
	 * @param restock
	 * 		  the specified number of restocked items
	 */
	public void setRestock(int restock)
	{
		onOrder = restock;
	}
	
	/**
	 * A method that gets the restock's arrival day
	 * @return
	 * 		  the restock's arrival day
	 */
	public int getArrivalDay()
	{
		return arrivalDay;
	}
	
	/**
	 * A method that sets the restock's arrival day
	 * @param arrival
	 * 		  the specified restock's arrival day
	 */
	public void setArrivalDay(int arrival)
	{
		arrivalDay = arrival;
	}
	
	/**
	 * A method that gets the item's quantity
	 * @return
	 * 		 the item's quantity
	 */
	public int getQuantity()
	{
		return qtyInStore;
	}
	
	/**
	 * A method that sets the quantity of the item to a 
	 * specified number
	 * @param quantity
	 * 		  the specified quantity of the item
	 */
	public void setQuantity(int quantity)
	{
		qtyInStore = quantity;
	}
	
	/**
	 * A method that returns a string representation of this item
	 * @return
	 * 		 a string representation of this item
	 */
	public String toString()
	{
		String item = "";
		item += String.format("%-11s %-19s %3d %10d %7.2f %10d %14d",itemCode, name, qtyInStore, averageSalesPerDay, price, onOrder, arrivalDay);
		return item;
	}

}
