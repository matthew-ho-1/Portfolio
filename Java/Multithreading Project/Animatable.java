import java.awt.*;

/**
 * Shows an animation of moving cars
 */
public interface Animatable
{
	/**
      Moves the picture. Specified parameters can be negative.
      @param dx the amount to move in x-direction
      @param dy the amount to move in y-direction
   */
   public void move(int dx, int dy);
   
   /**
      Draws the picture.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2);
}

