/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.net.*;

/**
 * A class that creates medals and is used to display them when the player crashes the bird or ends up losing
 */
public class Medals
{
	private Map<Integer,BufferedImage> medals;
	private BufferedImage doge;
	private BufferedImage testImage2;
	private BufferedImage testImage3;
	private BufferedImage testImage4;
	private BufferedImage testImage5;
	private BufferedImage testImage6;
	private BufferedImage testImage7;
	private int medalNum;
	
	/**
	 * Creates a Medals object which consists of a map of an integer representing the score and images to go about each score
	 */
	public Medals()
	{
		medals = new TreeMap<Integer,BufferedImage>();
		addMedals();
	}
	
	/**
	 * A helper method that adds all the images to the TreeMap 
	 */
	public void addMedals()
	{
		try
		{
			URL url = getClass().getResource("/images/doge.png");
			doge = ImageIO.read(url);
			URL url2 = getClass().getResource("/images/teslaLogo.png");
			testImage2 = ImageIO.read(url2);
			URL url3 = getClass().getResource("/images/ElonPoweredUp.png");
			testImage3 = ImageIO.read(url3);
			URL url4 = getClass().getResource("/images/ElonApproval.png");
			testImage4 = ImageIO.read(url4);
			URL url5 = getClass().getResource("/images/PharaohElon.png");
			testImage5 = ImageIO.read(url5);
			URL url6 = getClass().getResource("/images/hostMeme.jpg");
			testImage6 = ImageIO.read(url6);
			URL url7 = getClass().getResource("/images/deadDeer.jpg");
			testImage7 = ImageIO.read(url7);
		}
		catch(Exception e){}
		medals.put(10,doge);
		medals.put(20,testImage2);
		medals.put(30,testImage3);
		medals.put(40,testImage4);
		medals.put(50,testImage5);
		medals.put(60,testImage6);
		medals.put(70,testImage7);
	}
	
	/**
	 * Sets the current active medal to be displayed based off of the current score within the game
	 * @param num the score to be passed in representing the score
	 */
	public void setCurrentStatus(int num)
	{
		double temp = num;
		temp = Math.floor(temp / 10) * 10;
		medalNum = (int) temp;
	}
	
	/**
	 * Gives the current status or closest "10" value for the medal
	 * @return the corresponding medal number based off of the current score
	 */
	public int getCurrentStatus()
	{
		return medalNum;
	}
	
	/**
	 * Gives the current medal to be displayed at the end of the game
	 * @return the current medal to be displayed
	 */
	public BufferedImage getMedal()
	{
		if(medalNum > 70)
		{
			return medals.get(70);
		}
		else
		{
			return medals.get(medalNum);
		}
	}
}