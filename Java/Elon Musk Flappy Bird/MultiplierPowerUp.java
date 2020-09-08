/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.net.*;

/**
 * A class that represents the mars bar/multiplier power up which when collected adds two to the score every time the bird passes a pipe instead of of one
 */
public class MultiplierPowerUp
{
    private int xVel;
    private int x;
    private int y;
    private BufferedImage image;

    /**
     * Creates a default MultiplierPowerUp object with its provided image and x position and velocity
     */
    public MultiplierPowerUp()
    {
        xVel = -2;
        x = 460;
        y = (int) (Math.random() * 400) + 100;
        try
        {
        	URL url = getClass().getResource("/images/MARSBAR.png");
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
     * Moves the powerup when needed during the game based off of its current x position and velocity
     */
    public void move()
    {
        x += xVel;
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
            image = ImageIO.read(new File("images\\MARSBAR.PNG"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
     * Draws the powerup based off of its x and y location
     * @param g2 the graphics2D object to be used to draw with
     */
    public void draw(Graphics2D g2)
    {
    	g2.drawImage(image,x,y,150,50,null);
    }
    

}