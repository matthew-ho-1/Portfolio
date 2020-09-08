//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 2
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

/**
 * A Train class that represents the basic information for a train approaching a
 * station. A Train has a train number, the train destination, the arrival time to its track,
 * and the transfer time for how long the train waits at the station.
 */
public class Train 
{
	private Train next;
	private Train prev;
	private int trainNumber;
	private String destination;
	private int arrivalTime;
	private int transferTime;
	
	/**
	 * A constructor that constructs a Train with default values
	 */
	public Train()
	{
		next = null;
		prev = null;
		trainNumber = 0;
		destination = "";
		arrivalTime = 0;
		transferTime = 0;
	}
	
	
	/**
	 * A constructor that constructs a Train with specified values
	 * @param n
	 * 		  the specified next Train 
	 * @param p
	 * 		  the specified previous Train
	 * @param tN
	 * 		  the specified Train number
	 * @param dest
	 * 		  the specified Train's destination
	 * @param aT
	 * 		  the specified Train's arrival time
	 * @param tT
	 * 		  the specified Train's transfer time
	 * @param aTS
	 * 		  the specified Train's arrival time in String
	 * @param tNS
	 * 		  the specified Train's number in String
	 */
	public Train(Train n, Train p, int tN, String dest, int aT, int tT)
	{
		next = n;
		prev = p;
		trainNumber = tN;
		destination = dest;
		arrivalTime = aT;
		transferTime = tT;
	}
	
	/**
	 * A method that sets the next train in the list
	 * @param nextTrain
	 * 		  the specified next Train 
	 */
	public void setNext(Train nextTrain)
	{
		next = nextTrain;
	}
	
	/**
	 * A method that sets the previous train in the list
	 * @param prevTrain
	 * 		  the specified previous Train
	 */		  
	public void setPrev(Train prevTrain)
	{
		prev = prevTrain;
	}
	
	/**
	 * A method that returns the Next train in the list
	 * @return
	 * 		  the Next Train
	 */
	public Train getNext()
	{
		return next;
	}
	
	
	/**
	 * A method that returns the Previous train in the list
	 * @return
	 * 		  the Previous Train
	 */
	public Train getPrev()
	{
		return prev;
	}
	
	/**
	 * A method that returns a train's number
	 * @return
	 * 		  the Train's number
	 * 
	 */
	public int getTrainNumber()
	{
		return trainNumber;
	}
	
	/**
	 * A method that returns a train's destination
	 * @return
	 * 		  the train's destination
	 */	
	public String getDestination()
	{
		return destination;
	}
	
	/**
	 * A method that returns a train's arrival time
	 * @return
	 * 		  the train's arrival time
	 */	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/**
	 * A method that returns a train's transfer time
	 * @return
	 * 		  the train's transfer time
	 */
	public int getTransferTime()
	{
		return transferTime;
	}
	
	/**
	 * A method that sets all the train's date to the specified values
	 * @param trainNum
	 * 		  the specified train number
	 * @param dest
	 * 		  the specified train's destination
	 * @param arrTime
	 * 		  the specified train's arrival time
	 * @param transTime
	 * 		  the specified train's transfer time
	 */
	public void setData(int trainNum, String dest, int arrTime, int transTime)
	{
		trainNumber = trainNum;
		destination = dest;
		arrivalTime = arrTime;
		transferTime = transTime;
	}

	
	/**
	 * A method that checks if this train's object is equal to
	 * the specified train's object
	 * @return 
	 * 		  whether or not if this train's object is equal to the specified train's object 
	 */
	public boolean equals(Object o)
	{
		if(o instanceof Train)
		{
			Train other = (Train) o;
			return trainNumber == other.trainNumber;
		}
		return false;
	}
	
	/**
	 * A method that checks whether the time is valid or not
	 * @return
	 * 		  whether or not if the time is valid or not
	 */
	public boolean isValid()
	{
		int time = getArrivalTime();
		if(time % 100 >= 0 && time % 100 <= 59)
		{
			if(time / 100 >= 0 && time / 100 <= 23)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A method that returns the arrival Time in string
	 * @return
	 * 		  the arrival Time in String
	 */
	public String arrivalTime()
	{
		int time = getArrivalTime();
		int temp = time;
		int count = 0;
		String arrTime = "";
		while(temp != 0)
		{
			temp /= 10;
			count++;
		}
		if(count == 0 || count == 1)
			arrTime = "000" + time;
		else if(count == 2)
			arrTime = "00" + time;
		else if(count == 3)
			arrTime = "0" + time;
		else
			arrTime = String.valueOf(time);
		return arrTime;
	}
	
	/**
	 * A method that returns the departure time in string
	 * @return
	 *        the departure Time in String
	 */
	public String transferTime()
	{
		String deptTime = "";
		int time = getArrivalTime() + getTransferTime();
		int temp = time;
		int count = 0;
		while(temp != 0)
		{
			temp /= 10;
			count++;
		}
		if(count == 0 || count == 1)
			deptTime = "000" + time;
		else if(count == 2)
			deptTime = "00" + time;
		else if(count == 3)
			deptTime = "0" + time;
		else
			deptTime = String.valueOf(time);
		return deptTime;
	}

	
	/**
	 * A method that returns a textual representation of all the information for this Train object.
	 * @return
	 * 		  the textual representation of all the information for this Train object.
	 */
	public String toString()
	{
		String text = "";
		text += "Selected Train: \n";
		text += String.format("%18s","Train Number: ") + trainNumber + "\n";
		text += String.format("%23s", "Train Destination: ") + destination + "\n";
		text += String.format("%18s","Arrival Time: ") + arrivalTime() + "\n";
		text += String.format("%20s", "Departure Time: ") + transferTime() + "\n";
		return text;
	}
}
