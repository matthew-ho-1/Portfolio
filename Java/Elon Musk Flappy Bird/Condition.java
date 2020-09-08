/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */
import java.awt.*;

/**
 * An abstract class that each condition will inherit it's methods and override them based off of the current game status
 */
public abstract class Condition 
{
	public ConditionManager condition;
	
	/**
	 * Creates a default Condition based off of a provided ConditionManager
	 * @param condition the ConditionManager to be used
	 */
	public Condition(ConditionManager condition)
	{
		this.condition = condition;
		init();
	}
	
	/**
	 * Abstract method to be overridden by the sub condition class which should instantiate new objects when required
	 */
	public abstract void init();
	
	/**
	 * Abstract method to be overridden by the sub condition class which should determine what updates every time the game ticks
	 */
	public abstract void tick();
	
	/**
	 * Abstract method to be overridden by the sub condition class which should update the panel based off of everything that takes place within the tick method
	 * @param g the Graphics object to be used to draw with
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * Abstract method to be overridden by the sub condition class which should determined whether or not the mouse has been pressed to further determine an action
	 * @param p the point at which the mouse has been pressed
	 */
	public abstract void mousePressed(Point p);
	
	/**
	 * Abstract method to be overridden by the sub condition class which should determine whether the mouse has been moved to a specific point or not
	 * @param p the point at which the mouse has been moved to
	 */
	public abstract void mouseMoved(Point p);
	
	/**
	 * Abstract method to be overridden by the sub condition class which should determine whether the correct key has been pressed to activate the updates to specified objects
	 * @param key the key on the keyboard which has been pressed
	 */
	public abstract void keyPressed(int key);
}
