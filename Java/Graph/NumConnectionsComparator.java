//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.util.*;

/**
 * A Blood Type Comparator class that will be used to sort the patients by number
 * of connections
 */
public class NumConnectionsComparator implements Comparator<Patient>
{
   /**
	* A method that will compare two patients based on the number of connections
	* @param p1 
	* 		 the first specified patient to compare
	* @param p2
	* 		 the second specified patient to compare
	* @return 
	* 		 the value returned by comparing the two patients by the number of connections
	*/
	public int compare(Patient p1, Patient p2)
	{
	   if(p1.getNumConnections() == p2.getNumConnections())
		   return 0;
	   else if(p1.getNumConnections() > p2.getNumConnections())
		   return 1;
	   else
		   return -1;
	}
}
