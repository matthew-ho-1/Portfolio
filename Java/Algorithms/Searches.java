// Name: Matthew Ho
// Date: 4/11
// Period: 2nd


/**
 * A class with utility methods to search and sort an integer array
 */
public class Searches
{
	public static int linearSearch(int[] list, int key)
	{
		for(int i = 0;i<list.length;i++)
		{
			if(list[i] == key)
				return i;
		}
		return -1;
	}
	
	public static int binarySearch(int[] list, int key)
	{
		int min = 0;
		int max = list.length-1;
		int mid = 0;
		
		while(max <= min)
		{
			mid = (max + min)/2;
			if(key == list[mid])
				return mid;
			if(key > list[mid])
				min = mid + 1;
			else
				max = mid - 1;
		}
		return -1;
	}
}