//Name - Matthew Ho	
//Date - 2/12
//Period- 2nd 



import java.awt.Color;
import java.awt.Graphics2D;

/**
   Sky that can be positioned anywhere on the screen.
*/
public class Sky 

{
   private int x;
   private int y;
   private boolean time = true;
	private int r, g, b;
	private int r1 = 99, g1 = 69, b1 = 128;
	private int r2 = 20, g2 = 20, b2 = 50;
	private int changeSpeed, daySpeed;
	private int dayFrame = 0;
	private int changeFrame = 0;
   
   /**
      Constructs the sky with a given position
      @param xPos the x coordinate of the sky
      @param yPos the y coordinate of the sky
   */
   public Sky(int xPos, int yPos,int changeTime, int dayTime)
   {
      x = xPos;
      y = yPos;
      changeSpeed = changeTime;
	  daySpeed = dayTime;
		if(!time)
		{
			r = r2;
			g = g2;
			b = b2;
		}
		else
		{
			r = r1;
			g = g1;
			b = b1;
		}
   }
   
   public void changeSky()
	{
		if(dayFrame >= daySpeed)
		{
			if(!time)
			{
				time = true;
			}
			else
			{
				time = false;
			}
			dayFrame = 0;
			
		}
		else
		{
			dayFrame++;
		}
		
		if(changeFrame >= changeSpeed)
		{
			if(!time)
			{
				if(r < r1)
				{
					r++;
				}
				if(g < g1)
				{
					g++;
				}
				if(b < b1)
				{
					b++;
				}
			}
			else
			{
				if(r > r2)
				{
					r--;
				}
				if(g > g2)
				{
					g--;
				}
				if(b > b2)
				{
					b--;
				}
			}
			changeFrame = 0;
		}
		else
		{
			changeFrame++;
		}
		
	}
   

   /**
      Draws the sky
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setPaint(new Color(r, g, b));
	   g2.fillRect(x, y, 500, 400);

	   
   }
}