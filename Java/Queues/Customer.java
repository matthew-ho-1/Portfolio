//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 4
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

/**
 * 
 * A class that represents a customer that will dine at my restaurant.
 */
public class Customer 
{
   private static int totalCustomers = 0;
   private int orderNumber;
   private String food;
   private String foodFormatted;
   private int priceOfFood;
   private int timeArrived;
   private int timeToServe;
   private int totalTime;
   private boolean served;
   private boolean customerLeft;
   
   /**
    * A constructor that constructs a customer
    * @param time
    * 		 the time the customer arrived
    */
   public Customer(int time)
   {
	   timeArrived = time;
	   totalCustomers++;
	   orderNumber = totalCustomers;
	   served = false;
	   customerLeft = false;
   }
   
   /**
    * A constructor that constructs a customer that has left 
    * the restaurant
    * @param left
    * 		 whether or not the customer left the restaurant
    */
   public Customer(boolean left)
   {
	   totalCustomers++;
	   orderNumber = totalCustomers;
	   customerLeft = left;
   }
   
   /**
    * A method that returns the time the customer arrived
    * @return
    * 		 the time the customer arrived
    */
   public int getTimeArrived()
   {
	   return timeArrived;
   }
   
   /**
    * A method that returns the food the customer ordered
    * @return
    * 	     the food the customer ordered
    */
   public String getFood()
   {
	   return food;
   }
   
   /**
    * A method that returns whether or not the customer received their food
    * @return
    * 		 whether or not the customer received their food
    */
   public boolean receivedFood()
   {
	   return served;
   }
   
   /**
    * A method that returns the time that the customer has left in the restaurant
    * @return
    * 		 the time the customer has left in the restaurant
    */
   public int getTime()
   {
	   return timeToServe;
   }
   
   /**
    * A method that returns the total time that the customer is in the restaurant for
    * @return
    * 		 the total time that the customer is in the restaurant for
    */
   public int getTotalTime()
   {
	   return totalTime;
   }
   
   /**
    * A method that returns the price of the customer's food
    * @return
    * 		 the price of the customer's food
    */
   public int getPrice()
   {
	   return priceOfFood;
   }
   
   /**
    * A method that sets how long the customer has been in the restaurant for
    * @param time
    * 		 the specified time of how long the customer has been in the restaurant for
    */
   public void setTime(int time)
   {
	   timeToServe = time;
   }
   
   /**
    * A method that returns the customer's number
    * @return
    * 		 the customer's number
    */
   public int getCustomerNum()
   {
	   return orderNumber;
   }
   
   /**
    * A method that returns the customer's number out of all the customers
    * @return
    * 		 the customer's number out of all the customers
    */
   public int getOrderNum()
   {
	   return totalCustomers;
   }
   
   /**
    * A method that sets the food by a specified value and sets the time by how many
    * chefs are in the restaurant.
    * @param value
    * 		 the specified value in which the food is chosen by
    * @param chefs
    * 		the number of chefs in the restaurant
    * @return
    * 		the food that the customer has chosen
    */
   public String setFood(int value, int chefs)
   {
	   if(!customerLeft)
	   {
		   served = true;
		   if(value == 1)
		   {
			  food = "Cheeseburger";
			  foodFormatted = "C";
			  timeToServe = 25;
			  priceOfFood = 15;
		   }
		   else if(value == 2)
		   {
			  food = "Steak";
			  foodFormatted = "S";
			  timeToServe = 30;
			  priceOfFood = 25;
		   }
		   else if(value == 3)
		   {
			  food = "Grilled Cheese";
			  foodFormatted = "GC";
			  timeToServe = 15;
			  priceOfFood = 10;
		   }
		   else if(value == 4)
		   {
			  food = "Chicken Tenders";
			  foodFormatted = "CT";
			  timeToServe = 25;
			  priceOfFood = 10;
		   }
		   else
		   {
			  food = "Chicken Wings";
			  foodFormatted = "CW";
			  timeToServe = 30;
			  priceOfFood = 20;
		   }
		   if(chefs > 3)
		   {
			   int timeDiff = (chefs - 3) * 5;
			   timeToServe -= timeDiff;
		   }
		   if(chefs < 3)
		   {
			   int timeDiff = (3 - chefs) * 5;
			   timeToServe += timeDiff;
		   }
		   timeToServe += 15;
		   totalTime = timeToServe;
	   }
	   return food;
   }
  
   /**
    * A method that returns a String representation of a customer
    * @return
    * 		 the String represenation of a customer
    */
   public String toString()
   {
	   String customer = "";
	   customer += "[#" + orderNumber + " , "  + foodFormatted+ " , " + timeToServe + " min.]" ;
	   return customer;
   }
}
