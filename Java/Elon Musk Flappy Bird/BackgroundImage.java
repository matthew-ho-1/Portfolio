/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;
import java.net.URL;

/**
 * A class that controls the movement of the backgrounds and displays the background to be represented based off of the score
 */
public class BackgroundImage
{
	private BufferedImage backImageOne;
	private BufferedImage backImageTwo;
	private BufferedImage backImageTempOne;
	private BufferedImage backImageTempTwo;
	private boolean imageChangerOne;
	private boolean imageChangerTwo;
	private Queue<BufferedImage> backgrounds;
	private int x;
	private int x2;
	
	/**
	 * Creates a default BackgroundImage object which stores the initial background and the following images to be displayed
	 */
	public BackgroundImage()
	{
		x = 0;
		x2 = 1280;
		imageChangerOne = false;
		imageChangerTwo = false;
		try
		{
			URL url = getClass().getResource("/images/city background.jpg");
			backImageOne =  ImageIO.read(url);
			URL url2 = getClass().getResource("/images/city background.jpg");
			backImageTwo = ImageIO.read(url2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		backgrounds = new LinkedList<BufferedImage>();
		addBackgrounds();
	}
	
	/**
	 * Helper method which simply adds backgrounds into the queue in the order they should be dispalyed at
	 */
	public void addBackgrounds()
	{
		try
		{
			URL url = getClass().getResource("/images/Elon SpaceX.jpg");
			BufferedImage temp1 = ImageIO.read(url);
			backgrounds.add(temp1);
			
			URL url2 = getClass().getResource("/images/SpaceXRoadster.jpg");
			BufferedImage temp2 = ImageIO.read(url2);
			backgrounds.add(temp2);
			
			URL url3 = getClass().getResource("/images/elonMuskRocket.jpg");
			BufferedImage temp3 = ImageIO.read(url3);
			backgrounds.add(temp3);
			
			URL url4 = getClass().getResource("/images/elonMuskOnMars.jpg");
			BufferedImage temp4 = ImageIO.read(url4);
			backgrounds.add(temp4);
			
			URL url5 = getClass().getResource("/images/city background.jpg");
			BufferedImage temp5 = ImageIO.read(url5);
			backgrounds.add(temp5);
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	/**
	 * Sets the x value of the background to a given value
	 * @param xPos the x value which will represent the new x coordinate of the top left part of the background image
	 */
	public void setX(int xPos)
	{
		x = xPos;
	}
	
	/**
	 * Sets the second x value which represents the second image or the following background image tracing thwe first
	 * @param xPos2 the x value which will represent the new x coordinate of the top left part of the second background image
	 */
	public void setX2(int xPos2)
	{
		x2 = xPos2;
	}
	
	/**
	 * Gives the x value of the first background image
	 * @return the x value of the first background image
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gives the x value of the second background image
	 * @return the x value of the second background image
	 */
	public int getX2()
	{
		return x2;
	}
	
	/**
	 * Abstract method that is overridden which moves the background based off both of the current x values and changes the image when needed
	 */
	public void move()
	{
		x--;
		x2--;
		if(x < -1279)
		{
			if(imageChangerOne)
			{
				backImageOne = backImageTempOne;
				imageChangerOne = false;
			}
			x = 1280;
		}
		if(x2 < -1279)
		{
			if(imageChangerTwo)
			{
				backImageTwo = backImageTempTwo;
				imageChangerTwo = false;
			}
			x2 = 1280;
		}
	}
	
	/**
	 * Updates the second background image and places it back into the queue so it will loop eventually
	 */
	public void changeBackgroundImage()
	{
		BufferedImage img = backgrounds.remove();
		backImageTempOne = img;
		backImageTempTwo = img;
		imageChangerOne = true;
		imageChangerTwo = true;
		backgrounds.add(img);
	}
	
	/**
	 * Draws the backgrounds within the panel based off their current x and y values
	 * @param g2 the graphics2D object to be drawn with
	 */
	public void draw(Graphics2D g2)
	{
		g2.drawImage(backImageOne, x, 0, backImageOne.getWidth(), backImageOne.getHeight(), null);
		g2.drawImage(backImageTwo, x2, 0, backImageTwo.getWidth(), backImageTwo.getHeight(), null);
	}
}