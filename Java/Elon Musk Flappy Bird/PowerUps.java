/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;

/**
 * The class that represents the three powerups and determines whether or not they should spawn
 */
public class PowerUps
{
	private MultiplierPowerUp multi;
	private InvinciblePowerUp inv;
	private SlowDownPowerUp slow;

 	private String pwrUp = "";

	private boolean marsBar = false;
	private boolean cherry = false;
	private boolean star = false;

	private int rand;

	private int x;
	private int y;

	/**
	 * Creates a default PowerUps object and places the three power ups within it
	 */
    public PowerUps()
    {
    	multi = new MultiplierPowerUp();
    	inv = new InvinciblePowerUp();
    	slow = new SlowDownPowerUp();
    }

    /**
     * Randomly chooses which power up should be used
     */
    public String choosePowerUp()
    {
    	rand = (int)(Math.random()*3);
    	switch(rand)
    	{
    		case 0:
    			marsBar = true;
    			pwrUp = "Mars Bar";
    			break;
    		case 1:
    			cherry = true;
    			pwrUp = "Cherry";
    			break;
    		case 2:
    			star = true;
    			pwrUp = "Star";
    			break;
    	}
    	return pwrUp;
    }


    /**
     * Drwas the power up within the panel, but is based off of which power up is actively spawned
     * @param g2 the Graphics2D object to be drawn with
     */
    public void draw(Graphics2D g2)
    {
    	if(marsBar)
    	{
    		multi.draw(g2);
    	}
    	if(cherry)
    	{
    		slow.draw(g2);

    	}
    	if(star)
    	{
    		inv.draw(g2);
    	}
    }

    /**
     * Abstract method that has been overridden and moves the power up based off of the current xVelocity
     * @param xVel the x velocity at which the power up will move relative to the bird
     * @return which power up is currently active
     */
    public String move(int xVel)
    {
    	if(marsBar)
    	{
    		multi.move();
    		multi.setxVel(xVel);
    		if(multi.getX() < -300)
    		{
    			multi.setX(460);
    			marsBar = false;
    			choosePowerUp();
    		}
    	}
    	if(cherry)
    	{
    		slow.move();
    		slow.setxVel(xVel);
    		if(slow.getX() < -300)
    		{
    			slow.setX(460);
    			cherry = false;
    			choosePowerUp();
    		}
    	}
    	if(star)
    	{
    		inv.move();
    		inv.setxVel(xVel);
    		if(inv.getX() < -300)
    		{
    			inv.setX(460);
    			star = false;
    			choosePowerUp();
    		}
    	}
    	return pwrUp;
    }

    /**
     * Gives the x value of the current power up
     * @return the current x location of the power up
     */
    public int getX()
    {
    	if(marsBar)
    	{
    		x = multi.getX();
    	}
    	else if(cherry)
    	{
    		x = slow.getX();
    	}
    	else if(star)
    	{
    		x = inv.getX();
    	}
    	return x;
    }
    
    /**
     * Gives the y value of the current power up
     * @return the current y location of the power up
     */
    public int getY()
    {
    	if(marsBar)
    	{
    		y = multi.getY();
    	}
    	else if(cherry)
    	{
    		y = slow.getY();
    	}
    	else if(star)
    	{
    		y = inv.getY();
    	}
    	return y;

    }
}