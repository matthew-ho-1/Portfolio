//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

/**
 * An AlienHorde represents a group of Aliens
 */
public class AlienHorde
{
	private List<Alien> aliens;

	/**
	 * Constructs an AlienHorde of a specified size
	 * @param size the number of aliens
	 */
	public AlienHorde(int size)
	{
	}

	/**
	 * Adds an alien to the horder
	 * @param al the alien
	 */
	public void add(Alien al)
	{
	}

	/**
	 * Draws all of the aliens in the horde
	 * @param window the graphics window
	 */
	public void drawEmAll( Graphics window )
	{
	}

	/**
	 * Moves the entire alien horde down
	 */
	public void moveEmAll()
	{
	}

	/**
	 * Checks for collisions between active ammo and aliens in the horde and removes
	 * any ammo and alien that have collided
	 * @param shots the list of Ammo to check
	 */
	public void removeDeadOnes(List<Ammo> shots)
	{
	}

	/**
	 * Returns a String version of the alien horde
	 * NOTE:  just use the ArrayList toString
	 */
	public String toString()
	{
		return "";
	}
}
