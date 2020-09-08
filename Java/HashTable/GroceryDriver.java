//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 6
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.io.*;
import org.json.simple.parser.ParseException;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * A class that will act as the main driver for the inventory management system.
 */
public class GroceryDriver 
{
	/**
	 * The main method for GroceryDriver
	 * @param args
	 * 		  not used
	 * @throws ItemAlreadyExistsException 
	 */
	public static void main(String[] args) throws ItemAlreadyExistsException 
	{
	   File file = new File("Grocery.obj");
	   Scanner in = new Scanner(System.in);
	   HashedGrocery grocery = new HashedGrocery();
	   String choice = "";
	   if(!file.exists())
	   {
		   System.out.println("Grocery.obj does not exist. Creating new HashedGrocery object...");
		   System.out.println();
		   System.out.println("Business Day " + grocery.getBusinessDay() + ".");
		   System.out.println();
	   }
	   else
	   {
		   System.out.println("HashedGrocery is loaded from grocery.obj");
		   try 
		   {
			   ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			   grocery = (HashedGrocery) input.readObject();
			   System.out.println();
			   System.out.println("Business Day " + grocery.getBusinessDay() + ".");
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   } 
		   catch (ClassNotFoundException e) 
		   {
			   e.printStackTrace();
		   }	
	   }
	   do
	   {
		   try
		   {
			   printMenu();
			   System.out.println();
			   System.out.print("Enter option: ");
			   choice = in.next().toUpperCase();
			   switch(choice)
			   {
			      case "L": System.out.println();
			      			System.out.print("Enter file to load: ");
			      			String file1 = in.next();
			      			System.out.println();
			      			grocery.addItemCatalog(file1);
			      			System.out.println();
			    	  		break;
			      case "A": System.out.println();
			      			System.out.print("Enter item code: ");
			      			String itemCode = in.next();
			      			in.nextLine();
			      			System.out.print("Enter item name: ");
			      			String itemName = in.nextLine();
			      			System.out.print("Enter Quantity in store: ");
			      			int quantity = in.nextInt();
			      			System.out.print("Enter Average sales per day: ");
			      			int average = in.nextInt();
			      			System.out.print("Enter price: ");
			      			double price = in.nextDouble();
			      			Item item = new Item(itemCode,itemName,quantity,average,0,price);
			      			grocery.addItem(item);
			      			System.out.println();
			    	  		break;
			      case "B": System.out.println();
			      			System.out.print("Enter filename: ");
			      			String file2 = in.next();
			      			System.out.println();
			      			grocery.processSales(file2);
			      			System.out.println();
			    	  		break;
			      case "C": System.out.println();
			      		    System.out.println(grocery.toString());
			      		    System.out.println();
			    	  		break;
			      case "N": System.out.println();
			    	  		System.out.println("Advancing business day...");
			      			grocery.nextBusinessDay();
			    	  		break;
			   }
		   }
		   catch(IOException e)
		   {
			   e.printStackTrace();
		   }
		   catch(ParseException e)
		   {
			   e.printStackTrace();
		   }
		   catch(ItemAlreadyExistsException e)
		   {
			   
		   }
	   }
	   while(!choice.equals("Q"));
	   if(!file.exists())
	   {
		   System.out.println();
		   System.out.println("HashedGrocery is saved in grocery.obj.");
		   try 
		   {
			   file.createNewFile();
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
	   }
	   try 
	   {
		   ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		   out.writeObject(grocery);
		   out.close();
	   }
	   catch (IOException e) 
	   {
		   e.printStackTrace();
	   }
	   System.out.println();
	   System.out.println("Program terminating normally...");
	   in.close();
	}
	
	/**
	 * A method that prints the menu
	 */
	public static void printMenu()
	{
		System.out.println("Menu :\r\n" + 
				" \r\n" + 
				"(L) Load item catalog    \r\n" + 
				"(A) Add items              \r\n" + 
				"(B) Process Sales      \r\n" + 
				"(C) Display all items\r\n" + 
				"(N) Move to next business day  \r\n" + 
				"(Q) Quit ");
	}

}
