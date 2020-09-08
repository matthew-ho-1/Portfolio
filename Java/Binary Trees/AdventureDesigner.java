//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 5
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

import java.util.Scanner;
/**
 * The main class that will guide the user through making and playing the game
 */
public class AdventureDesigner 
{
	private static SceneTree tree;
	/**
	 * The main method of AdventureDesigner
	 * @param args
	 * 		  not used
	 */
	public static void main(String[] args) 
	{
		try
		{
			tree = new SceneTree();
			System.out.println("Creating a story...");
			System.out.print("Please enter a title: ");
			Scanner in = new Scanner(System.in);
			String title = in.nextLine();
			System.out.print("Please enter a scene: ");
			String scene = in.nextLine();
			tree.addNewNode(title, scene);
			System.out.println();
			System.out.println("Scene #" + tree.getCursor().getSceneID() + " added.");
			System.out.println();
			String choice = "";
			do
			{
			  try 
			  {
				  printMenu();
				  System.out.print("Please enter a selection: ");
				  choice = in.next().toUpperCase();
				  switch(choice)
				  {
				  	case "A": in.nextLine();
				  			  System.out.println();
				  			  System.out.print("Please enter a title: ");
				  			  String t = in.nextLine();
				  			  System.out.print("Please enter a scene: ");
				  			  String s = in.nextLine();
				  			  tree.addNewNode(t,s);
				  			  System.out.println();
				  			  System.out.println("Scene #" + tree.getCursor().getNumScene() + " added.");
				  			  System.out.println();
				  			  break;
				  	case "R": System.out.println();
				  		      System.out.print("Please enter an option: ");
				  		      String remove = in.next();
				  		      tree.removeScene(remove);
				  		      System.out.println();
				  			  break;
				  	case "S": System.out.println();
				  			  tree.getCursor().displayFullScene();
				  		      System.out.println();
				  			  break;
				  	case "P": System.out.println();
				  		      System.out.println(tree.toString());
				  			  System.out.println();
				  			  break;
				  	case "B": System.out.println();
				  			  tree.moveCursorBackwards();
				  			  System.out.println("Successfuly moved to " + tree.getCursor().getTitle());
				  			  System.out.println();
				  			  break;
				  	case "F": System.out.println();
				  			  System.out.print("Which option do you wish to go to: ");
				  			  String forward = in.next();
				  			  tree.moveCursorForward(forward);
				  			  System.out.println();
				  			  System.out.println("Successfuly moved to " + tree.getCursor().getTitle());
				  			  System.out.println();
				  			  break;
				  	case "G": playGame();
				  			  break;
				  	case "N": System.out.println();
				  			  System.out.println(tree.getPathFromRoot());
				  			  System.out.println();
				  			  break;
				  	case "M": System.out.println();
				  			  System.out.print("Move current scene to: ");
				  			  int num = in.nextInt();
				  			  tree.moveScene(num);
				  			  System.out.println();
				  			  System.out.println("Successfully moved scene");
				  			  System.out.println();
				  			  break;	  	
				  }
			  }
			  catch(FullSceneException e)
			  {
				System.out.println();
				System.out.println("You cannot add another scene!");
				System.out.println();
			  }
			  catch(NoSuchNodeException e)
			  {
				System.out.println();
				System.out.println("That option does not exist.");
				System.out.println();
			  }
			}
			while(!choice.equals("Q"));
		}
		catch(FullSceneException e)
		{
			System.out.println();
			System.out.println("Hey");
			System.out.println();
		}
		System.out.println();
		System.out.println("Program terminating normally...");
	}
	
	/**
	 * A method that starts at the root of the tree and displays the scene. 
	 */
	public static void playGame()
	{
		Scanner input = new Scanner(System.in);
		SceneNode node = tree.getRoot();
		String option;
		System.out.println();
		System.out.println("Now beginning game...");
		System.out.println();
		while(!node.isEnding())
		{
			System.out.println(node.getTitle());
			System.out.println(node.getSceneDescription());
			System.out.println();
			if(node.getLeft() != null)
			System.out.println("A) " + node.getLeft().getTitle());
			if(node.getMiddle() != null)
			System.out.println("B) " + node.getMiddle().getTitle());
			if(node.getRight() != null)
			System.out.println("C) " + node.getRight().getTitle());
			System.out.println();
			System.out.print("Please enter an option: ");
			option = input.next().toUpperCase();
			System.out.println();
			switch(option)
			{
				case "A": node = node.getLeft();
						  break;
				case "B": node = node.getMiddle();
						  break;
				case "C": node = node.getRight();
						  break;
			}
		}
		System.out.println(node.getTitle());
		System.out.println(node.getSceneDescription());
		System.out.println();
		System.out.println("The End");
		System.out.println();
		System.out.println("Returning back to creation mode...");
		System.out.println();
	}
	
	/**
	 * A method that prints the menu
	 */
	public static void printMenu()
	{
		System.out.println("A) Add Scene");
		System.out.println("R) Remove Scene");
		System.out.println("S) Show Current Scene");
		System.out.println("P) Print Adventure Tree");
		System.out.println("B) Go Back A Scene");
		System.out.println("F) Go Forward A Scene ");
		System.out.println("G) Play Game");
		System.out.println("N) Print Path To Cursor");
		System.out.println("M) Move Scene");
		System.out.println("Q) Quit");
		System.out.println();
	}

}
