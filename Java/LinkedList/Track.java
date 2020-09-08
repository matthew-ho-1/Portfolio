//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 1
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

/**
 * A Track class to modify or access different Trains on different Tracks
 */
public class Track 
{
   private Train head;
   private Train tail;
   private Train cursor;
   private Track next;
   private Track prev;
   private double utilizationRate;
   private int trackNumber;
   
   /**
    * A constructor that constructs the Track to default value
    */
   public Track()
   {
	   head = null;
	   tail = null;
	   cursor = null;
	   next = null;
	   prev = null;
	   utilizationRate = 0.0;
	   trackNumber = 0;
   }
   
   /**
    * A constructor that constructs the Track to specified values
    * @param h
    * 		 the specified head Train node
    * @param t
    * 		 the specified tail Train node
    * @param c
    * 		 the specified Train cursor
    * @param n
    * 		 the specified next Track
    * @param p
    * 		 the specified previous Track
    * @param uR
    * 		 the specified utilization rate
    * @param tN
    * 		 the specified track number
    */
   public Track(Train h, Train t, Train c, Track n, Track p, double uR, int tN)
   {
	   head = h;
	   tail = t;
	   cursor = c;
	   next = n;
	   prev = p;
	   utilizationRate = uR;
	   trackNumber = tN;
   }
   
   /**
    * A method that returns the head of the Track
    * @return
    *       the train reference that is at the head of the Track
    */
   public Train getHead()
   {
	   return head;
   }
   
   /**
    * A method that returns the cursor 
    * @return
    *        the train reference that is the cursor
    */
   public Train getCursor()
   {
	   return cursor;
   }
   
   /**
    * A method that sets the cursor
    * @param c
    *        the specified train reference to set the cursor to
    */
   public void setCursor(Train c)
   {
	   cursor = c;
   }
   
   /**
    * A method that returns the next of the cursor
    * @return
    * 	    the specified train reference of the next of cursor
    */
   public Track getNext()
   {
	   return next;
   }
   
   /**
    * A method that returns the previous of the cursor
    * @return
    *        the specified train reference of the previous of cursor
    */
   public Track getPrev()
   {
	   return prev;
   }
   
   /**
    * A method that sets the next of the cursor
    * @param newNext
    * 		 the specified train reference of the next of cursor
    */
   public void setNext(Track newNext)
   {
	   next = newNext;
   }
   
   /**
    * A method that sets the previous of the cursor
    * @param newPrev
    *        the specified train reference of the previous of cursor
    */
   public void setPrev(Track newPrev)
   {
	   prev = newPrev;
   }
   
   /**
    * A method that returns the utilization rate of the track
    * @return
    *       the utilization rate
    */
   public double getUtilizationRate()
   {
	  Train temp = head;
	  double totalTransferTime = 0.0;
	  while(temp != null)
	  {
		  totalTransferTime += temp.getTransferTime();
		  temp = temp.getNext();
	  }
	  return (totalTransferTime / 1440.0) * 100;
   }
   
   /**
    * A method that returns the track number of the track
    * @return
    * 		the track number
    */
   public int getTrackNumber()
   {
	   return trackNumber;
   }
   
   /**
    * A method that sets the track number of the track
    * @param trackNum
    * 		 the specified track number to set the track to
    */
   public void setTrackNumber(int trackNum)
   {
	   trackNumber = trackNum;
   }
   
   /**
    * A method that adds a new Train to the specified Track list
    * @param newTrain
    * 			    the new Train to add to the specified Track list
    */
   public void addTrain(Train newTrain)
   {
	  if(cursor == null)
	  {
		  head = newTrain;
		  tail = newTrain;
		  cursor = newTrain;
	  }
	  else if(cursor.getNext() == null)
	  {
		  cursor.setNext(newTrain);
		  newTrain.setPrev(cursor);
		  cursor = newTrain;
		  tail = cursor;
	  }
	  else
	  {
		  newTrain.setNext((cursor.getNext()));
		  cursor.setNext(newTrain);
		  newTrain.getNext().setPrev(newTrain);
		  newTrain.setPrev(cursor);
		  cursor = newTrain;
	  }
	  sort();
   }
   
   /**
    * A method that prints the data of the selected train.
    */
   public void printSelectedTrain()
   {
	   System.out.println(cursor.toString());
   }
   
   /**
    * A method that removes the selected train from the track and return
    * a Train reference to it
    * @return
    * 		 the removed Train reference
    */
   public Train removeSelectedTrain()
   {
	   if(cursor == null)
	   {
		   System.out.println("train not removed");
	   }
	   Train temp = cursor;
	   if(cursor.getNext() == null && cursor.getPrev() == null)
	   {
		   cursor = null;
	   }
	   else if(cursor.getPrev() == null)
	   {
		   cursor.getNext().setPrev(null);
		   cursor = cursor.getNext();
	   }
	   else if(cursor.getNext() == null)
	   {
		   cursor.getPrev().setNext(null);
		   cursor= cursor.getPrev();
	   }
	   else
	   {
		   cursor.getPrev().setNext(cursor.getNext());
		   cursor.getNext().setPrev(cursor.getPrev());
		   cursor = cursor.getNext();
	   }
	   return temp;
   }
   
   /**
    * A method that moves the reference of the selected Train node forwards to the next Train if it exists
    * @return
    * 		 whether or not there is a next train
    */
   public boolean selectNextTrain() throws NoTrainExistsException
   {
	   boolean exist = false;
	   if(cursor.getNext() == null)
	   {
		   throw new NoTrainExistsException();
	   }
	   else
	   {
		   cursor = cursor.getNext();
		   exist = true;
	   }
	   return exist;
   }
   
   /**
    * A method that moves the reference of the selected Train nodes backwards to the previous Train if it exists
    * @return
    * 		whether or not there is a previous train
    */
   public boolean selectPrevTrain() throws NoTrainExistsException
   {
	   boolean exist = false;
	   if(cursor.getPrev() == null)
	   {
		  throw new NoTrainExistsException();
	   }
	   else
	   {
		   cursor = cursor.getPrev();
		   exist = true;
	   }
	   return exist;
   }
   
   /**
    * A method that returns the number of trains in the track
    * @return
    *       the number of trains in the track
    */
	public int numberOfTrains()
	{
		int totalNum = 0;
		Train temp = head;
		while(temp != null)
		{
			totalNum++;
			temp = temp.getNext();
		}
		return totalNum;
	}
	
	/**
	 * A method that compares the trains number in the track to see if the specified
	 * train is equal to a train in the Track list
	 * @param t
	 * 		  the specified train to compare to
	 * @return
	 * 	      whether or not the specified train's number is found
	 */
	public boolean compareTrainNum(Train t)
	{
		boolean matched = false;
		Train temp = head;
		while(temp != null && !matched)
		{
			if(temp.equals(t))
			{
				matched = true;
			}
			temp = temp.getNext();
		}
		return matched;
	}
	
   /**
    * A method that returns a neatly formatted list of all the trains scheduled on this Track in String format
    * @return
    * 		the formatted list of all the trains scheduled on this Track through a String
    */
   public String toString()
   {
	   utilizationRate = getUtilizationRate();
	   String table = "Track " + getTrackNumber() + " (" + String.format("%.2f", utilizationRate) + "% Utilization Rate):" + "\n";
	   table += String.format("%-9s %-16s %-25s %-16s %-20s", "Selected", "Train Number", "Train Destination", "Arrival Time", "Departure Time") + "\n";
	   table += "-------------------------------------------------------------------------------------" + "\n";
	   Train temp = head;
	   while(temp != null)
	   {
		   if(cursor == temp)
		   {
		      table += String.format("%5s %12d %8s %-21s %11s %17s", "*",temp.getTrainNumber(), "",temp.getDestination(), temp.arrivalTime(),temp.transferTime()) + "\n";
		   }
		   else
		   {
			  table += String.format("%5s %12d %8s %-21s %11s %17s", "",temp.getTrainNumber(),"",temp.getDestination(), temp.arrivalTime(),temp.transferTime()) + "\n";
		   }
		   temp = temp.getNext();
	   }
	   return table;
   }
   
   /**
    * A method to compare the times of the various trains on the track
    * @param train
    * 		 the specified train to compare to
    * @return
    * 		 whether or not a train's time conflicts with a train on the track
    */
   public boolean compareTime(Train train)
   {
	  boolean matchTime = false;
	  Train temp = head;
	  if(head == null)
	  {	  
	  }
	  else
	  {
		  while(temp != null && !matchTime)
		  {
		    if(Integer.parseInt(train.arrivalTime()) >= Integer.parseInt(temp.arrivalTime()))
		    {
		    	if(Integer.parseInt(train.arrivalTime()) <= Integer.parseInt(temp.transferTime()))
		    	{
		    		matchTime = true;
		    	}
		    }
		    temp = temp.getNext();
		  }
	  }
	  return matchTime;
	}
   
   /**
    * A method that sorts the trains in order of time
    */
	private void sort()
	{
		boolean sorted = false;
		Train temp1 = head;
		{
			while(temp1.getNext() != null)
			{
				sorted = true;
				if(temp1.getArrivalTime() > temp1.getNext().getArrivalTime())
				{
					sorted = false;
					Train temp2 = temp1.getNext();
					int trainNumber1 = temp1.getTrainNumber();
					String trainDestination1 = temp1.getDestination();
					int arrivalTime1 = temp1.getArrivalTime();
					int transferTime1 = temp1.getTransferTime();
					int trainNumber2 = temp2.getTrainNumber();
					String trainDestination2 = temp2.getDestination();
					int arrivalTime2 = temp2.getArrivalTime();
					int transferTime2 = temp2.getTransferTime();
					temp1.setData(trainNumber2,trainDestination2,arrivalTime2,transferTime2);
					temp2.setData(trainNumber1,trainDestination1,arrivalTime1,transferTime1);
				}
				temp1 = temp1.getNext();
			}
		}
	}
}
