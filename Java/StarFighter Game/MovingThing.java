//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

//*** Note:  Because MovingThing is abstract, it will compile
//*** without implementing the Moveable methods.  However, subclasses
//*** would not compile without the interface methods.  

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a moving thing.  Cannot be instantiated
 */
public abstract class MovingThing implements Moveable
{
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private int speed;

	/**
	 * Constructs a MovingThing object with default values of 10
	 */
	public MovingThing()
	{
		xPos = 10;
		yPos = 10;
		width = 10;
		height = 10;
		speed = 10;
	}

	/** 
	 * Constructs a MovingThing object at a specified x and y position
	 * with width and height and speed of 10
	 * @param x the specified x
	 * @param y the specified y
	 */
	public MovingThing(int x, int y)
	{
		xPos = x;
		yPos = y;
		width = 10;
		height = 10;
		speed = 10;
	}
	
	/**
	 * Constructs a MovingThing object with specified position,
	 * width and height and speed
	 * @param x the specified x
	 * @param y the specified y
	 * @param w the specified w
	 * @param h the specified h
	 * @param s the specified s
	 */
	public MovingThing(int x, int y, int w, int h, int s)
	{
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		speed = s;
	}
	/**
	 * Sets the position of a moveable object
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void setPos( int x, int y)
	{
	  xPos = x;
	  yPos = y;
	}
	/** 
	 * Sets the x position of a moveable object
	 * @param x the new x
	 */
    public void setX( int x )
    {
      xPos = x;
    }
    /**
     * Sets the y position of a moveable object
     * @param y the new y
     */
    public void setY( int y )
    {
      yPos = y;	
    }
	/**
	 * Gets the x position of a moveable object
	 * @return the x position
	 */
    public int getX()
    {
      return xPos;
    }
    /**
     * Gets the y position of a moveable object
     * @return the y position
     */
    public int getY()
    {
      return yPos;	
    }
	/** 
	 * Gets the width of a moveable object
	 * @return the width
	 */
	public int getWidth()
	{
	  return width;
	}
	/** 
	 * Gets the height of a moveable object
	 * @return the height
	 */
	public int getHeight()
	{
	 return height;	
	}
	/** 
	 * Sets the width of a moveable object
	 * @param w the new width
	 */
	public void setWidth( int w )
	{
	  width = w;
	}
	/**
	 * Sets the height of a moveable object
	 * @param h the new height
	 */
	public void setHeight( int h )
	{
	  height = h;
	}
	/**
	 * Sets the speed of a moveable object
	 * @param s the new speed
	 */
    public void setSpeed( int s )
    {
      speed = s;	
    }
    /**
     * Gets the speed of a moveable object
     * @return the current speed
     */
	public int getSpeed()
	{
	  return speed;
	}
	/**
	 * Moves a MovingThing according to a specified direction
	 * @param direction the direction in which to move
	 */
	public abstract void move(String direction);
	
	/**
	 * Draws a MovingThing in a specified Graphics window
	 * @param window the Graphics window
	 */
	public abstract void draw(Graphics window);

	/**
	 * Returns a string version of the MovingThing
	 * @return a string version of the MovingThing
	 */
	public String toString()
	{
		return xPos + " " + yPos + " " + width + " " + height + " " + speed;
	}
}