// Name: Matthew Ho
// Date: 4/11
// Period: 2nd


// TimeTest.java
// This class is used to measure elapsed time in Chapters 18, 36 & 38
// for the analyses of different algorithms.
// It is also used in Chapter 27 as an example of redefining the toString method.


import java.text.DecimalFormat;

/**
 * A class for timing algorithms
 */
class TimeTest
{
	
	private long startNanos;	// tick count at the start of the test
	private long endNanos;		// tick count at the end of the test
	private long nanos;			// elapsed number of ticks
	private long hours;			// elapsed hours
	private long minutes;		// elapsed minutes
	private long seconds;		// elapsed seconds
	private long fractions;		// elapsed fractions of a second
	
	/**
	 * constructor
	 */
	public TimeTest()
	{
		startNanos = 0;
		endNanos = 0;
		nanos = 0;
		hours = 0;
		minutes = 0;
		seconds = 0;
		fractions = 0;
	}
	
	/**
	 * starts the clock
	 */
	public void startClock()
	{
		startNanos = System.nanoTime();
	}


	/**
	 * stops the clock
	 */
	public void stopClock()  
	{
		endNanos = System.nanoTime();
		computeTime();
	}
	
	/**
	 * pauses 
	 */
	public void delay(long n)
	{
		n = n * 1000000;
		long startDelay = System.nanoTime();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.nanoTime();
	}
			
	/**
	 * calculates the time that passed between start and stop
	 */
	private void computeTime()
	{
		nanos = endNanos - startNanos;
		hours = nanos / 3600000000000L;
		long leftOver = nanos % 3600000000000L;
		minutes = leftOver / 60000000000L;
		leftOver = leftOver % 60000000000L;
		seconds = leftOver / 1000000000L;
		fractions = leftOver % 1000000000L;
	}
	
	/**
	 * returns the time as a string
	 */
	public String toString()
	{
		DecimalFormat twos = new DecimalFormat("00");
		DecimalFormat nines = new DecimalFormat("000000000");
		String temp = twos.format(hours) + ":";
		temp = temp + twos.format(minutes) + ":";
		temp = temp + twos.format(seconds) + ".";
		temp = temp + nines.format(fractions);
		return temp;
	}

}

			 