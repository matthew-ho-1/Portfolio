//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 


import java.awt.Color;
import java.util.*;
import java.awt.Graphics2D;

/**
	An oval shaped boat that can be positioned anywhere on the screen.
*/

public class RoundBoat implements Animatable, Runnable
{
   private int x;
   private int y;
   private int r;
   private int g;
   private int b;
   
   /**
      Constructs a boat with a given position
      @param xPos the x coordinate of the boat
      @param yPos the y coordinate of the boat
   */
   public RoundBoat(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
      r = (int)(Math.random()*255);
      g = (int)(Math.random()*255);
      b = (int)(Math.random()*255);
   }

   /**
      Moves the boat
      @param dx the amount to move in x-direction
      @param dy the amount to move in y-direction
   */
   public void move(int dx, int dy)
   {
	  x += dx;
	  if(x < -100)
	  {
		  x = 330;
	  }
	  if(x > 330)
	  {
		  x = -50;
	  }
   }
   
  /**
      Draws the boat
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(new Color(r,g,b));
	   g2.fillRoundRect(x,y,100,30,20,30);
   }
   
   /**
	 * Animates the boat
	 */
   public void run()
   {
	   Random rand = new Random();
	  
	   int r1 = rand.nextInt(2)+1;
	   int r2 = rand.nextInt(5)+1;
	   
	   while(!Thread.interrupted())
	   {
		   if(r1 == 1)
			   move(r2,0);
		   else
			   move(-r2,0);
		   try
		   {
			   Thread.sleep(100);
		   }
		   catch(InterruptedException e)
		   {
		   }
	   }
   }

}