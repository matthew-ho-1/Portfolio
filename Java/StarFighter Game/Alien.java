//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd 

//*** LIKE THE SHIP CLASS

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Represents a moving alien that can be drawn in a graphics
 * window.
 */
public class Alien extends MovingThing
{
	private Image image;
	
	/**
	 * Constructs an alien with a location of (0,0), a width
	 * height of 30 and a speed of 0
	 */
	public Alien()
	{
		this(0,0,30,30,0);
	}

	/**
	 * Constructs an alien with a specified location, a width
	 * height of 30 and a speed of 0
	 * @param x the x location
	 * @param y the y location
	 */
	public Alien(int x, int y)
	{
		super(x,y,30,30,0);
	}

	/**
	 * Constructs an alien with a specified location, a width
	 * height of 30 and a specified speed
	 * @param x the x location
	 * @param y the y location
	 * @param s the speed
	 */
	public Alien(int x, int y, int s)
	{
		super(x,y,30,30,s);
	}

	/**
	 * Constructs an alien with a specified location, width,
	 * height andspeed
	 * @param x the x location
	 * @param y the y location
	 * @param w the width
	 * @param h the height
	 * @param s the speed
	 */
	public Alien(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h, s);
		try
		{
			URL url = getClass().getResource("/images/alien.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println("No");
		}
	}

	/**
	 * Adjusts the x and y of the Alien based on a specified direction, and the speed at which the
	 * alien is moving. 
	 * @param direction the direction in which to move
	 */
   public void move(String direction)
	{
	 if(direction.equals("RIGHT"))
		{
			if(getX() == 800+getWidth())
			{
				setX(0-getWidth());
			}
			else
			{
				setX(getX()+getSpeed());
			}
		}
		if(direction.equals("LEFT"))
		{
			if(getX() == 0-getWidth())
			{
				setX(800+getWidth());
			}
			else
			{
				setX(getX()-getSpeed());
			}
		}
		if(direction.equals("DOWN"))
		{
			if(getY() == 600+getHeight())
			{
				setY(0-getHeight());
			}
			else
			{
				setY(getY()+getSpeed());
			}
		}
		if(direction.equals("UP"))
		{
			if(getY() == 0-getHeight())
			{
				setY(600+getHeight());
			}
			else
			{
				setY(getY()-getSpeed());
			}
		
		}
	
	}

	/**
	 * Draws an Alien in a specified Graphics window
	 * @param window the Graphics window
	 */
	public void draw( Graphics window )
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	
}
