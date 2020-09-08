/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The class that contains the panel and manages the threads active within the game and menus
 */
@SuppressWarnings("serial")
public class Gameboard extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	private boolean running;
	private Thread thread;
	private ConditionManager condition;
	
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	
	/**
	 * Creates a default Gameboard object by adding all of the listeners and setting the size of the panel
	 */
	public Gameboard()
	{
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		running = false;
		condition = new ConditionManager();
		start();
		
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener((MouseMotionListener) this);
		addKeyListener(this);
	}
	
	/**
	 * Starts the threads and activates the game
	 */
	public void start()
	{
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Repaints the objects within the panel during the run time
	 */
	public void run() 
	{
		long startTime, elapsedTime, waitTime;
	
		while(running)
		{
			startTime = System.nanoTime();
			tick();
			repaint();
			
			elapsedTime = System.nanoTime() - startTime;
			waitTime = (((long) (1000/60) - elapsedTime) / 1000000);
			
			if(waitTime <= 0)
			{
				waitTime = 8;
			}
			
			try
			{
				Thread.sleep(waitTime);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Abstract method that has been overridden and calls the current condition's tick method to determine what is to be updated
	 */
	public void tick()
	{
		condition.tick();
	}
	
	/**
	 * Drwas everything within the panel, based off of the current condition
	 * @param g the graphics object to be drawn with
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		condition.paint(g);
	}

	/**
	 * Abstract mouseListener method not used
	 */
	public void mouseClicked(MouseEvent e) 
	{
	}

	/**
	 * Determine where the mouse point has been clicked on in the given condition screen
	 * @param e the MouseEvent to activate actions after the mouse is pressed on in the panel
	 */	
	public void mousePressed(MouseEvent e) 
	{
		condition.mousePressed(e.getPoint());
	}
	
	/**
	 * Abstract mouseListener method not used
	 */
	public void mouseReleased(MouseEvent e) 
	{
	}
	
	/**
	 * Abstract mouseListener method not used
	 */
	public void mouseEntered(MouseEvent e) 
	{	
	}
	
	/**
	 * Abstract mouseListener method not used
	 */
	public void mouseExited(MouseEvent e) 
	{
	}
	
	/**
	 * Abstract mouseListener method not used
	 */
	public void mouseDragged(MouseEvent e) {}

	/**
	 * Determines where on the panel the mouse has been pressed also based off of the current condition within the game
	 * @param e the MouseEvent representing the point clicked in the panel
	 */
	public void mouseMoved(MouseEvent e) 
	{
		condition.mouseMoved(e.getPoint());
	}

	/**
	 * Abstract keyListener methods not used
	 */
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	/**
	 * Determines which key the user has inputed also based off of the current condition within the game
	 * @param e the KeyEvent representing which key the user has inputed
	 */
	public void keyPressed(KeyEvent e) 
	{
		condition.keyPressed(e.getKeyCode());
	}


}