//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.JOptionPane;

/**
 * Represents the graphic environment of the StarFighter game
 */
public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;
	private Ammo ammo;
	private Bullets shots;
	private AlienHorde horde;
	

	/* uncomment once you are ready for this part
	private AlienHorde horde;
	*/

	private boolean[] keys;
	private BufferedImage back;

	/**
	 * Creates an OuterSpace object
	 */
	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables when ready
		//Ship, Alien, etc
		//Your ship should be at least 40 x 40
		ship = new Ship(400,400,40,40,5);
		
		alienOne = new Alien(200,200,50,50,2);
		alienTwo = new Alien(400,100,50,50,2);
		ammo = new Ammo();
		shots = new Bullets();
		int size = 10;
		horde = new AlienHorde(size);
		int count = 0;
		for(int i = 50; i<840; i+= 70)
		{
			if(count < size)
			{
				horde.add(new Alien(i,40,40,40,1));
				count++;
			}
		}
	
	
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

	/**
	 * Updates the graphics window
	 * @param window the graphics window
	 */
	public void update(Graphics window)
	{
		paint(window);
	}

	/**
	 * Updates the contents of the graphics window using double buffering
	 * @param window the graphics window
	 */
	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		if(keys[0]) // See keyPressed and keyReleased methods below
			ship.move("LEFT");
		// add more code to move the ship different directions
		if(keys[1])
			ship.move("RIGHT");
		if(keys[2])
			ship.move("UP");
		if(keys[3])
			ship.move("DOWN");
		//add code to move Alien
		alienOne.move("LEFT");
		alienTwo.move("RIGHT");
		if(keys[4])
		{
			shots.add(new Ammo(ship.getX() + 21, ship.getY(), 7, 7, 2));
			keys[4] = false;
		}
		
	


		//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
		
		
		//add code to draw all of the stuff when ready.  Draw everything to graphToBack.
	    ship.draw(graphToBack);
	    alienOne.draw(graphToBack);
	    alienTwo.draw(graphToBack);
	    if(ammo != null)
	    {
	    	ammo.move("UP");
	    	ammo.draw(graphToBack);	
	    }
	    
	    shots.moveEmAll();
	    shots.cleanEmUp();
	    if(Math.random()*100 < 20)
	    {
	    	horde.moveEmAll();
	    	horde.removeDeadOnes(shots.getList());
	    }
	    
		
		twoDGraph.drawImage(back, null, 0, 0); // draws the drawn image to the screen all at once
	}

	/**
	 * Updates the keys array based on the key that was pressed
	 * Uses the arrow keys left, right, up and down
	 * @param e the KeyEvent representing the pressed key
	 */
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	/**
	 * Updates the keys array based on the key that was released
	 * @param e the KeyEvent representing the released key
	 */
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	/**
	 * Needed to satisfy the KeyListener
	 * @param e not used
	 */
	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

	/**
	 * Runs the thread in an infinite loop with a pause of 5 miliseconds between updates
	 */
   	public void run()
   	{
   		boolean finished = false;
   		try
   		{
	   		while(!finished)
	   		{
	   		   Thread.currentThread().sleep(10);
	           repaint();
	           for(int i = 0; i<horde.getSize(); i++)
	           if(horde.getList().get(i).getY()>=520)
	           {
	        	   JOptionPane.showMessageDialog(null, "You Lose!");
	        	   finished = true;
	        	   System.exit(0);
	           }   
	        }
      	}
      	catch(Exception e)
      	{
      	}
  	}
}

