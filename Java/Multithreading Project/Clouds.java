//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 



import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;
/**
	A cloud that can be positioned anywhere on the screen.
*/
public class Clouds implements Animatable, Runnable
{
   private int x;
   private int y;
   
   /**
      Constructs a cloud with a given position
      @param xPos the x coordinate of the cloud
      @param yPos the y coordinate of the cloud
   */
   public Clouds(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }	

   /**
      Moves the cloud
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
   }
   
  /**
      Draws the cloud
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(new Color(247, 254, 255));
		g2.fillRoundRect(x, y, 100, 20, 50, 50);
		g2.fillOval(x + 5, y - 10, 50, 30);
		g2.fillOval(x + 40, y - 20, 40, 40);
		g2.fillOval(x + 75, y - 10, 20, 20);
   }
   
   
   /**
  	 * Animates the clouds
  	 */
   public void run()
   {
	   Random rand = new Random();
	  
	   int r1 = rand.nextInt(5)+1;
	   
	   while(!Thread.interrupted())
	   {
		   move(-r1,0);
		   try
		   {
			   Thread.sleep(75);
		   }
		   catch(InterruptedException e)
		   {
			   
		   }
	   }
   }
	   
}

