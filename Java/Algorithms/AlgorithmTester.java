// Name: Matthew Ho
// Date: 4/11
// Period: 2nd



// algorithmTester.java
// This program tests the efficiency of algorithms.


import java.util.*;

/**
 * A class to test the efficiency of algorithms
 */
public class AlgorithmTester
{
	public static void main(String args[]) 
	{
		System.out.println("\nAlgorithm Tester\n");
		Scanner input = new Scanner(System.in);
		TimeTest time = new TimeTest();
		System.out.print("Enter array size  ===>>  ");
		int size = input.nextInt();
		int[] list = new int[size];
		createList(list,size);
		System.out.println();
		// after you know your algorithm works, comment this out to time test with large sets
		// also comment out the printing of the array AFTER the sort
//		displayList(list);
		
		//////////
		time.startClock();
		
//		Sorts.bubbleSort(list);cccc
//		Sorts.smartBubbleSort(list);
//     	Sorts.selectionSort(list);
// 		Sorts.insertionSort(list);
//		Sorts.mergeSort(list);
//		Sorts.radixSort(list);
		Sorts.quickSort(list,0,list.length-1);
		time.stopClock();
		//////////
//		displayList(list);
		System.out.println();
		System.out.println(time);
	}
	
	/**
	 * Fills the specified array of the specified size with either
	 * random ascending or descending data
	 * @param list the array
	 * @param size the number of elements
	 */
	public static void createList (int[] list, int size)
	{
		Scanner input = new Scanner(System.in);
		boolean inputOK;
		String choice;
		
		do
		{
 			System.out.println("\n\nPlease choose the type of data list:\n");										
			System.out.println("{1} -- Random Order List");
			System.out.println("{2} -- Ascending Order List");
			System.out.println("{3} -- Descending Order List\n");
			System.out.print  ("Enter choice: -->  ");
			choice = input.nextLine();
			inputOK = ( choice.equals("1") || choice.equals("2") || choice.equals("3") );
			if (!inputOK)
		    	System.out.println("\nInput Error!  Please enter a number from 1 to 3.");
		}
		while (!inputOK);
		
		Random rand = new Random(1234);
		for (int k = 0; k < size; k++)
			switch (choice.charAt(0))
			{
				case '1' : list[k] = rand.nextInt(9000) + 1000; break;
				case '2' : list[k] = 1000 + k; break;
				case '3' : list[k] = 1000 + size - k;
			}	
	}

	/**
	 * Displays the specified array
	 * @param list the array
	 */
	public static void displayList(int[] list)
	{
		for (int k = 0; k < list.length; k++)
			System.out.print(list[k] + "  ");
		System.out.println("\n\n");
	}
	


}


