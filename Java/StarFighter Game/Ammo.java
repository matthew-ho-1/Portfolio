//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

//*** NOTE:  Unlike the other MovingThings, there is no image associated with Ammo.
//*** The draw method just uses Graphics method to set the color and draw a filled rectangle.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Represents moving Ammo tha can be drawn in a graphics window
 */
public class Ammo extends MovingThing
{	
	/**
	 * Contructs an ammo at location (0,0) with a width, height and speed of 0
	 */
	public Ammo()
	{
		super(0,0,0,0,0);
	}

	/**
	 * Constructs an ammo at a specified location with a width and height of 10 and speed of 0
	 * @param x the x location
	 * @param y the y location
	 */
	public Ammo(int x, int y)
	{
		super(x,y,10,10,0);
	}

	/**
	 * Constructs an ammo at a specified location with a specified width, height and speed
	 * @param x the x location
	 * @param y the y location
	 * @param w the width
	 * @param h the height
	 * @param s the speed
	 */
	public Ammo(int x, int y, int w, int h, int s)
	{
		super(x,y,w,h,s);
	}

	/**
	 * Draws the ammo in the graphics window
	 * @param window the graphics window
	 */
	public void draw( Graphics window )
	{
	   window.setColor(Color.YELLOW);
	   window.fillRect(getX(),getY(),getWidth(),getHeight());
	   
	}
	
	/**
	 * Adjusts the y of the ammo based on a specified direction, either UP or DOWN and the 
	 * speed at which the ammo is moving. 
	 * @param direction the direction in which to move
	 */
	public void move( String direction )
	{
		if(direction.equals("DOWN"))
		{
			setY(getY()+getSpeed());
		}
		if(direction.equals("UP"))
		{
			setY(getY()-getSpeed());
		}
	}
}
