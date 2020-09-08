/*
 * Name: Matthew Ho
 * Date Completed: 11/9
 * Period: 2nd
 */


import java.util.*;

/**
 * Uses the Maze class to solve a maze if possible
 */
public class MazeSolver
{
	public static void main(String args[]) 
	{
		Scanner input = new Scanner(System.in);
		// seed is used to generate the same maze for testing
		System.out.print("Enter random starting seed  ===>>  ");
		int seed = input.nextInt();
	
		Maze maze = new Maze(seed);
		System.out.println ("STARTING MAZE: \n" + maze);
		maze.solveMaze();
		System.out.println ("ENDING MAZE: \n" + maze);
		System.out.println (maze.mazeSolution()); 
	}
}



