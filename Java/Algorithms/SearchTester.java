// Name: Matthew Ho
// Date: 4/11
// Period: 2nd

import java.util.Arrays;
import java.util.Scanner;

/**
 * A class to test the Searches and MyArray classes, which perform search and sort algorithms on 
 * integer arrays
 */
public class SearchTester
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in); 
		
		// creates an array with 30 random numbers < 100
		int[] numbers = new int[30];
		for(int i = 0; i < numbers.length; i++)
			numbers[i] = (int)(Math.random() * 100);
		
		// uses the	Arrays class to simply print the contents of the randomly generated array
		System.out.println(Arrays.toString(numbers));
		
		System.out.print("Enter a number to search for: ");
		int number = in.nextInt();
		
		int index = Searches.linearSearch(numbers, number);
		System.out.println ("Linear Search: " + number + " index is " + index);
		
		Arrays.sort(numbers);
		System.out.println (Arrays.toString(numbers));
		
		int index2 = Searches.binarySearch(numbers, number);
		System.out.println ("Binary Search: " + number + " index is " + index2);
		
	}
}