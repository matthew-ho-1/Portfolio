//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.util.*;

/**
 * A Blood Type Comparator class that will be used to sort the patients by blood type
 */
public class BloodTypeComparator implements Comparator<Patient>
{
	/**
	 * A method that will compare two patients based on their blood type
	 * @param p1
	 * 		  the first specified patient to compare
	 * @param p2
	 * 		  the second specified patient to compare
	 * @return 
	 * 		 the value returned by comparing the two patients by blood type
	 */
	public int compare(Patient p1, Patient p2)
	{
		return (p1.getBloodType().getBloodType().compareTo(p2.getBloodType().getBloodType()));
	}
}
