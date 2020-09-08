//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 5
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

/**
 * A class that represents a specific scene
 */
public class SceneNode 
{
	private String title;
	private String sceneDescription;
	private int sceneID;
	private SceneNode left;
	private SceneNode middle;
	private SceneNode right;
	private SceneNode parent;
	private static int numScenes = 0;
	
	/**
	 * A constructor that constructs a SceneNode
	 */
	public SceneNode(String t, String sD)
	{
		title = t;
		sceneDescription = sD;
		left = null;
		middle = null;
		right = null;
		numScenes++;
		sceneID = numScenes;
	}
	
	/**
	 * An empty constructor that constructs a SceneNode
	 */
	public SceneNode()
	{
		
	}
	
	/**
	 * A method that gets the title of the node
	 * @return
	 * 		 the title of the node
	 */
	public String getTitle()
	{
		return title;
	}
	
	
	/**
	 * A method that gets the scene description of the node
	 * @return
	 * 		  the scene description of the node
	 */
	public String getSceneDescription()
	{
		return sceneDescription;
	}
	
	/**
	 * A method that gets the scene id of the node
	 * @return
	 * 		 the scene id of the node
	 */
	public int getSceneID()
	{
		return sceneID;
	}
	
	/**
	 * A method that gets the number of scenes created
	 * @return
	 * 		  the number of scenes created
	 */
	public int getNumScene()
	{
		return numScenes;
	}
	
	/**
	 * A method that gets the left node
	 * @return
	 * 		 the left node
	 */
	public SceneNode getLeft()
	{
		return left;
	}
	
	/**
	 * A method that sets the left node
	 * @param newLeft
	 * 		  the new left node
	 */
	public void setLeft(SceneNode newLeft)
	{
		left = newLeft;
	}
	
	/**
	 * A method that gets the middle node
	 * @return
	 * 		 the middle node
	 */
	public SceneNode getMiddle()
	{
		return middle;
	}
	
	/**
	 * A method that sets the middle node
	 * @param newMiddle
	 * 		  the new middle node
	 */
	public void setMiddle(SceneNode newMiddle)
	{
		middle = newMiddle;
	}
	
	/**
	 * A method that gets the right node
	 * @return
	 * 		  the right node
	 */
	public SceneNode getRight()
	{
		return right;
	}
	
	/**
	 * A method that sets the right node
	 * @param newRight
	 * 		  the new right node
	 */
	public void setRight(SceneNode newRight)
	{
		right = newRight;
	}
	
	/**
	 * A method that gets the parent node
	 * @return
	 * 		 the parent node
	 */
	public SceneNode getParent()
	{
		return parent;
	}
	
	/**
	 * A method that sets the parent node
	 * @param newParent
	 * 		  the new parent node
	 */
	public void setParent(SceneNode newParent)
	{
		parent = newParent;
	}
	
	/**
	 * A method that sets the number of scenes
	 * @param num
	 * 		  the specified number of scenes
	 */
	public void setNumScene(int num)
	{
		numScenes = num;
	}
	
	/**
	 * A method that sets the scene to the first available slot in the child
	 * nodes
	 * @param scene
	 * 		  the specified scene to add
	 * @throws FullSceneException
	 * 		  when the current node doesn't have any empty child nodes
	 */
	public void addSceneNode(SceneNode scene)throws FullSceneException
	{
		if(left != null && middle != null && right != null)
		{
			numScenes--;
			throw new FullSceneException();
		}
		if(left == null)
		   setLeft(scene);
		else if(middle == null)
		   setMiddle(scene);
		else if(right == null)
		   setRight(scene);
	}
	
	/**
	 * A method that determines if this scene is an ending for a storyline
	 * @return
	 * 		 whether or not if the scene is the ending of a storyline
	 */
	public boolean isEnding()
	{
		if(left == null && middle == null && right == null)
			return true;
		return false;
	}
	
	/**
	 * A method that outputs scene information, as would be shown 
	 * during gameplay
	 */
	public void displayScene()
	{
		System.out.println(title);
		System.out.println(sceneDescription);
		if(left != null)
			System.out.println("A) " + left.getTitle());
		if(middle != null)
			System.out.println("B) " + middle.getTitle());
		if(right != null)
			System.out.println("C) " + right.getTitle());
	}
	
	/**
	 * A method that outputs all information about a scene, as would be
	 * shown in creation mode
	 */
	public void displayFullScene()
	{
		System.out.println("Scene ID #" + sceneID);
		System.out.println("Title: " + title);
		System.out.println("Scene: " + sceneDescription);
		if(left == null && middle == null && right == null)
			System.out.println("Leads to: NONE");
		if(left != null && middle == null && right == null)
			System.out.println("Leads to: " + "'" + left.getTitle() + "'" + " (#" + left.getSceneID() + ")");
		if(left != null && middle != null && right == null)
			System.out.println("Leads to: " + "'" + left.getTitle() + "'" + " (#" + left.getSceneID() + "), " + "'" + middle.getTitle() + "'" + " (#" + middle.getSceneID() + ")");
		if(left != null && middle != null && right != null)
			System.out.println("Leads to: " + "'" + left.getTitle() + "'" + " (#" + left.getSceneID() + "), " + "'" + middle.getTitle() + "'" + " (#" + middle.getSceneID() + "), " + "'" + right.getTitle() + "'" + " (#" + right.getSceneID() + ")");
	}
	
	/**
	 * A method that returns a string representing a brief summary of 
	 * this SceneNode, as would be shown in the tree
	 * @return 
	 * 		  A string representing a brief summary of this SceneNode, as would be shown in the tree
	 */
	public String toString()
	{
		String tree = "";
		tree += getTitle() + " (#" + getSceneID() + ")";
		return tree;
	}
	
	

}
