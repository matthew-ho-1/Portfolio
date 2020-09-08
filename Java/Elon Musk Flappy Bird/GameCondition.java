/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

/**
 * The class that holds everything for game components and while the game is actively being played
 */
public class GameCondition extends Condition
{
	private Bird bird;
	private PowerUps powerUps;
	private ArrayList<Pipe> pipes;
	private Ellipse2D birdy;
	private Rectangle topPipe1, botPipe1, topPipe2, botPipe2;
	private static int score;
	private boolean multiplied, slowed, invincible, speedChange = false;
	private boolean drawn = true;
	private static Medals medals;
	private static BackgroundImage backGround;
	private int spawn, count, timer, pipeCount;
	private Rectangle marsBar, cherry, star;
	private String pwrUp, currentPowerUp = "";

	/**
	 * Creates a GameCondition object based off the current condition manager
	 * @param condition the ConditionManager to enable the game to be played
	 */
	public GameCondition(ConditionManager condition) 
	{
		super(condition);
		pipes =  new ArrayList<Pipe>();
		score = 0;
		count = 0;
		timer = 0;
		pipeCount = 0;
		spawn = (int)((Math.random()*9)+1);
		powerUps = new PowerUps();
		pwrUp = powerUps.choosePowerUp();
		medals = new Medals();
		pipes.add(new Pipe(1400));
		pipes.add(new Pipe(2102));
		backGround = new BackgroundImage();
	}

	/**
	 * An implemented abstract method which constructs the bird for this condition
	 */
	public void init() 
	{
		bird = new Bird();
	}
	
	/**
	 * Gives the current score for further usage when updated objects within the game
	 * @return the current score
	 */
	public static int getScore()
	{
		return score;
	}
	
	/**
	 * Gives the current medal to be displayed
	 * @return the medal to be displayed
	 */
	public static Medals getMedal()
	{
		return medals;
	}
	
	/**
	 * Gives the backgroundImage to be displayed within the game
	 * @return the backgroundImage being displayed
	 */
	public static BackgroundImage getbackGroundImage()
	{
		return backGround;
	}

	/**
	 * An implemented abstract method that updates everything accordingly during the GameCondition
	 */
	public void tick() 
	{
		for(int i = 0; i < pipes.size(); i++)
		{
			pipes.get(i).move();
		}
		bird.tick();
		if(invincible)
		{
			if(pipeCount >= 5)
			{
			    drawn = true;
				invincible = false;
				pipeCount = 0;
				currentPowerUp = "";
			}
		}
		else
		{
			collisionHit();
		}
		backGround.move();
		if(pipes.get(0).getX() <= 50 && pipes.get(0).getX() > 50 + pipes.get(0).getxVel() + 1 || pipes.get(1).getX() <= 50 && pipes.get(1).getX() > 50 + pipes.get(1).getxVel() + 1)
		{
			score++;
			timer++;
			speedChange = true;
			if(timer == 10)
			{
				spawn = (int)((Math.random()*9)+1);
			}
			if(multiplied)
			{
				score++;
				pipeCount++;
				if(pipeCount >= 5)
				{
					multiplied = false;
					pipeCount = 0;
					currentPowerUp = "";
			        drawn = true;
				}
			}
			if(slowed)
			{
				if(score > 10)
				{
					pipeCount++;
					pipes.get(0).resetVel();
					pipes.get(1).resetVel();
					if(pipeCount >= 5)
					{
						slowed = false;
						pipeCount = 0;
						currentPowerUp = "";
					    drawn = true;
					}
				}
				else
				{
					drawn = true;
					score++;
					slowed = false;
					currentPowerUp = "";
				}
			}
			if(invincible)
			{
				pipeCount++;
			}
		}
		else if(score >= count + 10)
		{
			count = score;
			medals.setCurrentStatus(count);
			backGround.changeBackgroundImage();
			if(speedChange && pipes.get(0).getxVel() > -8)
			{
				timer = 0;
				spawn = (int)((Math.random()*9)+1);
				for(int i = 0; i < pipes.size(); i++)
				{
					pipes.get(i).incVel();
				}
				speedChange = false;
			}
		}
		else if(spawn == timer)
		{
			pwrUp = powerUps.move(pipes.get(0).getxVel());
			hitPowerUp();
		}
	}
	
	/**
	 * Detects whether or not the bird has hit a power up that has been randomly chosen to spawn in the screen
	 */
	public void hitPowerUp()
	{
		if(pwrUp.equals("Mars Bar"))
		{
			marsBar = new Rectangle(powerUps.getX(),powerUps.getY(),150,50);
			if(birdy.intersects(marsBar))
			{
				multiplied = true;
				currentPowerUp = "Mars Bar";
				drawn = false;
			}
		}
		else if(pwrUp.equals("Cherry"))
		{
			cherry = new Rectangle(powerUps.getX(),powerUps.getY(),100,50);
			if(birdy.intersects(cherry))
			{
				slowed = true;
				currentPowerUp = "Cherry";
				drawn = false;
			}

		}
		else if(pwrUp.equals("Star"))
		{
			star = new Rectangle(powerUps.getX(),powerUps.getY(),50,50);
			if(birdy.intersects(star))
			{
				invincible = true;
				currentPowerUp = "Star";
				drawn = false;
			}
		}
		if(score % 10 == 0 && score > 40)
		{
			spawn = (int)((Math.random()*9)+1);
		}
	}

	/**
	 * Updates the panel by redrawing everything based off of updates
	 * @param g the graphics object to be used to draw with
	 */
	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		backGround.draw(g2);
		for(int i = 0; i < pipes.size(); i++)
		{
			pipes.get(i).draw(g2);
		}
		bird.paint(g2);
		Font font = new Font("Score", Font.BOLD, 50);
		g2.setColor(Color.CYAN);
		g2.setFont(font);
		g2.drawString(String.format("Score: %d", score), 0, 50); 
		Font font2 = new Font("Power Up: ", Font.BOLD, 50);
        g2.setFont(font2);
        g2.drawString("Power Up: " + currentPowerUp,700,50);
        if(timer == spawn)
        {
        	if(drawn)
        	{
        		powerUps.draw(g2);
        	}
        }
	}
	
	/**
	 * Detects whether or not the bird hits a pipe or the ground
	 */
	public void collisionHit()
	{
		birdy = new Ellipse2D.Double(bird.getX() + 70, bird.getY(), 65, 65);
		topPipe1 = new Rectangle(pipes.get(0).getX(), pipes.get(0).getY2(), 120, pipes.get(0).getHeight2());
		botPipe1 = new Rectangle(pipes.get(0).getX(), pipes.get(0).getY() + 10, 120, pipes.get(0).getHeight1());
		topPipe2 = new Rectangle(pipes.get(1).getX(), pipes.get(1).getY2(), 120, pipes.get(1).getHeight2());
		botPipe2 = new Rectangle(pipes.get(1).getX(), pipes.get(1).getY() + 10, 120, pipes.get(1).getHeight1());
		if(birdy.intersects(topPipe1) || birdy.intersects(botPipe1) || birdy.intersects(topPipe2) || birdy.intersects(botPipe2) || birdy.getY() > 700)
		{
			condition.conditions.push(new EndCondition(condition));
			condition.conditions.remove(0);
		}
	}
	
	/**
	 * Abstract mouse and keylistener methods not used
	 */
	public void mouseMoved(Point p) {}
	public void mousePressed(Point p) {}
	
	/**
	 * Detects if the space bar key has been pressed which will activate the bird's movement
	 * @param key the key that has been pressed on the keyboard
	 */
	public void keyPressed(int key) 
	{
		if(key == KeyEvent.VK_SPACE)
		{
			bird.flyHigh();
		}
	}
	
}
