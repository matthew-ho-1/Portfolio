/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.util.*;
import java.awt.*;

/**
 * A class that represents based off of the java structure of a stack that holds child classes of the superclass condition
 */
public class ConditionManager
{
	public Stack<Condition> conditions;
	
	/**
	 * Creates a default ConditionManager and adds the current condition into it
	 */
	public ConditionManager()
	{
		conditions = new Stack<Condition>();
		conditions.push(new MenuCondition(this));
	}
	
	/**
	 * Abstract method overwritten that will call the current condition's tick method
	 */
	public void tick()
	{
		conditions.peek().tick();
	}
	
	/**
	 * Method which will call the current condition's tick method
	 * @param g the graphics object to be passed on and drawn with
	 */
	public void paint(Graphics g)
	{
		conditions.peek().paint(g);
	}
	
	/**
	 * Method which will call the current condition's mousePressed method
	 * param p the location on the screen where the mouse has been pressed
	 */
	public void mousePressed(Point p)
	{
		conditions.peek().mousePressed(p);
	}
	
	/**
	 * Method which will call the current condition's mouseMoved method
	 * @param p the location on the screen where the mouse has been moved to
	 */
	public void mouseMoved(Point p)
	{
		conditions.peek().mouseMoved(p);
	}
	
	/**
	 * Method which will call the current condition's keyPressed method
	 * @param key the key on the keyboard which has been pressed and will be passed on
	 */
	public void keyPressed(int key)
	{
		conditions.peek().keyPressed(key);
	}
}
