import java.awt.*;


/**
   An elevator that can be positioned anywhere on the screen.
*/
public class Elevator implements Animatable,Runnable
{
   private int x;
   private int y;
   private int yVel;
   private int r;
   private int g;
   private int b;
   
   /**
      Constructs an elevator with a given position
      @param xPos the x coordinate of the elevator
      @param yPos the y coordinate of the elevator
   */
   public Elevator(int xPos, int yPos, int yV)
   {
      x = xPos;
      y = yPos;
      yVel = yV;
      r = (int)(Math.random()*255);
      g = (int)(Math.random()*255);
      b = (int)(Math.random()*255);
   }

   /**
      Moves the elevator
      @param dx the amount to move in x-direction
      @param dy the amount to move in y-direction
   */
   public void move(int dx, int dy)
   {
	 y += yVel;
	 if(y < -200)
	 {  
		 yVel = -yVel;
	      r = (int)(Math.random()*255);
	      g = (int)(Math.random()*255);
	      b = (int)(Math.random()*255);
	 }
	 if(y > 950)
	 {
		 yVel = -yVel;
	      r = (int)(Math.random()*255);
	      g = (int)(Math.random()*255);
	      b = (int)(Math.random()*255);
	 }
	 if(yVel == 0)
	 {
		 yVel++;
	 }
	 
   }

   /**
      Draws the elevator
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(Color.WHITE);
	   g2.fillRect(x, y, 100, 100);
	   g2.setColor(Color.BLACK);
	   g2.fillRect(x,y+60, 100, 50);
	   g2.setColor(new Color(r,g,b));
	   g2.fillOval(x+5, y+10,25,25);
	   g2.drawLine(x+17,y+20,x+17,y+60);
	   g2.drawLine(x+17,y+35,x+30,y+60);
	   g2.drawLine(x+17,y+35,x+5,y+60);
	   g2.setColor(new Color(r,g,b));
	   g2.fillOval(x+50, y+10,25,25);
	   g2.drawLine(x+62,y+20,x+62,y+60);
	   g2.drawLine(x+62,y+35,x+75,y+60);
	   g2.drawLine(x+62,y+35,x+50,y+60);
	   
   }
   
   /**
 	 * Animates the Elevators
 	 */
   public void run()
   {    
	   while(!Thread.interrupted())
	   {
		   move(0,0);
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