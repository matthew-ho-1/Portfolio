/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */
 
 import java.awt.*;
 import java.awt.image.*;
 import javax.imageio.*;
 import java.net.*;
 
 //gap between pipes should be 150
 //two pipes should add up to equal 570
 
 /**
  * The class representing the two pipes, one coming from the top of the screen and one coming from the bottom which are moved and adjusted based off of the game and it's score
  */
 public class Pipe
 {
 	private int x; //x value of pipes, should be same as they move
 	private int y; //y value of bottom pipe
 	private int y2; //y value of top pipe
 	private int height1; //height of bottom pipe
 	private int height2; //height of top pipe
 	private int xVel; //how fast the pipes move
 	private BufferedImage image;
 	private BufferedImage image2;
 	
 	/**
 	 * Creates the two pipes given an x value in which they will be initially placed
 	 * @param xx the left x coordinate of the pipes
 	 */
 	public Pipe(int xx)
 	{
 		x = xx;
 		xVel = -2;
 		
 		int rand1 = (int) (Math.random() * 300) + 140;
 		int rand2 = 520-rand1;
 		
 		height1 = rand1;
 		height2 = rand2;
 			
 		y = 720 - height1;
 		y2 = 0;
 		try
 		{
 			URL url = getClass().getResource("/images/Tesla Pipe.png");
 			image2 = ImageIO.read(url);
 			URL url2 = getClass().getResource("/images/Tesla Pipe2.png");
 	 		image = ImageIO.read(url2);
 		}
 		catch(Exception e) {e.printStackTrace();}
 	}
 	
 	/**
 	 * Gives the x coordinate of the pipes
 	 * @return the x coordinates of the pipes
 	 */
 	public int getX()
 	{
 		return x;
 	}
 	
 	/**
 	 * Gives the y value of the top left of the bottom pipe
 	 * @return the y value of the top left of the bottom pipe
 	 */
 	public int getY2()
 	{
 		return y2;
 	}
 	
 	/**
 	 * Gives the y value of the top left of the top pipe
 	 * @return the y value of the top left of the top pipe
 	 */
 	public int getY()
 	{
 		return y;
 	}
 	
 	/**
 	 * Gives the height of the bottom pipe
 	 * @return the height of the bottom pipe
 	 */
 	public int getHeight2()
 	{
 		return height2;
 	}
 	
 	/**
 	 * Sets the x location of the pipes to and updated location within the game and will generate a new gap between the two pipes
 	 * @param xLoc the x coordinate to where the pipes will be moved to
 	 */
 	public void setX(int xLoc)
 	{
 		x = xLoc; // all this resets the pipes to start coming from the right again and change up their sizes
 		
 		int rand1 = (int) (Math.random() * 300) + 140;
 		int rand2 = 520-rand1;
 		
 		height1 = rand1;
 		height2 = rand2;
 		
 		y = 720-height1;
 		y2 = 0;
 	}
 	
 	/**
 	 * Abstract method that is overridden and moves the pipes based off of the current xVelocity value and resets the x value if it moves off screen
 	 */
 	public void move()
 	{
 		x += xVel;
 		if(x < -120)
 		{
 			setX(1280);
 		}
 	}
 	
 	/**
 	 * Draws the pipes within the panel based off of their x and y values and respected heights
 	 * @param g2 the graphics2D object to be drawn with
 	 */
 	public void draw(Graphics2D g2)
 	{
 		g2.drawImage(image, x - 80, y, 285, height1, null); //bottom pipe
 		g2.drawImage(image2, x - 85, y2, 285, height2, null); // top pipe
 	}
 	
 	/**
 	 * Sets the x velocity of the pipes to a new speed
 	 * @param xSpeed the value of which the pipes' x velocity will be updated to
 	 */
 	public void setxVel(int xSpeed)
 	{
 		xVel = xSpeed;
 	}
 	
 	/**
 	 * Gives the x velocity at which the pipes are moving at
 	 * @return the x velocity at which the pipes are currently moving at
 	 */
 	public int getxVel()
 	{
 		return xVel;
 	}
 	
 	/**
 	 * Gives the height of the top pipe
 	 * @return the height of the top pipe
 	 */
 	public int getHeight1()
 	{
 		return height1;
 	}
 	
 	/**
 	 * Increases the velocity of the pipes in the negative direction so they move faster
 	 */
 	public void incVel()
 	{
 		xVel -= 2;
 	}
 	
 	/**
 	 * Resets the x velocity of the pipes to the original value of -2, mainyl for when the game resets and the velocity will be reset
 	 */
 	public void resetVel()
 	{
 		xVel = -2;
 	}
 	
 }