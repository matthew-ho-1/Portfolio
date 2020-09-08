//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 4
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

import java.util.ArrayList;
/**
 * A class that will take Customers and seat them
 */
public class Restaurant extends ArrayList<Customer> 
{
   private ArrayList<Customer> restaurant;
   
  /**
   * A constructor that constructs the Restaurant
   */
   public Restaurant()
   {
	   restaurant = new ArrayList<Customer>();
   }
   
   /**
    * A method that adds new customer to the restaurant
    * @param c
    * 		 the specified new customer
    */
   public void enqueue(Customer c)
   {
	   restaurant.add(c);
   }
   
   /**
    * A method that removes and returns the first customer in the restaurant
    * @return
    * 		 the first customer in the restaurant
    */
   public Customer dequeue()
   {
	   return restaurant.remove(0);
   }
   
   /**
    * A method that returns but does not remove the first customer in the restaurant
    * @return
    * 		 the first customer in the restaurant
    */
   public Customer peek()
   {
	  return restaurant.get(0);
   }
   
   /**
    * A method that returns a Customer object at a specified index
    * @param index
    * 		 the specified index to look for
    * @return
    */
   public Customer getCustomer(int index)
   {
	   return restaurant.get(index);
   }
   
   
   /**
    * A method that returns the number of customers in the restaurant
    * @return
    * 		the number of customers in the restaurant 
    */
   public int size()
   {
	   return restaurant.size();
   }
   
   /**
    * A method to check whether there are customers in the restaurant
    * @return
    * 		 whether or not there are customers in the restaurant
    */
   public boolean isEmpty()
   {
	   return restaurant.isEmpty();
   }
   
   /**
    * A method that returns a String representation of this Restaurant 
    * @return 
    * 		 the String representation of this Restaurant 
    */
   public String toString()
   {
	   String rest = "{";
	   for(int i = 0; i < size(); i++)
	   {
		   if(i < size() - 1)
			   rest += restaurant.get(i).toString() + ", ";
		   else
			   rest += restaurant.get(i).toString();
	   }
	   rest += "}"; 
	   return rest;
   }
   
   
}
