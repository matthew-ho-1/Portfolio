//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

//*** CONSTRUCTOR NOTE:  The 5 parameter constructor is the one that loads the image, 
//*** so, ultimately, all of the other constructors need to call that one (like the default constructor does)

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Represents a moving ship that can be drawn in a graphics
 * window.
 */
public class Ship extends MovingThing
{
	private Image image;

	/**
	 * Constructs a ship with a location of (10,10), a width
	 * height and speed of 10
	 */
	public Ship()
	{
		this(10,10,10,10,10);
	}

	/**
	 * Constructs a ship at a specified location with width,
	 * height and speed of 10.
	 * @param x the x location
	 * @param y the y location
	 */
	public Ship(int x, int y)
	{
	  super(x,y,10,10,10);
	}

	/**
	 * Constructs a ship at a specified location with a specified
	 * speed with a width and height of 10.
	 * @param x the x location
	 * @param y the y location
	 * @param s the speed
	 */
	public Ship(int x, int y, int s)
	{
	  super(x,y,10,10,s);
	}

	/**
	 * Constructs a ship at a specified location with a specified
	 * speed, width and height.
	 * @param x the x location
	 * @param y the y location
	 * @param w the width
	 * @param h the height
	 * @param s the speed
	 */
	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h, s);
		
		try
		{
			URL url = getClass().getResource("/images/ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println("ERROR 240: Cannot read file");
		}
	}

	
		
	/**
	 * Adjusts the x and y of the Ship based on a specified direction, and the speed at which the
	 * ship is moving. Ships can move LEFT, RIGHT, UP or DOWN
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
	 * Draws a Ship in a specified Graphics window
	 * @param window the Graphics window
	 */
	public void draw( Graphics window )
	{
   		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	
}
