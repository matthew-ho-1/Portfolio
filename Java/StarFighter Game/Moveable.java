//© A+ Computer Science  -  www.apluscompsci.com
//Name - Matthew Ho
//Date -
//Period - 2nd

public interface Moveable
{
	/**
	 * Sets the position of a moveable object
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void setPos( int x, int y);
	
	/** 
	 * Sets the x position of a moveable object
	 * @param x the new x
	 */
    public void setX( int x );
    
    /**
     * Sets the y position of a moveable object
     * @param y the new y
     */
    public void setY( int y );

	/**
	 * Gets the x position of a moveable object
	 * @return the x position
	 */
    public int getX();
    
    /**
     * Gets the y position of a moveable object
     * @return the y position
     */
    public int getY();

	/** 
	 * Gets the width of a moveable object
	 * @return the width
	 */
	public int getWidth();
	
	/** 
	 * Gets the height of a moveable object
	 * @return the height
	 */
	public int getHeight();
	
	/** 
	 * Sets the width of a moveable object
	 * @param w the new width
	 */
	public void setWidth( int w );
	
	/**
	 * Sets the height of a moveable object
	 * @param h the new height
	 */
	public void setHeight( int h );
	
	/**
	 * Sets the speed of a moveable object
	 * @param s the new speed
	 */
    public void setSpeed( int s );
    
    /**
     * Gets the speed of a moveable object
     * @return the current speed
     */
	public int getSpeed();
}
