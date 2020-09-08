//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 



import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
	A sun that can be positioned anywhere on the screen.
*/


public class Sun implements Animatable, Runnable
{
   private int x;
   private static int y;
   
   /**
      Constructs a sun with a given top left corner
      @param xPos the x coordinate of the sun
      @param yPos the y coordinate of the sun
   */
   public Sun(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }

   /**
      Moves the sun
      @param dx the amount to move in x-direction
      @param dy the amount to move in y-direction
   */
   public void move(int dx, int dy)
   {
	  
      y -= dy;
      if(y == -80)
      {
    	  y = 400;
      } 
   }
   
  /**
      Draws the sun.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   int s = 10;
	   g2.setColor(Color.YELLOW);
	   g2.fillOval(x,y,50,50);
	   g2.fillRect(x-10, y, s, s);
	   g2.fillRect(x-15, y+30, s, s);
	   g2.fillRect(x+5, y+55, s, s);
	   g2.fillRect(x+40, y+54, s, s);
	   g2.fillRect(x+60, y+30, s, s);
	   g2.fillRect(x+55, y, s, s);
	   g2.fillRect(x+22, y-15, s, s);
	
   }
   
   public static int getY()
   {
	   return y;
   }
   
   /**
 	 * Animates the sun
 	 */
   public void run()
   {
	   Random rand = new Random();
	  
	   int r1 = rand.nextInt(2)+1;
	   int r2 = rand.nextInt(5)+1;
	   
	   while(!Thread.interrupted())
	   {
		   if(r1 == 1)
			   move(0,r2);
		   else
			   move(0,r2);
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