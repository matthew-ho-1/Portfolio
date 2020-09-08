//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 1
//CSE214
//Recitation 8-	TA Robert Ignatowicz  
import java.util.Scanner;

/**
 * A class to test the Track class as well as modify certain tracks.
 * Provides a user interface for the Track class.
 */
public class Station 
{
	private Track head;
	private Track tail;
	private Track cursor;
	
	/**
	 * A constructor that constructs the Station object with default values
	 */
	public Station()
	{
		head = null;
		tail = null;
		cursor = null;
	}
	
	/**
	 * A constructor that constructs the Station object with specified values
	 * @param h
	 * 		  the specified Track head node
	 * @param t
	 * 		  the specified Track tail node
	 * @param c
	 * 		  the specified Track cursor
	 */
	public Station(Track h, Track t, Track c)
	{
		head = h;
		tail = t;
		cursor = c;
	}
	
	/**
	 * A method that adds newTrack to this Station
	 * @param newTrack
	 * 			     the Track to add to the station
	 */
	public void addTrack(Track newTrack)
	{
		 if(cursor == null)
		  {
			  head = newTrack;
			  tail = newTrack;
			  cursor = newTrack;
		  }
		  else if(cursor.getNext() == null)
		  {
			  cursor.setNext(newTrack);
			  newTrack.setPrev(cursor);
			  cursor = newTrack;
			  tail = cursor;
		  }
		  else
		  {
			  newTrack.setNext((cursor.getNext()));
			  cursor.setNext(newTrack);
			  newTrack.getNext().setPrev(newTrack);
			  newTrack.setPrev(cursor);
			  cursor = newTrack;
		  }
		 sort();
	}
	
	/**
	 * A method that gets the cursor of the Station
	 * @return
	 * 		 the track reference of the cursor of the Station
	 */
	public Track getCursor()
	{
		return cursor;
	}
	
	/**
	 * A method that sets the cursor of the Station
	 * @param t
	 * 		  the specified track reference to set the cursor of the station
	 */
	public void setCursor(Track t)
	{
		cursor = t;
	}
	
	/**
	 * A method that gets the head of the station
	 * @return
	 * 		 the track reference to the head of the Station
	 */
	public Track getHead()
	{
		return head;
	}
	
	/**
	 * A method that removes the selected Track object from this Station and returns it
	 * @return
	 * 		  the removed track object from the station
	 */
	public Track removeSelectedTrack()
	{
		if(cursor == null)
		{
			System.out.println("train not removed");
	    }
		Track temp = cursor;
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
	 * A method that keeps track of the number of tracks in the Station
	 * @return
	 * 	      the number of tracks in the station
	 */
	public int numberOfTracks()
	{
		int totalNum = 0;
		Track temp = head;
		while(temp != null)
		{
			totalNum++;
			temp = temp.getNext();
		}
		return totalNum;
	}
	
	/**
	 * A method that prints the selected Track list
	 */
	public void printSelectedTrack()
	{
		System.out.println(cursor.toString());
	}
	
	/**
	 * A method that prints all Tracks list in this Station
	 */
	public void printAllTracks()
	{
	   System.out.println(toString());
	}
	
	/**
	 * A method that Moves the reference of the selected Track node to the node which 
	 * has the same trackNumber as the given trackToSelect parameter if it exists
	 * @param trackToSelect
	 * 		  the selected Track node
	 * @return
	 * 		  whether or not the reference of the selected Track node is moved to the specified Track 
	 */
	public boolean selectTrack(int trackToSelect)
	{
      Track temp = head;
	  while(temp != null)
	  {
		  if(temp.getTrackNumber() == trackToSelect)
		  {
			  setCursor(temp);
			  return true;
		  }
		  temp = temp.getNext();
	  }
	  return false;	
	}
	
	/**
	 * A method that checks to see if there are duplicate track numbers in the Station
	 * @param t
	 * 		  the specified Track to compare to
	 * @return
	 *        whether or not a duplicate track number can be found in the Station
	 */
	public boolean duplicateTracks(Track t) throws TrainAlreadyExistsException
	{
		boolean duplicate = false;
		Track temp = head;
		while(temp != null)
		{
			if(temp.getTrackNumber() == t.getTrackNumber())
			{
				duplicate = true;
				throw new TrainAlreadyExistsException();
			}
			temp = temp.getNext();
		}
		return duplicate;
	}
	
	/**
	 * A method that sorts the tracks by order of number
	 */
	private void sort()
	{
		boolean sorted = false;
		Track temp1 = head;
		while(temp1.getNext() != null)
		{
			sorted = true;
			if(temp1.getTrackNumber() > temp1.getNext().getTrackNumber())
			{
				sorted = false;
				Track temp2 = temp1.getNext();
				int trackNumberA = temp1.getTrackNumber();
				int trackNumberB = temp2.getTrackNumber();
				temp1.setTrackNumber(trackNumberB);
				temp2.setTrackNumber(trackNumberA);
			}
			temp1 = temp1.getNext();
		}
	}
	
	/**
	 * A method that returns a neatly formatted representation of the Station object
	 * @return
	 * 		 the String form a neatly formatted representation of the Station object
	 */
	public String toString()
	{
		String table = "";
		Track temp = head;
		while(temp != null)
		{
			table += temp.toString() + "\n";
			temp = temp.getNext();
		}
		return table;
	}
	
    /**
     * A method that prints the menu for the user
     */
	public static void printMenu()
	{
	   System.out.println("|-----------------------------------------------------------------------------|\r\n" + 
	   		"| Train Options                       | Track Options                         |\r\n" + 
	   		"|    A. Add new Train                 |    TA. Add Track                      |\r\n" + 
	   		"|    N. Select next Train             |    TR. Remove selected Track          |\r\n" + 
	   		"|    V. Select previous Train         |    TS. Switch Track                   |\r\n" + 
	   		"|    R. Remove selected Train         |   TPS. Print selected Track           |\r\n" + 
	   		"|    P. Print selected Train          |   TPA. Print all Tracks               |\r\n" + 
	   		"|-----------------------------------------------------------------------------|\r\n" + 
	   		"| Station Options                                                             |\r\n" + 
	   		"|   SI. Print Station Information                                             |\r\n" + 
	   		"|    Q. Quit                                                                  |\r\n" + 
	   		"|-----------------------------------------------------------------------------|");
	   System.out.println();
	   System.out.print("Choose an operation: ");
	}
	
	/**
	 * The main method of the Station class to test the Track class
	 * @param args
	 * 		      not used 
	 */
	public static void main(String[] args) 
	{
      Scanner in = new Scanner(System.in);
      int counter = 0;
      Station station = new Station();
      String choice;
      do 
      {
    	  printMenu();
    	  choice = in.next().toUpperCase();
    	  try
    	  {
    		  if(!choice.equals("Q"))
    		  {
		    	  switch(choice)
		    	  {
		    	  	case "A": System.out.println();
		    	  			  System.out.print("Enter train number: ");
		    	  			  int trainNumber = in.nextInt();
		    	  			  in.nextLine();
		    	  			  System.out.print("Enter train destination: ");
		    	  			  String destination = in.nextLine();
		    	  			  System.out.print("Enter train arrival time: ");
		    	  			  String arrivalTimeString = in.nextLine();
		    	  			  int arrivalTime = Integer.parseInt(arrivalTimeString);
		    	  			  System.out.print("Enter train transfer time: ");
		    	  			  int transferTime = in.nextInt();
		    	  			  Train train = new Train(null,null,trainNumber,destination,arrivalTime,transferTime);
		    	  			  if(!train.isValid())
			    	  		  {
			    	  		    System.out.println();
			    	  		    System.out.println("Train not added: Invalid arrival time");
			    	  			System.out.println();
			    	  		  }
			    	  		  else if(station.getCursor() == null)
			    	  		  {
			    	  			System.out.println();
			    	  			System.out.println("Train not added: There is no Track to add the Train to!");
			    	  			System.out.println();
			    	  		  }
			    	  		  else if(station.getCursor().compareTime(train))
			    	  		  {
			    	  			System.out.println();
			    	  			System.out.println("Train not added: There is a Train already scheduled on Track " + station.getCursor().getTrackNumber() + " at that time!");
			    	  			System.out.println();
			    	  		  }
			    	  		  else if(station.getCursor().compareTrainNum(train))
			    	  		  {
			    	  			System.out.println();
			    	  			System.out.println("Train not added: There is already a Train with that number!");
			    	  			System.out.println();
			    	  		  }
			    	  		  else
			    	  		  {
				    	  		station.getCursor().addTrain(train);
			    	  			System.out.println();
			    	  		    System.out.println("Train No. " + trainNumber + " to " + destination + " added to Track " + station.getCursor().getTrackNumber());
			    	  			System.out.println();
			    	  		  }
		    	  			  break;
		    	  	case "N": System.out.println();
		    	  	          station.getCursor().selectNextTrain();
		    	  		 	  System.out.println("Cursor has been moved to next train.");
		    	  		 	  System.out.println();
		    	  	 	      break;
		    	  	case "V": System.out.println();
		    	  			  station.getCursor().selectPrevTrain();
		    	  			  System.out.println("Cursor has been moved to previous train.");
		    	  			  System.out.println();
		    	  			  break;
		    	  	case "R": System.out.println();
		    	  			  Train removed = station.getCursor().removeSelectedTrain();
		    	  			  System.out.println("Train No. " + removed.getTrainNumber() + " to " + removed.getDestination() + " has been removed from Track " + station.getCursor().getTrackNumber() + ".");
		    	  			  System.out.println();
		    	  			  break;
		    	  	case "P": System.out.println();
		    	  			  System.out.println(station.getCursor().getCursor().toString());
		    	  			  System.out.println();
		    	  			  break;
		    	  	case "TA":System.out.println();
		    	  			  System.out.print("Enter track number: ");
		    	  	          int trackNumber = in.nextInt();
		    	  	          Track track = new Track(null,null,null,null,null,0.0,trackNumber);
		    	  	          if(!station.duplicateTracks(track))
		    	  	          {
		    	  	            station.addTrack(track);
		    	  	          	System.out.println();
		    	  	          	System.out.println("Track " + trackNumber + " added to the Station.");
		  	  	          		System.out.println();
		    	  	          }
		    	  	          break;
		    	  	case "TR": System.out.println();
		    	  		       Track remove = station.removeSelectedTrack();
		    	  			   System.out.println("Closed Track " + remove.getTrackNumber() + ".");
		    	  			   System.out.println();
		    	  			   break;
		    	  	case "TS": System.out.println();
		    	  			   System.out.print("Enter the Track Number: ");
		    	  			   int trackNum = in.nextInt();
		    	  			   if(station.selectTrack(trackNum))
		    	  			   {
		    	  				   System.out.println();
		    	  				   System.out.println("Switched to Track " + trackNum);
		    	  				   System.out.println();
		    	  			   }
		    	  			   else
		    	  			   {
		    	  				   System.out.println();
		    	  				   System.out.println("Could not switch to Track " + trackNum + ": Track " + trackNum + " does not exist.");
		    	  				   System.out.println();
		    	  			   }
		    	  			   break;
		    	  	case "TPS":System.out.println();
		    	  			   station.printSelectedTrack();
		    	  			   System.out.println();
		    	  			   break;
		    	  	case "TPA":System.out.println();
		    	  	           station.printAllTracks();
		    	  	           System.out.println();
		    	  			   break;
		    	  	case "SI":System.out.println();
		    	  	          System.out.println("Station (" + station.numberOfTracks() + " track(s))");
		    	  	          Track temp = station.getHead();
		    	  	          while(temp != null)
		    	  	          {
		    	  	        	  String format = "Track " + temp.getTrackNumber() + ": " + temp.numberOfTrains() + " trains arriving (" + String.format("%.2f", temp.getUtilizationRate()) + "% Utilization Rate):";
		    	  	        	  System.out.printf("%56s",format);
		    	  	        	  System.out.println();
		    	  	        	  temp = temp.getNext();
		    	  	          }
		    	  	          System.out.println();
		    	  	          break;
		    	  	  default: System.out.println();
		    	  	           System.out.println("Invalid Choice");
		    	  	           System.out.println();
		    	  	           break;
		    	  }
    		  }
    	  }
    	  catch(NoTrainExistsException e)
    	  {
	  			System.out.println("Selected train not updated: Already at the start or end of the track list");
	  			System.out.println();
    	  }
    	  catch(TrainAlreadyExistsException e)
    	  {
    		 	System.out.println();
  	          	System.out.println("Track not added: Track " + station.getCursor().getTrackNumber() + " already exists.");
	            System.out.println();
    		  
    	  }
      }
      while(!choice.equals("Q"));
      System.out.println();
      System.out.println("Program terminating normally...");

	}
	
}
