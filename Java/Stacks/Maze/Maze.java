/*
 * Name: Matthew Ho
 * Date Completed: 11/9
 * Period: 2nd
 */

import java.util.*;

/** 
 *Represents a Maze.  Has methods to traverse the Maze
 */

  
public class Maze
{
	
	private char mat[][];			// 2d character array that stores the maze display
	private Coord currentPosition;		// object that stores current maze position
	private Stack<Coord> visitStack;		// stack that stores location that have been visited
                 
    /*
     * Coord is an inner class that stores a single maze location. 
     */                            
	class Coord	
	{
		private int rPos;
		private int cPos;
		
		public Coord (int r, int c) 		{ rPos = r; cPos = c; }
		public boolean isFree() 			{ return (rPos == 0 && cPos == 0); }
		public void setPos(int r, int c) 	{ rPos+= r; cPos+= c; }
		public String toString() {return "(" + rPos + ", " + cPos + ")";}
	}
	
	/**
	 * constructor which generates the random 12 x 12 maze, and places 
	 * 'X' (walls) around the perimeter with a random starting location
	 * in the last column.
	 * Uses a random value of 0 or 1 to place walls ('X') or open spaces
	 * ('O') in the maze.
	 */  
	public Maze(int seed)
	{
		// so you can generate the same maze for testing purposes
		Random random = new Random(seed); 
		
		// instantiate mat and generate random maze 
		mat = new char[12][12];
		for(int r=0;r<mat.length;r++)
		{
			for(int c=0;c<mat[0].length;c++)
			{
			  int num = random.nextInt(2);
			  if(num == 0 || r == 0 || r == mat.length-1 || c==0 || c == mat[0].length-1)
			  {
			  	mat[r][c] = 'X';
			  }
			  else
			  {
			  	mat[r][c] = 'O';
			  }
			}
		}
		mat[0][0] = 'O';

		// set the starting location to '.', instantiate the stack,
		// set currentPosition and push it onto the stack
		// careful to avoid aliasing
		visitStack = new Stack<Coord>();
		int num = random.nextInt(mat.length);
		currentPosition = new Coord(num,mat.length-1);
		mat[currentPosition.rPos][currentPosition.cPos] = '.';
		visitStack.push(currentPosition);
	}

	/**
	 * returns the contents of the maze
	 * @return the maze
	 */
	public String toString()
	{
		String ret="";
	 	for(int row=0;row<mat.length;row++)
	 	{
	 	   	for(char c:mat[row])
	 	   	{
	 	   	   ret += c + " ";
	 	   	}
	 	   	ret += "\n";
	 	}
	 	return ret;
	}

	/**
	 * This methods solves the maze with private helper method <getMove>.
	 * A loop is needed to repeat getting new moves until either a maze solution
	 * is found or it is determined that there is no way out of the maze.
	 */
	public void solveMaze()
	{
		System.out.println("\n>>>>>   WORKING  ....  SOLVING MAZE   <<<<<\n");
		boolean done = false;
		while(!done)
		{
	  		if(!getMove())
	  		{
			  if(visitStack.size() == 1)
			  {
				done = true;
			  }
			  else
			  {
				visitStack.pop();
				currentPosition = visitStack.peek();
			  }
			}
			if(currentPosition.isFree())
			{
				done = true;
			}
		}
	}


	/**
	 * Short method to display the result of the maze solution.  The maze has a solution if
	 * position 0, 0 has been reached.  
	 * @return a String representing the maze results
	 * Precondition:  solveMaze has been called
	 */
	public String mazeSolution() // no changes to this method
	{
		if (currentPosition.isFree())
			return "\nTHE MAZE HAS A SOLUTION.\n";
		else
			return "\nTHE MAZE HAS NO SOLUTION.\n";
	}

	/**
	 * Determines if a coordinate position is inbounds or not
	 * @return whether or not the coordinate with row r and column
	 * c is in bounds
	 */
	private boolean inBounds(int r, int c)        
	{
		return (r > -1 && r < mat.length && c > -1 && c < mat[0].length);
	}
   
    /*
     * This method checks eight possible positions in a counter-clock wise manner
	 * starting with the (-1,0) position.  If a position is found the method returns
	 * true and the currentPosition coordinates are altered to the new position
	 */
	private boolean getMove() 
	{  
		boolean found = false;
		int currentX = currentPosition.rPos;
		int currentY = currentPosition.cPos;
		if(inBounds(currentX-1,currentY) && mat[currentX-1][currentY] == 'O')
		{
			found = true;
		    currentPosition = new Coord(currentX-1,currentY);
		    visitStack.push(currentPosition);
		    mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX-1,currentY+1) && mat[currentX-1][currentY+1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX-1,currentY+1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX,currentY+1) && mat[currentX][currentY+1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX,currentY+1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX+1,currentY+1) && mat[currentX+1][currentY+1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX+1,currentY+1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX+1,currentY) && mat[currentX+1][currentY] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX+1,currentY);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX+1,currentY-1) && mat[currentX+1][currentY-1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX+1,currentY-1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX,currentY-1) && mat[currentX][currentY-1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX,currentY-1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}
		else if(inBounds(currentX-1,currentY-1) && mat[currentX-1][currentY-1] == 'O')
		{
			found = true;
			currentPosition = new Coord(currentX-1,currentY-1);
			visitStack.push(currentPosition);
			mat[currentPosition.rPos][currentPosition.cPos] = '.';
		}	
		return found; 
	}
  
}