//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 4
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that tests the Restaurant class and runs the simulation
 */
public class DiningSimulator 
{
	private ArrayList<Restaurant> restaurants;
	private int chefs;
	private int duration;
	private double arrivalProb;
	private int maxCustomerSize;
	private int numRestaurants;
	private int customersLost;
	private int totalServiceTime;
	private int customersServed;
	private int profit;
	
	public DiningSimulator(int numRest, int maxSize, double prob, int chf, int durat)
	{
		restaurants = new ArrayList<Restaurant>();
		numRestaurants = numRest;
		maxCustomerSize = maxSize;
		arrivalProb = prob;
		chefs = chf;
		duration = durat;
	}
	
	/**
	 * The main method that tests the Restaurant class and runs the simulation
	 * @param args
	 * 		  not used
	 */
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		String choice = "P";
		System.out.println("Starting simulator... ");
		do
		{
			if(!choice.equals("N"))
			{
				try
				{
					System.out.println();
					System.out.print("Enter the number of restaurants: ");
					int num = in.nextInt();
					System.out.print("Enter the maximum number of customers a restaurant can serve: ");
					int max = in.nextInt();
					System.out.print("Enter the arrival probability of a customer: ");
					double arrival = in.nextDouble();
					if(arrival < 0.0 || arrival > 1.0)
					{
						throw new IllegalArgumentException();
					}
					System.out.print("Enter the number of chefs: ");
					int chef = in.nextInt();
					if(chef < 1)
					{
						throw new IllegalArgumentException();
					}
					System.out.print("Enter the number of simulation units: ");
					int dur = in.nextInt();
					System.out.println();
					DiningSimulator sim = new DiningSimulator(num,max,arrival,chef,dur);
					double average = sim.simulate();
					System.out.println("Simulation ending...");
					System.out.println();
					System.out.println("Total customer time: " + sim.getTotalTime() + " minutes");
					System.out.println("Total customers served: " + sim.getCustomersServed());
					String avg = String.format("%.2f",average);
					System.out.println("Average customer time lapse: " + avg + " minutes per order");
					System.out.println("Total Profit: $" + sim.getTotalProfit());
					System.out.println("Customers that left: " + sim.getCustomersLeft());
					System.out.println();
					System.out.print("Do you want to try another simulation? (y/n): ");
					choice = in.next().toUpperCase();
				}
				catch(InputMismatchException e)
				{
					System.out.println("Invalid Input");
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Invalid Input");
				}
			}
		}
		while(!choice.equals("N"));
		System.out.println();
		System.out.println("Program terminating normally...");
	}
	
	
	/**
	 * A method that runs the simulator
	 * @return
	 * 		 the average time the customer spent at the restaurant
	 */
	public double simulate()
	{
		double average = 0.0;
		for(int i = 0; i < numRestaurants; i++)
		{
			restaurants.add(new Restaurant());
		}
		for(int i = 0; i < duration; i++)
		{
			System.out.println("Time: " + (i + 1));
			for(int j = 0; j < numRestaurants; j++)
			{
				int count = 0;
				do
				{
					int index = restaurants.get(j).size();
					if(index >= maxCustomerSize)
					{
						Customer left = new Customer(true);
						int orderNum = restaurants.get(j).getCustomer(index - 1).getOrderNum();
						System.out.println("Customer #" + orderNum + " cannot be seated at Restaurant " + (j + 1) + "! They have left the restaurant.");
					    customersLost++;
					    break;
					}
					else if(occurs())
					{
						restaurants.get(j).enqueue(new Customer(i));
						int orderNum = restaurants.get(j).getCustomer(index).getOrderNum();
						System.out.println("Customer #" + orderNum + " has entered Restaurant " + (j + 1) + ".");
						count++;
					}
					else
						break;
				}
				while(count < 3);
			}
			for(int j = 0; j < numRestaurants; j++)
			{
				for(int k = 0; k < restaurants.get(j).size(); k++)
				{
					if(!restaurants.get(j).getCustomer(k).receivedFood())
					{
					    int orderNum = restaurants.get(j).getCustomer(k).getCustomerNum();
						String food =  restaurants.get(j).getCustomer(k).setFood(randInt(1,5), chefs);
						System.out.println("Customer #" + orderNum + " has been seated with order " + "\"" + food  + "\"" + ".");
					}
					else if(restaurants.get(j).getCustomer(k).getTime() < 10)
					{
						Customer done = restaurants.get(j).dequeue();
						profit += done.getPrice();
						customersServed++; 
						totalServiceTime += done.getTotalTime();
						System.out.println("Customer #" + done.getCustomerNum() + " has enjoyed their food! " + "$" + done.getPrice() + " profit.");
					}
					else
					{
					    restaurants.get(j).getCustomer(k).setTime(restaurants.get(j).getCustomer(k).getTime() - 5);
						continue;
					}
				}
			}
			for(int j = 0; j < restaurants.size(); j++)
			{
				System.out.println("R" + (j + 1) + " : " + restaurants.get(j).toString());
			}
			System.out.println();
		}
		double served = customersServed;
		average = totalServiceTime / served;
		return average;
	}
	
	/**
	 * A method that calculates the probability of a customer
	 * entering the restaurant
	 * @param probability
	 * 		  the specified probability
	 * @return
	 * 		  whether or not the customer entered the restaurant
	 */
	public boolean occurs()
	{
		return (Math.random() < arrivalProb);
	}
	
	/**
	 * A helper method that can be used to generate a random number 
	 * between minVal and maxVal inclusively
	 * @param minVal
	 * 		  the specified min value
	 * @param maxVal
	 * 		  the specified max value
	 * @return
	 * 		  the randomly generated number
	 */
	public int randInt(int minVal, int maxVal)
	{
		return (int) (Math.random() * ((maxVal - minVal) + 1)) + minVal;
	}
	
	/**
	 * A method that returns the total service time
	 * @return
	 * 		 the total service time
	 */
	public int getTotalTime()
	{
		return totalServiceTime;
	}
	
	/**
	 * A method that returns the number of customers that 
	 * received their orders, paid, and left
	 * @return
	 * 		 the number of customers that received their orders, paid and left
	 */
	public int getCustomersServed()
	{
		return customersServed;
	}
	
	/**
	 * A method that returns the total profits
	 * @return
	 * 		 the total profits
	 */
	public int getTotalProfit()
	{
		return profit;
	}
	
	/**
	 * A method that returns the number of customers that left 
	 * the restaurant
	 * @return
	 * 		 the number of customers that left the restaurant
	 */
	public int getCustomersLeft()
	{
		return customersLost;
	}
	
}
