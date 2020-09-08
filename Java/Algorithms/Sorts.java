// Name: Matthew Ho
// Date: 4/11
// Period: 2nd


// algorithmTester.java
// This program tests the efficiency of algorithms.


import java.util.*;

/**
 * A class containing the following sorting algorithms:
Dumb bubble
Smart bubble
Selection
Insertion
MergeSort (and Merge)
Quicksort // will learn later and add
Heap Sort // will learn later and add
Radix // will learn later and add

 */
public class Sorts
{
	public static void bubbleSort(int[] list)
	{
		for(int p = 1; p < list.length; p++)
		{
			for(int i = 0; i < list.length - p; i++)
			{
				if(list[i] > list[i+1])
				{
					int temp = list[i];
					list[i] = list[i+1];
					list[i+1] = temp;
				}
			}
		}
	}
	
	public static void smartBubbleSort(int[] list)
	{
		boolean sorted = false;
		int p = 1;
		while(!sorted)
		{
			sorted = true;
			for(int i = 0; i < list.length-p ;i++)
			{
				if(list[i]>list[i+1]) 
				{
					sorted = false;
					int temp = list[i];
					list[i] = list[i+1];
					list[i+1]=temp;
				}
			}
			p++;
		}
	}
	
	public static void selectionSort(int[] list)
	{
		for(int j= 0; j < list.length-1;j++)
		{
			int smallest = j;
			for(int i = j+1; i < list.length;i++)
			{
				if(list[i] < list[smallest]) 
				{
					smallest = i; 	
				}
			}
			int temp = list[j];
			list[j] = list[smallest];
			list[smallest] = temp;
		}
	}
	
	public static void insertionSort(int[] list)
	{
		for(int i = 1; i < list.length; i++)
		{
			int key = list[i];
			int pos = i;
			while( pos > 0 && list[pos-1] > key)
			{
				list[pos]=list[pos-1];
				pos--;
			}
			list[pos] = key;	
		}
	}
	
	 public static void mergeSort(int[] list)
	  {
	  	mergeSort(list,0,list.length-1);
	  }
	
	public static void mergeSort(int[] list, int min, int max)
	{
		if (min < max)
	    {
	      int middle = (min + max) / 2;
	      mergeSort(list, min, middle);
	      mergeSort(list,middle + 1, max);
	      merge(list, min, middle, middle + 1, max);
	    }
	}
	
	public static void merge(int[] list, int leftStart, int leftEnd, int rightStart, int rightEnd)
	{
		int[] temp = new int[list.length];
		int index = leftStart;
		int first = leftStart;
		while((leftStart <= leftEnd) && (rightStart <= rightEnd))
		{
			if (list[leftStart] < list[rightStart])
		      {
		        temp[index] = list[leftStart];
		        leftStart++;
		      }
		      else
		      {
		        temp[index] = list[rightStart];
		        rightStart++;
		      }
		      index++;
		}
		while (leftStart <= leftEnd)
		{
		   temp[index] = list[leftStart];
		   leftStart++;
		   index++;
		}
		while (rightStart <= rightEnd)
		{
		   temp[index] = list[rightStart];
		   rightStart++;
		   index++;
		}
		for (index = first; index <= rightEnd; index++)
		{
		      list[index] = temp[index];
		}
	}
	
	
	public static void radixSort(int[] nums) 
	{
		int numDigits = getMaxDigits(nums);
		
		for (int k = 0; k <= numDigits; k++) 
		{
			List<Queue<Integer>> qs = itemsToQueues(nums, k);
			queuesToArray(qs, nums);
		}
	}
	
	public static int getMaxDigits(int[] numbers)
	{
		int numDig = 0;
		for(int i:numbers) 
		{
			int temp = (int)Math.log10(i);
			if(temp>numDig)
				numDig = temp;
		}
		return numDig;
	}

	public static int getDigit(int number, int k) 
	{
		int num = number;
		int mult = (int) Math.pow(10,k);
		num/=mult;
		num%=10;
		return num;
	}
	
	public static List<Queue<Integer>> itemsToQueues(int[] nums, int k) 
	{
		List<Queue<Integer>> list = new ArrayList<Queue<Integer>>();
		for(int i = 0; i < 10; i++)
		{
			list.add(new LinkedList<Integer>());
		}
		for(int n:nums)
		{
			list.get(getDigit(n,k)).add(n);
		}
		return list;
	}
	
	public static void queuesToArray(List<Queue<Integer>> queues, int[] nums) 
	{
		int count = 0;
		for(int i = 0; i < queues.size(); i++)
		{
			for(int j:queues.get(i))
			{
				nums[count++] = j;
			}
		}
		queues.clear();
	}
	
	public static void quickSort(int[] list, int first, int last)
	{
		if(first < last)
		{
			int pivotVal = list[first];
			int pivotInd = first;
			for(int k = first+1; k <= last;k++)
			{
				if(list[k] <= pivotVal)
				{
					pivotInd++;
					int temp = list[k];
					list[k] = list[pivotInd];
					list[pivotInd] = temp;
				}
			}
			int temp2 = list[pivotInd];
			list[pivotInd] = list[first];
			list[first] = temp2;
			quickSort(list,first,pivotInd-1);
			quickSort(list,pivotInd+1,last);
		}
		
		
		
		
	}
	
	
}	


