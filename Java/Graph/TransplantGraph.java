//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A method that contains an adjacency matrix for the organ donors and recipients
 */
public class TransplantGraph implements Serializable
{
	private ArrayList<Patient> donors;
	private ArrayList<Patient> recipients;
	public static final int MAX_PATIENTS = 100;
	private boolean [][] connections;
	
	/**
	 * A constructor for the TransplantGraph
	 */
	public TransplantGraph()
	{
		donors = new ArrayList<Patient>();
		recipients = new ArrayList<Patient>(); 
		connections = new boolean[MAX_PATIENTS][MAX_PATIENTS];
	}
	
	/**
	 * A method that gets the ArrayList of donors
	 * @return
	 * 		  the ArrayListlist of donors
	 */
	public ArrayList<Patient> getDonors()
	{
		return donors;
	}
	
	/**
	 * A method that gets the lists of recipients
	 * @return
	 * 		 the list of recipients
	 */
	public ArrayList<Patient> getRecipients()
	{
		return recipients;
	}
	
	/**
	 * A method that gets the adjacency matrix 
	 * for the connections
	 * @return
	 * 		 the adjacency matrix for the connections
	 */
	public boolean[][] getConnections()
	{
		return connections;
	}
	
	/**
	 * A method that creates and returns a new TransplantGraph object from
	 * the donorFile and recipientFile
	 * @param donorFile
	 * 		  the specified donorFile
	 * @param recipientFile
	 * 		  the specified recipientFile
	 * @return
	 * 		 a new TransplantGraph object
	 * @throws FileNotFoundException 
	 */
	public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) throws FileNotFoundException
	{
		TransplantGraph graph = new TransplantGraph();
		FileInputStream donor = new FileInputStream(donorFile);
		FileInputStream recipient = new FileInputStream(recipientFile);
		InputStreamReader inDonor = new InputStreamReader(donor);
		InputStreamReader inRecipient = new InputStreamReader(recipient);
		Scanner inputDon = new Scanner(inDonor);
		Scanner inputRec = new Scanner(inRecipient);
		while(inputDon.hasNextLine())
		{
			String don = inputDon.nextLine();
			String[] donArray = don.split(", ");
			int ID = 0;
			String name = "";
			String organ = "";
			int age = 0;
			BloodType bloodType = null;
			for(int i = 0; i < donArray.length; i++)
			{
				if(i == 0)
					ID = Integer.parseInt(donArray[i]);
				else if(i == 1)
					name = donArray[i];
				else if(i == 2)
					age = Integer.parseInt(donArray[i]);
				else if(i == 3)
					organ = donArray[i];
				else if(i == 4)
					bloodType = new BloodType(donArray[i]);
			}
			String organFormat = organ.substring(0,1).toUpperCase() + organ.substring(1);
			Patient patient = new Patient(name, organFormat, age, ID, bloodType, true);
			graph.getDonors().add(patient);
		}
		while(inputRec.hasNextLine())
		{
			String rec = inputRec.nextLine();
			String[] recArray = rec.split(", ");
			int ID = 0;
			String name = "";
			String organ = "";
			int age = 0;
			BloodType bloodType = null;
			for(int i = 0; i < recArray.length; i++)
			{
				if(i == 0)
					ID = Integer.parseInt(recArray[i]);
				else if(i == 1)
					name = recArray[i];
				else if(i == 2)
					age = Integer.parseInt(recArray[i]);
				else if(i == 3)
					organ = recArray[i];
				else if(i == 4)
					bloodType = new BloodType(recArray[i]);
			}
			Patient patient = new Patient(name, organ, age, ID, bloodType, false);
			graph.getRecipients().add(patient);
		}
		for(int r = 0; r < graph.getDonors().size(); r++)
		{
			for(int c = 0; c < graph.getRecipients().size(); c++)
			{
				Patient pDon = graph.getDonors().get(r);
				Patient pRecip = graph.getRecipients().get(c);
				if(pDon.getOrgan().toUpperCase().equals(pRecip.getOrgan().toUpperCase()))
				{
					boolean compatible = BloodType.isCompatible(pRecip.getBloodType(), pDon.getBloodType());
					graph.getConnections()[r][c] = compatible;
					if(compatible)
					{
						pDon.addID(c);
						pRecip.addID(r);
					}
				}
				else
					graph.getConnections()[r][c] = false;
			}
		}
		return graph;
	}
	
	/**
	 * A method that adds the specified Patient to the recipients list
	 * @param patient
	 * 		  the specified Patient
	 */
	public void addRecipient(Patient patient)
	{
		recipients.add(patient);
		for(int r = 0;r < donors.size(); r++)
		{
			boolean compatible = false;
			Patient donor = donors.get(r);
			if(patient.getOrgan().equals(donor.getOrgan()))
			{
				compatible = BloodType.isCompatible(patient.getBloodType(), donor.getBloodType());
				connections[r][recipients.size() - 1] = compatible;
				if(compatible)
				{
					patient.addID(r);
					donor.addID(recipients.size() - 1);
				}
			}
			else
				connections[r][recipients.size() - 1] = compatible;
		}
	}
	
	/**
	 * A method that adds the specified Patient to the donors list
	 * @param patient
	 * 		  the specified patient
	 */
	public void addDonor(Patient patient)
	{
		donors.add(patient);
		for(int c = 0;c < recipients.size(); c++)
		{
			boolean compatible = false;
			Patient recipient = recipients.get(c);
			if(patient.getOrgan().equals(recipient.getOrgan()))
			{
				compatible = BloodType.isCompatible(recipient.getBloodType(), patient.getBloodType());
				connections[donors.size() - 1][c] = compatible;
				if(compatible)
				{
					patient.addID(c);
					recipient.addID(donors.size() - 1);
				}
			}
			else
				connections[donors.size() - 1][c] = compatible;
		}
	}
	
	/**
	 * A method that removes the specified Patient from the recipients list
	 * @param name
	 * 		  the specified name to look for
	 * @throws NoSuchPatientExistsException
	 * 		  when there is no patient with the specified name
	 */
	public void removeRecipient(String name) throws NoSuchPatientExistsException
	{
		int colRemoved = -1;
		for(int i = 0; i < recipients.size(); i++)
		{
			if(recipients.get(i).getName().equals(name))
			{
				recipients.remove(i);
				colRemoved = i;
			}
		}
		if(colRemoved != -1)
		{
			if(colRemoved != recipients.size())
			{
				for(int p = 0; p < recipients.size(); p++)
				{
					recipients.get(p).setID(p);
				}
				for(int c = colRemoved; c < recipients.size() - 1; c++)
				{
					for(int r = 0; r < donors.size(); r++)
					{
						connections[r][c] = connections[r][c + 1];
					}
				}
				for(int r = 0; r < donors.size(); r++)
				{
					for(int c = 0; c < recipients.size(); c++)
					{
						donors.get(r).clearID();
						recipients.get(c).clearID();
					}
				}
				for(int row = 0; row < donors.size(); row++)
				{
					for(int col = 0; col < recipients.size(); col++)
					{
						Patient donor = donors.get(row);
						Patient recipient = recipients.get(col);
						boolean compatible = connections[row][col];
						if(compatible)
						{
							donor.addID(col);
							recipient.addID(row);
						}
					}
				}
			}
		}
		else
			throw new NoSuchPatientExistsException();
	}
	
	/** 
	 * A method that removes the specified Patient from the donors list
	 * @param name
	 * 		  the specified name to look for
	 * @throws NoSuchPatientExistsException
	 * 		   when there is no patient with the specified name
	 */
	public void removeDonor(String name) throws NoSuchPatientExistsException
	{
		int rowRemoved = -1;
		for(int i = 0; i < donors.size(); i++)
		{
			if(donors.get(i).getName().equals(name))
			{
				donors.remove(i);
				rowRemoved = i;
			}
		}
		if(rowRemoved != -1)
		{
			if(rowRemoved != donors.size())
			{
				for(int p = 0; p < donors.size(); p++)
				{
					donors.get(p).setID(p);
				}
				for(int r = rowRemoved; r < donors.size(); r++)
				{
					for(int c = 0; c < recipients.size(); c++)
					{
						connections[r][c] = connections[r + 1][c];
					}
				}
				for(int r = 0; r < donors.size(); r++)
				{
					for(int c = 0; c < recipients.size(); c++)
					{
						donors.get(r).clearID();
						recipients.get(c).clearID();
					}
				}
				for(int row = 0; row < donors.size(); row++)
				{
					for(int col = 0; col < recipients.size(); col++)
					{
						Patient donor = donors.get(row);
						Patient recipient = recipients.get(col);
						boolean compatible = connections[row][col];
						if(compatible)
						{
								donor.addID(col);
								recipient.addID(row);
						}
					}
				}
			}
		}
		else
			throw new NoSuchPatientExistsException();
	}
	
	/**
	 * A method that prints all organ recipients' information in a neatly formatted
	 * table
	 */
	public void printAllRecipients()
	{
		System.out.println("Index | Recipient Name     | Age | Organ needed | Blood Type | Donors IDs\r\n" + 
				"=========================================================================");
		for(int i = 0; i < recipients.size(); i++)
		{
			System.out.println(recipients.get(i).toString());
		}
	}
	
	/**
	 * A method that prints all organ donors' information in a neatly formatted table
	 */
	public void printAllDonors()
	{
		System.out.println("Index | Recipient Name     | Age | Organ needed | Blood Type | Recipients IDs\r\n" + 
				"=========================================================================");
		for(int i = 0; i < donors.size(); i++)
		{
			System.out.println(donors.get(i).toString());
		}	
	}
}
