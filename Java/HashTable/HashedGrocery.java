//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 6
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.io.*;
import java.util.Hashtable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A class that will allow employees to perform different functions on the inventory of the grocery store
 */
public class HashedGrocery implements Serializable
{
	private int businessDay;
	private Hashtable<String,Item> hashTable;
	
	/**
	 * A Constructor that constructs a HashedGrocery object
	 */
	public HashedGrocery()
	{
		hashTable = new Hashtable<String,Item>();
		businessDay = 1;
	}
	
	/**
	 * A method that adds items to the hashTable 
	 * @param item
	 * 		  the item to add
	 * @throw An exception should be thrown if an item with the same item code already exists in the hash table
	 */
	public void addItem(Item item) throws ItemAlreadyExistsException
	{
	   if(hashTable.containsKey(item.getItemCode()))
	   {
		   System.out.println();
		   System.out.println(item.getItemCode() + ": Cannot add item as item code already exists.");
		   System.out.println();
		   throw new ItemAlreadyExistsException();
	   }
	   else
	   {
		   hashTable.put(item.getItemCode(),item);
		   System.out.println();
		   System.out.println(item.getItemCode() + ": " + item.getName() + " is added to inventory");
	   }
	}
	
	
	/**
	 * A method that changes the quantity in store amount of item by the adjust by quality
	 * @param item
	 *        the specified item to add
	 * @param adjustByQty
	 * 		  the amount of quality to adjust the item by
	 */
	public void updateItem(Item item, int adjustByQty)
	{
		hashTable.get(item.getItemCode()).setQuantity(adjustByQty);
	}
	
	/**
	 * A method that adds all the items present in the text file.
	 * @param filename
	 * 		  the specified text file name
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ItemAlreadyExistsException 
	 * @throws An exception thrown if filename does not exist
	 */
	public void addItemCatalog(String filename) throws FileNotFoundException, IOException, ParseException, ItemAlreadyExistsException
	{
		boolean duplicate = false;
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader in = new InputStreamReader(fis);
		JSONParser parser = new JSONParser();
		JSONArray objs = (JSONArray) parser.parse(in);
		for(int i = 0; i < objs.size(); i++)
		{
			JSONObject obj = (JSONObject) objs.get(i);
			String itemCode = (String) obj.get("itemCode");
			String itemName = (String) obj.get("itemName");
			String avg = (String) obj.get("avgSales");
			int average = Integer.parseInt(avg);
			String quantityFood = (String) obj.get("qtyInStore");
			int quantity = Integer.parseInt(quantityFood);
			String priceFood = (String) obj.get("price");
			double price = Double.parseDouble(priceFood);
			String order = (String) obj.get("amtOnOrder");
			int onOrder = Integer.parseInt(order);
			for(String key : hashTable.keySet())
			{
				if(key.equals(itemCode))
				{
					duplicate = true;
					break;
				}
			}
			if(!duplicate)
			{
				Item item = new Item(itemCode,itemName,quantity,average,onOrder,price);
				hashTable.put(itemCode, item);
				System.out.println(item.getItemCode() + ": " + item.getName() + " is added to inventory");
			}
			else
			{
				System.out.println(itemCode + ": Cannot add item as item code already exists");
				throw new ItemAlreadyExistsException();
			}
		}
	}
	
	/**
	 * A method that processes the file name to see which items have been sold that day.
	 * @param filename
	 * 		  the specified text file name
	 * @throws ParseException 
	 * @throws FileNotFoundException, IOException 
	 * @throws An exception thrown if filename does not exist
	 */ 
	public void processSales(String filename) throws IOException, ParseException
	{
		boolean found = false;
		FileInputStream fis = new FileInputStream(filename);
		InputStreamReader in = new InputStreamReader(fis);
		JSONParser parser = new JSONParser();
		JSONArray objs = (JSONArray) parser.parse(in);
		for(int i = 0; i < objs.size(); i++)
		{
			JSONObject obj = (JSONObject) objs.get(i);
			String code = (String) obj.get("itemCode");
			String qty = (String) obj.get("qtySold");
			int quantity = Integer.parseInt(qty);
			for(String itemCode : hashTable.keySet())
			{
				if(itemCode.equals(code))
				{
					found = true;
					break;
				}
				found = false;
			}
			if(found)
			{
				Item item = hashTable.get(code);
				if(item.getQuantity() < quantity)
					System.out.println(code +  ": Not enough stock for sale. Not updated");
				else
				{
					updateItem(item,item.getQuantity() - quantity);
					if(item.getOrders() > 0)
					{
						System.out.print(" Order already placed. Order not processed");
						System.out.println();
					}
					else if(item.getQuantity() < 3 * item.getAverageSales())
					{
						item.setRestock(2 * item.getAverageSales());
						item.setArrivalDay(businessDay + 3);
						System.out.print(code + ": " + quantity + " units of " + item.getName() +  " are sold.");
						System.out.print(" Order has been placed for " + item.getOrders() + " more units.");
						System.out.println();
					}
					else
						System.out.println(code + ": " + quantity + " units of " + item.getName() +  " are sold.");
				}
			}
			else
				System.out.println(code + ": Cannot buy as it is not in the grocery store.");
		}
	}
	
	/**
	 * A method that increases the business day by 1
	 */
	public void nextBusinessDay()
	{
		int count = 0;
		businessDay++;
		System.out.println("Business Day " + businessDay);
		System.out.println();
		for(String itemCode : hashTable.keySet())
		{
			if(hashTable.get(itemCode).getArrivalDay() == businessDay)
			{
				count++;
				if(count == 1)
				{
					System.out.println("Orders have arrived for: ");
					System.out.println();
				}
				Item item = hashTable.get(itemCode);
				updateItem(item,item.getQuantity() + item.getOrders());
				System.out.println(item.getItemCode() + ": " + item.getOrders() + " units of " + item.getName() + ".");
				item.setRestock(0);
				item.setArrivalDay(0);
			}
		}
		if(count == 0)
		{
			System.out.println("No orders have arrived.");
			System.out.println();
		}
		else
			System.out.println();
	}
	
	/**
	 * A method that gets the current business day
	 * @return
	 * 		  the current business day
	 */
	public int getBusinessDay()
	{
		return businessDay;
	}
	
	/**
	 * A method that prints all items in a neatly formatted table
	 * @return 
	 * 		  a neatly formatted table of all items in a String format
	 */
	public String toString()
	{
		String table = "";
		table += "Item code   Name                Qty   AvgSales   Price    OnOrder    ArrOnBusDay\r\n" + 
				"--------------------------------------------------------------------------------\n";
		for(String itemCode : hashTable.keySet())
			table += hashTable.get(itemCode).toString() + "\n";
		return table;
	}
}
