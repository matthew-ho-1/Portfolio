//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 

import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;
import java.util.Random;

/**
	An airplane that can be positioned anywhere on the screen.
*/

public class Airplane implements Animatable, Runnable
{
   private int x;
   private int y;
   private int r;
   private int g;
   private int b;
   
   /**
      Constructs an Airplane with a given position
      @param xPos the x coordinate of the airplane
      @param yPos the y coordinate of the airplane
   */
   public Airplane(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
      r = (int)(Math.random()*255);
      g = (int)(Math.random()*255);
      b = (int)(Math.random()*255);
   }

   /**
      Moves the Airplane
      @param dx the amount to move in x-direction
      @param dy the amount to move in y-direction
   */
   public void move(int dx, int dy)
   {
	  x += dx;
      if(x > 400)
	  {
	    x = -150;
      }
   }
   
  /**
      Draws the airplane
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(new Color(r,g,b));
		g2.fillRoundRect(x, y, 150, 30, 30, 30);
		g2.fillOval(x,y-30,30,60);
		g2.fillRect(x+70, y+20, 30, 40);
   }
   
   /**
 	 * Animates the airplane
 	 */
   public void run()
   {
	   Random rand = new Random();
	
	   int r1 = rand.nextInt(5)+1;
	   
	   while(!Thread.interrupted())
	   {
		  move(r1,0);
		   try
		   {
			   Thread.sleep(50);
		   }
		   catch(InterruptedException e)
		   {
			   
		   }
	   }
   }

}