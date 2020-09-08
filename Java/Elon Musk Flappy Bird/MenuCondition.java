/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.awt.font.*;
import javax.imageio.ImageIO;
import java.net.*;

/**
 * The class that holds everything for the main menu while the menu is actively being displayed
 */
public class MenuCondition extends Condition 
{
	private String[] options = {"Play Game", "Instructions", "Quit"};
	private int current;
	private BackgroundImage backGround;
	private boolean help = false;
	private boolean back = false;
	private Rectangle rectStart;
	private Rectangle rectHelp;
	private Rectangle rectExit;
	private Rectangle rectBack;
	private BufferedImage bird;
	private BufferedImage pipe;
	private BufferedImage marsBar;
	private BufferedImage cherry;
	private BufferedImage star;
	
	/**
	 * Creates a MenuCondition object based off of the current condition manager
	 * @param condition the ConditionManager to enable the main menu to display
	 */
	public MenuCondition(ConditionManager condition) 
	{
		super(condition);
		current = 0;
		backGround = new BackgroundImage();
		rectStart = new Rectangle(Gameboard.WIDTH / 2 - 140, 270, 210, 40);
		rectHelp = new Rectangle(Gameboard.WIDTH / 2 - 140, 345, 220, 40);
		rectExit = new Rectangle(Gameboard.WIDTH / 2 - 140, 420, 80, 40);
		rectBack = new Rectangle(10 ,5 ,70 ,30);
		try
		{
			URL url = getClass().getResource("/images/elon smoking the joint0.png");
			bird = ImageIO.read(url);
			URL url2 = getClass().getResource("/images/Tesla Pipe.png");
			pipe = ImageIO.read(url2);
			URL url3 = getClass().getResource("/images/MARSBAR.png");
			marsBar = ImageIO.read(url3);
			URL url4 = getClass().getResource("/images/cherry.png");
			cherry = ImageIO.read(url4);
			URL url5 = getClass().getResource("/images/star.png");
			star = ImageIO.read(url5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Abstract method not needed to be overridden
	 */
	public void init() 
	{
	}
	
	/**
	 * Abstract method overriden which in this class is in charge of simply keeping the background moving
	 */
	public void tick() 
	{ 
		backGround.move();
	}

	/**
	 * Displays all of the options within the game and based off of current values will update as to what the user wants to do
	 * @param g the graphics object to be drawn with
	 */
	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		backGround.draw(g2);
		if(!help)
		{
			g2.drawImage(bird,500,50,200,100,null);
			AffineTransform transform = g2.getTransform();
			transform.translate(350,200);
			g2.transform(transform);
			g2.setColor(Color.WHITE);
			FontRenderContext frc = g2.getFontRenderContext();
			TextLayout t1 = new TextLayout(new String("ELON MUSK FLAPPY BIRD"), g.getFont().deriveFont(45F), frc);
			g2.setStroke(new BasicStroke(5f));
			g2.draw(t1.getOutline(null));
		}
		for(int i = 0; i < options.length; i++)
		{
			if(i == current)
			{
				g2.setColor(new Color(66, 244, 167));
			}
			else
			{
				g2.setColor(Color.WHITE);
			}
			if(!help)
			{
				g2.setFont(new Font("font", Font.CENTER_BASELINE, 40));
				g2.drawString(options[i], 150, 100+(i * 75));
			}
			else
			{
				if(back)
				{
					g2.setColor(new Color(66, 244, 167));
				}
				else
				{
					g2.setColor(Color.WHITE);
				}
				g2.setFont(new Font("font",Font.BOLD,25));
				g2.drawString("Back",15,30);
				g2.setColor(Color.GREEN);
				g2.drawRect(80,60,1100,600);
				g2.setFont(new Font("font",Font.BOLD,35));
				g2.setColor(Color.WHITE);
				g2.drawString("How to Play:", 525, 45);
				g2.setColor(Color.GRAY);
				g2.fillRect(200,165,150,50);
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("font",Font.BOLD,35));
				g2.drawString("Press   space    to make            Jump",100, 200);
				g2.drawImage(bird,480,150,150,100,null);
				g2.drawString("Avoid  the    ",240,350);
				g2.drawImage(pipe,420,250,120,150,null);
				g2.drawString("Get the Highest Score",220,480);
				g2.setColor(Color.BLUE);
				g2.drawRect(700,100,460,400);
				g2.drawString("Collect power ups: ",775,150);
				g2.drawImage(marsBar,725,200,150,50,null);
				g2.setColor(Color.GREEN);
				g2.setFont(new Font("font",Font.BOLD,20));
				g2.drawString("Mars Bar",755,270);
				g2.drawString("Increases your score gained by 2",900,235);
				g2.drawImage(cherry,752,300,100,50,null);
				g2.setColor(Color.RED);
				g2.drawString("Cherry",770,370);
				g2.drawString("Slows the pipes down",900,335);
				g2.setFont(new Font("font",Font.BOLD,12));
				g2.drawString("**If your score is less than 10, adds 1 to your score**",860,350);
				g2.drawImage(star,770,400,50,50,null);
				g2.setFont(new Font("font",Font.BOLD,20));
				g2.setColor(Color.YELLOW);
				g2.drawString("Star",775,470);
				g2.drawString("Makes you invincible",900,435);
				g2.setColor(Color.MAGENTA);
				g2.setFont(new Font("font",Font.BOLD,45));
				g2.drawString("Good Luck Have Fun! :)",400,600);
			}
		}
	}

	/**
	 * Determines whether or not which options has been pressed by the user
	 * @param p the point at which the user has clicked
	 */
	public void mousePressed(Point p) 
	{
		Point point = new Point(p);
		if(rectStart.contains(point))
		{
			condition.conditions.push(new GameCondition(condition));
			condition.conditions.remove(0);
		}
		if(rectHelp.contains(point))
		{
            help = true;
		}
		else if(rectExit.contains(point))
		{
			System.exit(0);
		}
		else if(rectBack.contains(point))
		{
			help = false;
		}
	}
	
	/**
	 * Creates a rectangle and determines the user's mouse movement to help see which option the user selects
	 * @param p the point at which the user's mouse has moved to
	 */
	public void mouseMoved(Point p) 
	{
		Point point = new Point(p);
		if(rectStart.contains(point))
		{
			current = 0;
		}
		else if(rectHelp.contains(point))
		{
			current = 1;
		}
		else if(rectExit.contains(point))
		{
			current = 2;
		}
		else if(rectBack.contains(point))
		{
			back = true;
		}
		else
		{
			back = false;
		}
	}

	/**
	 * Abstract keyListener method not used
	 */
	public void keyPressed(int key) {}
	
}
