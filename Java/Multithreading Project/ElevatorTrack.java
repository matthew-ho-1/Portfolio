import java.awt.*;


/**
   An elevator track that can be positioned anywhere on the screen.
*/
public class ElevatorTrack 
{
   private int x;
   private int y;
   
   /**
      Constructs an elevator track with a given position
      @param xPos the x coordinate of the elevator track
      @param yPos the y coordinate of the elevator track
   */
   public ElevatorTrack(int xPos, int yPos)
   {
      x = xPos;
      y = yPos;
   }

   /**
      Draws the elevator track
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	   g2.setColor(Color.DARK_GRAY);
	   g2.fillRect(x, y, 100, 1280);

   }


}