/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import javax.swing.*;

/**
 * Class which is used to launch/initiate the game
 */
@SuppressWarnings("serial")
public class LaunchGame extends JFrame
{
	/**
	 * Method used the instantiate a launch game object
	 * @param args not used
	 */
	public static void main(String args[])
	{
		new LaunchGame();
	}
	
	/**
	 * Creates a default LauncheGame, labeling the panel and setting its size and adding the main gameboard into the frame
	 */
	public LaunchGame()
	{
		setSize(1280, 720);
		setTitle("Elon Musk Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Gameboard());
		setLocationRelativeTo(null);
		setVisible(true);
	}
}