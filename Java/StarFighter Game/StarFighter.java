//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

//*** No changes will be made to this class

import javax.swing.JFrame;
import java.awt.Component;

/**
 * Represents the environment for running a StarFighter game
 */
public class StarFighter extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	/**
	 * Constructs a StarFighter JFrame
	 */
	public StarFighter()
	{
		super("STARFIGHTER");
		setSize(WIDTH,HEIGHT);

		OuterSpace theGame = new OuterSpace();
		((Component)theGame).setFocusable(true);

		getContentPane().add(theGame);

		setVisible(true);
	}
	
	/**
	 * Launches the StarFighter game
	 * @param args not used
	 */
	public static void main( String args[] )
	{
		StarFighter run = new StarFighter();
	}
}