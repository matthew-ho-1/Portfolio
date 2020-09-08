/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.net.URL;

/**
 * The class which represents the bird/Elon Musk and controls his actions within the game such as his movement
 */
public class Bird 
{
	private double x, y;
	private double yVelocity = 0;
	private BufferedImage[] image;
	private int count;
	private int wait;
	
	/**
	 * Creates a default bird and placing it in a specific x and y location and adds all the image components to piece Elon together
	 */
	public Bird()
	{
		x = 100;
		y = 200;
		image = new BufferedImage[4];
		wait = 0;
		count = 0;
		try
		{
			URL url = getClass().getResource("/images/elon smoking the joint0.png");
			image[0] = ImageIO.read(url);
			URL url2 = getClass().getResource("/images/elon smoking the joint2.png");
			image[1] = ImageIO.read(url2);
			URL url3 = getClass().getResource("/images/elon smoking the joint4.png");
			image[2] = ImageIO.read(url3);
			URL url4 = getClass().getResource("/images/elon smoking the joint6.png");
			image[3] = ImageIO.read(url4);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * An overriden abstract method that controls his movement and the actions of his wings based off of the current status of the game
	 */
	public void tick()
	{
		yVelocity += 0.25;
		y += yVelocity;
		wait++;
		if(y < 0)
		{
			y = 0;
		}
		if(wait > 15)
		{
			count++;
			wait = 0;
			if(count > 3)
			{
				count = 0;
			}
		}
		
	}
	
	/**
	 * Resets the bird's yVelocity to a value of -6
	 */
	public void flyHigh()
	{
		yVelocity = -6;
	}
	
	/**
	 * Gives the x value of the bird
	 * @return the top left x coordinate of the bird
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Gives the y value of the bird
	 * @return the top left y coordinate of the bird
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Sets the y value of the bird to a new value
	 * @param yPos the y coordinate to which the bird will be moved to
	 */
	public void setY(double yPos)
	{
		y = yPos;
	}
	
	/**
	 * Draws the bird based off the current image x and y values within the panel
	 * @param g the graphics object to be drawn with
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image[count], (int)x, (int)y, 200, 100, null);
	}
	
}