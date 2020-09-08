import java.io.*;
import java.util.*; 

//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

/**
 * A class that will act as the main driver for the application
 */
public class TransplantDriver implements Serializable
{
	public static final String DONOR_FILE = "donors.txt";
	public static final String RECIPIENT_FILE = "recipients.txt";
	
	/**
	 * A method that will act as the main driver for the application
	 * @param args
	 * 		  not used
	 */
	public static void main(String[] args) 
	{
		File file = new File("transplant.obj");
		TransplantGraph graph = null;
		Scanner in = new Scanner(System.in);
		String choice = "";
		if(!file.exists())
		{
			try 
			{
			System.out.println("transplant.obj not found. Creating new TransplantGraph object...");
			System.out.println("Loading data from 'donors.txt'...");
			System.out.println("Loading data from 'recipients.txt' ...");
			graph = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Loading data from tansplant.obj");
			try
			{
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
				graph = (TransplantGraph) input.readObject();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		do
		{
			try
			{
				System.out.println();
				printMenu();
				System.out.println();
				System.out.print("Please select an option: ");
				choice = in.next().toUpperCase();
				switch(choice)
				{
					case "LR": System.out.println();
							   graph.printAllRecipients();
							   break;
					case "LO": System.out.println();
							   graph.printAllDonors();
							   break;
					case "AO": System.out.println();
							   System.out.print("Please enter the organ donor name: ");
							   in.nextLine();
							   String nameDon = in.nextLine();
							   System.out.print("Please enter the organs " + nameDon + " is donating: ");
							   String organDon = in.next();
							   System.out.print("Please enter the blood type of " + nameDon + ": ");
							   String bloodType = in.next();
							   BloodType bloodTypeDon = new BloodType(bloodType);
							   System.out.print("Please enter the age of " + nameDon + ": ");
							   int ageDon = in.nextInt();
							   Patient patientDon = new Patient(nameDon, organDon, ageDon, graph.getDonors().size(), bloodTypeDon, true);
							   graph.addDonor(patientDon);
							   System.out.println();
							   System.out.println("The organ donor with ID " + (graph.getDonors().size() - 1) + " was successfully added to the donor list! ");
							   break;
					case "AR": System.out.println();
					   		   System.out.print("Please enter the new recipient's name: ");
					   		   in.nextLine();
					   		   String nameRecip = in.nextLine();
					   		   System.out.print("Please enter the recipient's blood type: ");
					   		   String bloodTyped = in.next();
					   		   BloodType bloodTypeRecip = new BloodType(bloodTyped);
					   		   System.out.print("Please enter the recipient's age: ");
					   		   int ageRecip = in.nextInt();
					   		   System.out.print("Please enter the organ needed: ");
					   		   String organRecip = in.next();
							   Patient patientRecip = new Patient(nameRecip, organRecip, ageRecip, (graph.getRecipients().size()) , bloodTypeRecip, false);
							   graph.addRecipient(patientRecip);
					   		   System.out.println();
					   		   System.out.println(nameRecip + " is now on the organ transplant waitlist!");
							   break;
					case "RO": System.out.println();
							   System.out.print("Please enter the name of the organ donor to remove: ");
							   in.nextLine();
							   String removeDonor = in.nextLine();
							   graph.removeDonor(removeDonor);
							   System.out.println();
							   System.out.println(removeDonor + " was removed from the organ donor list.");
							   break;
					case "RR": System.out.println();
							   System.out.print("Please enter the name of the recipient to remove: ");
							   in.nextLine();
							   String removeRecip = in.nextLine();
							   graph.removeRecipient(removeRecip);
							   System.out.println();
							   System.out.println(removeRecip + " was removed from the organ transplant waitlist.");
							   break;
					case "SR": System.out.println();
							   String subchoiceR = "";
					 		   do
					 		   {
								   printSubMenuRecip();
								   System.out.println();
								   System.out.print("Please select an option: ");
								   subchoiceR = in.next().toUpperCase();
								   switch(subchoiceR)
								   {
								   		case "I": System.out.println();
								   				  Collections.sort(graph.getRecipients());
								   				  graph.printAllRecipients();
								   				  System.out.println();
								   				  break;
								   		case "N": System.out.println();
								   				  Collections.sort(graph.getRecipients(), new NumConnectionsComparator());
								   				  graph.printAllRecipients();
								   				  System.out.println();
								   				  break;
								   		case "B": System.out.println();
							   				  	  Collections.sort(graph.getRecipients(), new BloodTypeComparator());
							   				      graph.printAllRecipients();
							   				      System.out.println();
							   				      break;
								   		case "O": System.out.println();
						   				  		  Collections.sort(graph.getRecipients(), new OrganComparator());
						   				  		  graph.printAllRecipients();
						   				  		  System.out.println();
								   				  break;
							   		}
							   }
							   while(!subchoiceR.equals("Q"));
					 		   System.out.println();
							   System.out.println("Returning to main menu.");
					 		   Collections.sort(graph.getRecipients());
							   break;
					case "SO": System.out.println();
							   String subchoiceD = "";
							   do
					   		   {
								   printSubMenuDonor();
								   System.out.println();
						   		   System.out.print("Please select an option: ");
						   		   subchoiceD = in.next().toUpperCase();
					   			   switch(subchoiceD)
					   			   {
					   			   		case "I": System.out.println();
					   			   				  Collections.sort(graph.getDonors());
					   			   				  graph.printAllDonors();
					   			   				  System.out.println();
					   			   				  break;
					   			   		case "N": System.out.println();
					   			   				  Collections.sort(graph.getDonors(), new NumConnectionsComparator());
					   			   				  graph.printAllDonors();
					   			   				  System.out.println();
					   			   				  break;
					   			   		case "B": System.out.println();
					   			   				  Collections.sort(graph.getDonors(), new BloodTypeComparator());
					   			   				  graph.printAllDonors();
					   			   				  System.out.println();
					   			   				  break;
					   			   		case "O": System.out.println();
					   			   				  Collections.sort(graph.getDonors(), new OrganComparator());
					   			   				  graph.printAllDonors();
					   			   				  System.out.println();
					   			   				  break;
					   			   }
					   		   }
					   		   while(!subchoiceD.equals("Q"));
							   System.out.println();
							   System.out.println("Returning to main menu.");
							   Collections.sort(graph.getDonors());
							   break;		
				}
			}
			catch(NoSuchPatientExistsException e)
			{
				System.out.println();
				System.out.println("No such patient exists.");
			}
		}
		while(!choice.equals("Q"));
		System.out.println();
		System.out.println("Writing data to transplant.obj...");
		if(!file.exists())
		{
		   try 
		   {
			   file.createNewFile();
		   } 
		   catch (IOException e) 
		   {
			   e.printStackTrace();
		   }
		}
		try 
		{
		   ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		   out.writeObject(graph);
		   out.close();
		}
		catch (IOException e) 
		{
		   e.printStackTrace();
		}
		System.out.println();
		System.out.println("Program terminating normally...");
		in.close();
	}
	
	/**
	 * A method that will print out the menu
	 */
	public static void printMenu()
	{
		System.out.println("Menu:\r\n" + 
				"    (LR) - List all recipients\r\n" + 
				"    (LO) - List all donors\r\n" + 
				"    (AO) - Add new donor\r\n" + 
				"    (AR) - Add new recipient\r\n" + 
				"    (RO) - Remove donor\r\n" + 
				"    (RR) - Remove recipient\r\n" + 
				"    (SR) - Sort recipients\r\n" + 
				"    (SO) - Sort donors\r\n" + 
				"    (Q) - Quit");
	}
	
	/**
	 * A method that will print out the submenu for
	 * the recipient sorting menu
	 */
	public static void printSubMenuRecip()
	{
		System.out.println("    (I) Sort by ID\r\n" + 
				"    (N) Sort by Number of Donors\r\n" + 
				"    (B) Sort by Blood Type\r\n" + 
				"    (O) Sort by Organ Needed\r\n" + 
				"    (Q) Back to Main Menu");
	}
	
	/**
	 * A method that will print out the submenu for
	 * the donor sorting menu
	 */
	public static void printSubMenuDonor()
	{
		System.out.println("    (I) Sort by ID\r\n" + 
				"    (N) Sort by Number of Recipients\r\n" + 
				"    (B) Sort by Blood Type\r\n" + 
				"    (O) Sort by Organ Donated\r\n" + 
				"    (Q) Back to Main Menu\r\n");
	}

}
