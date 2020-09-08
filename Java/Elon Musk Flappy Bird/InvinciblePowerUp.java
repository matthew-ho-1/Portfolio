/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */
import java.awt.*;
import java.io.*;
import java.net.URL;

import javax.imageio.*;
import java.awt.image.*;

/**
 * A class that represents the star/Invincible power up which when collected will allow the player to pass through pipes without having to worry about collision for a total of 5 pipes
 */
public class InvinciblePowerUp
{
    private int x;
    private int y;
    private int xVel;
    private BufferedImage image;

    /**
     * Creates a default InvinciblePowerUp object with its provided x position and velocity
     */
    public InvinciblePowerUp()
    {
        xVel = -6;
        x = 460;
        y = (int) (Math.random() * 400) + 100;
        try
        {
        	URL url = getClass().getResource("/images/star.png");
            image = ImageIO.read(url);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Sets the x velocity of the power up to a given value
     * @param xV the x velocity to be updated to
     */   
    public void setxVel(int xV)
    {
    	xVel = xV;
    }
    
    /**
     * Sets the x location of the power up to a given x coordinate within the game
     * @param xPos the x location to be updated to
     */   
    public void setX(int xPos)
    {
    	x = xPos;
    	y = (int) (Math.random() * 400) + 100;
        try
        {
        	image = ImageIO.read(new File("images\\star.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Moves the powerup when needed during the game based off of its current x position and velocity
     */  
    public void move()
    {
        x += xVel;
    }

    /**
     * Gives the current x location of the power up
     * @return the current x location of the power up
     */    
    public int getX()
    {
        return x;
    }

    /**
     * Gives the current y location of the power up
     * @return the y location of the power up
     */    
    public int getY()
    {
    	return y;
    }

    /**
     * Sets the image of the power up to a new image provided
     * @param i the image to which the power up will be updated to
     */   
    public void setImage(BufferedImage i)
    {
    	image = i;
    }

    /**
     * Draws the powerup based off of its x and y location
     * @param g2 the graphics2D object to be used to draw with
     */    
    public void draw(Graphics2D g2)
    {
    	g2.drawImage(image,x,y,50,50,null);
    }



}