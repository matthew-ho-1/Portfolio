/**
 * Vijay Singh
 * Charles Bacani
 * Matt Ho
 * 1st period/2nd period
 * Mrs. Gallatin
 */

import java.awt.*;
import java.net.URL;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.font.*;
import javax.imageio.*;

/**
 * The class which represents the endCondition or what happens when the game ends
 */
public class EndCondition extends Condition
{
	private Medals medals;
	private BufferedImage playButton;
	private int score;
	private BackgroundImage backGround;
	private BufferedImage anImage;
	private	Rectangle menuRect;
	private boolean current = false;
	
	/**
	 * Creates a default EndCondition based off of a given ConditionManager
	 * @param condition the ConditionManager to enable the endScreen to be displayed
	 */
	public EndCondition(ConditionManager condition) 
	{
		super(condition);
		medals = new Medals();
		backGround = GameCondition.getbackGroundImage();
		medals = GameCondition.getMedal();
		menuRect = new Rectangle(0,0,160,100);
		score = GameCondition.getScore();
		try
		{
			URL url = getClass().getResource("/images/PlayButton.png");
			playButton = ImageIO.read(url);
		}
		catch(Exception e) {}
		
	}

	/**
	 * An implemented abstract method not used
	 */
	public void init() 
	{
	}

	/**
	 * An implemented abstract method which still moves the background but does not allow for any pipes or birds to be displayed
	 */
	public void tick() 
	{
		backGround.move();
	}

	/**
	 * Updates the panel by redrawing everything based off of the score and medals based off of the score
	 * @param g the graphics object to be used to draw with
	 */
	public void paint(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		backGround.draw(g2);
		Font font = new Font("GAME OVER", Font.BOLD, 100);
		AffineTransform transform = g2.getTransform();
		transform.translate(125,185);
		g2.transform(transform);
		g2.setColor(Color.GREEN);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout t1 = new TextLayout(new String("GAME OVER"), g.getFont().deriveFont(45F), frc);
		g2.setStroke(new BasicStroke(5f));
		g2.draw(t1.getOutline(null));
		g2.setColor(new Color(252,157,14));
		g2.drawRoundRect(-10,25,300,200,25,25);
		font = new Font("MEDAL", Font.BOLD, 25);
		g2.setFont(font);
		g2.drawString("MEDAL", 25, 75);
		g2.drawString("SCORE", 175, 75);
		g2.drawString("" + score, 175, 100);
		if(score > 9)
		{
			anImage = medals.getMedal();
			g2.drawImage(anImage, 35, 120, anImage.getWidth(), anImage.getHeight(), null);
		}
		g2.drawImage(playButton, 150-(playButton.getWidth()/2), 250, playButton.getWidth(), playButton.getHeight(), null);
		if(current)
		{
			g2.setColor(new Color(66, 244, 167));
		}
		else
		{
			g2.setColor(Color.WHITE);
		}
		g2.drawString("Back to Menu",-120,-150);
	}

	/**
	 * Determines which button has been pushed leading the user to either play the game again or taking them back to the main menu
	 * @param p the point at which the user has clicked on the screen
	 */
	public void mousePressed(Point p) 
	{
		Point point = new Point(p);
		Rectangle playRect = new Rectangle(275-(playButton.getWidth()/2), 430, playButton.getWidth(), playButton.getHeight());
		if(playRect.contains(point))
		{
			condition.conditions.push(new GameCondition(condition));
			condition.conditions.remove(0);
		}
		else if(menuRect.contains(point))
		{
			condition.conditions.push(new MenuCondition(condition));
			condition.conditions.remove(0);
		}
	}

	/**
	 * Deternines where the mouse has been moved to
	 * @param p the point at which the user has clicked on the screen
	 */
	public void mouseMoved(Point p) 
	{
		Point point = new Point(p);
		if(menuRect.contains(point))
		{
			current = true;
		}
		else
		{
			current = false;	
		}
	}

	/**
	 * Abstract keyListener method not used
	 */
	public void keyPressed(int key) {}

}
