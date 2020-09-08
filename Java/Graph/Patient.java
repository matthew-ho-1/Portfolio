//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.io.Serializable;
import java.util.*;

/**
 * A class that represents an active organ donor or recipient in the database
 */
public class Patient implements Comparable, Serializable
{
	private String name;
	private String organ;
	private int age;
	private BloodType bloodType;
	private int ID;
	private boolean isDonor;
	private ArrayList<Integer> connectionsList;
	
	/**
	 * A constructor for Patient
	 * @param n
	 * 		  the specified patient name
	 * @param o
	 * 		  the specified patient's organ
	 * @param a
	 * 		  the specified patient's age
	 * @param id
	 * 	      the specified patient's id
	 * @param bType
	 * 		  the specified patient's blood type
	 * @param donor
	 * 		  whether or not the patient is a donor 
	 */
	public Patient(String n, String o, int a, int id, BloodType bType, boolean donor)
	{
		name = n;
		organ = o;
		age = a;
		ID = id;
		isDonor = donor;
		bloodType = bType;
		connectionsList = new ArrayList<Integer>();
	}
	
	/**
	 * A method that gets the name of the patient
	 * @return
	 * 		  the name of the patient
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * A method that gets the blood type of the patient
	 * @return
	 * 		  the blood type of the patient
	 */
	public BloodType getBloodType()
	{
		return bloodType;
	}
	
	/**
	 * A method that gets the organs of the patient
	 * @return
	 * 		 the organs of the patient
	 */
	public String getOrgan()
	{
		return organ;
	}
	
	/**
	 * A method that adds the donor or recipient IDs
	 * to the patient's connection list
	 * @param id
	 * 		  the specified donor or recipient ID
	 */
	public void addID(int id)
	{
		connectionsList.add(id);
	}
	
	/**
	 * A method that clears the patient's connection list
	 */
	public void clearID()
	{
		connectionsList.clear();
	}
	
	/**
	 * A method that sets the ID for the patient
	 * @param id
	 * 		  the specified ID for the patient
	 */
	public void setID(int id)
	{
		ID = id;
	}
	
	/**
	 * A method that gets the number of connections that 
	 * a patient may have
	 * @return
	 * 		  the number of connections that a patient may have
	 */
	public int getNumConnections()
	{
		return connectionsList.size();
	}
	
	
	/**
	 * A method compares this patient object to o, comparing by ID such 
	 * that the values should be sorted in ascending order.
	 * @param o
	 * 		  the specified object to compare
	 * @return
	 */
	public int compareTo(Object o)
	{
		Patient other = (Patient) o;
		if(ID == other.ID)
			return 0;
		else if(ID > other.ID)
			return 1;
		else
			return -1;
	}
	
	/**
	 * A method that returns the String representation of this patient object
	 * @return 
	 * 		  String representation of this patient object
	 */
	public String toString()
	{
		String format = "";
		String id = "";
		for(int i = 0; i < connectionsList.size(); i++)
		{
			if(i == connectionsList.size() - 1)
				id += connectionsList.get(i);
			else
				id += connectionsList.get(i) + ", ";
		}
		format += String.format("%2s %-3s| %-17s  | %-2d  |%1s %-12s |  %2s %-5s | %-10s ", "" , ID, name, age, "", organ, "", bloodType.getBloodType(), id);
		return format;
	}
}
