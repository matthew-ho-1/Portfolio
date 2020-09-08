//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 

import java.awt.*;
import java.awt.Rectangle;
import java.util.*;
/**
   A building that can be positioned anywhere on the screen.
*/
public class Building
{
   private int x;
   private int y;
   private int r;
   private int g;
   private int b;
   
   /**
      Constructs a building with a given position
      @param xPos the x coordinate of the building
      @param yPos the y coordinate of the building
   */
   public Building(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
      r = (int)(Math.random()*255);
      g = (int)(Math.random()*255);
      b = (int)(Math.random()*255);
   }

   /**
      Draws the building.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   int s = 100;
	
	   g2.setColor(new Color(r,g,b));
	   g2.fillRect(x, y, 950,900);
	   g2.setColor(Color.BLACK);
	   for(int i=-60; i<=1200;i+=180)
	   {
		   for(int j = 350; j<=650; j+=150)
		   {
			   g2.fillRect(j, i, s, s);
		   }
		   for(int k = 1050; k<=1200; k+=150)
		   {
			   g2.fillRect(k,i,s,s);
		   }
		   
	   }
   }


}