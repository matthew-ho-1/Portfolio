//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 


import java.awt.Color;
import java.awt.Graphics2D;

/**
   Ground (combination of ocean and beach) that can be positioned anywhere on the screen.
*/
public class Ground
{
   private int x;
   private int y;
   
   /**
      Constructs the ground with a given position
      @param xPos x coordinate of the ground
      @param yPos y coordinate of the ground
   */
   public Ground(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }

   /**
      Draws the ground
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(Color.YELLOW);
	   g2.fillRect(x, y, 500, 600);
	   g2.setColor(Color.BLUE);
	   g2.fillRect(x, y, 500, 400);
	   g2.setColor(Color.BLACK);
	  
	   
   }
}
