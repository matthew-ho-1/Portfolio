//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.util.*;

/**
 * An Organ Type Comparator class that will be used to sort the patients by organ
 */
public class OrganComparator implements Comparator<Patient>
{
   /**
	* A method that will compare two patients based on their organ type
	* @param p1
	* 		 the first specified patient to compare
	* @param p2
	* 		 the second specified patient to compare
	* @return 
	* 		the value returned by comparing the two patients by organ type
	*/
   public int compare(Patient p1, Patient p2)
   {
	   return (p1.getOrgan().compareTo(p2.getOrgan()));
   }
}
