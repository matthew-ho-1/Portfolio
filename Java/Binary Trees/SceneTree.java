//Name: Matthew Ho
//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 5
//CSE214
//Recitation 8-	TA Robert Ignatowicz  

/**
 * A class that represents a collection of SceneNode objects arranged as a ternary tree.
 */
public class SceneTree 
{
	private SceneNode root;
	private SceneNode cursor;
	private SceneNode found;
	
	/**
	 * A constructor that constructs the SceneTree
	 */
	public SceneTree()
	{
		root = null;
		cursor = null;
	}
	
	/**
	 * A method that gets the cursor from the tree
	 * @return
	 * 		  the cursor from the tree
	 */
	public SceneNode getCursor()
	{
		return cursor;
	}
	
	/**
	 * A method that gets the root from the tree
	 * @return
	 * 		  the root from the tree
	 */
	public SceneNode getRoot()
	{
		return root;
	}
	
	/**
	 * A method that moves the cursor to the parent node
	 * @throws NoSuchNodeException
	 * 		   when the current node does not have a parent
	 */
	public void moveCursorBackwards() throws NoSuchNodeException
	{
		if(cursor.getParent() == null)
			throw new NoSuchNodeException();
		cursor = cursor.getParent();
	}
	
	/**
	 * A method that moves the cursor to the appropriate child node
	 * @param option
	 * 		  A specified option to select left, middle, or right child
	 * @throws NoSuchNodeException
	 * 		   when the current node does not have such child
	 */
	public void moveCursorForward(String option) throws NoSuchNodeException
	{
		option = option.toUpperCase();
		if(option.equals("A"))
		{
			if(cursor.getLeft() != null)
				cursor = cursor.getLeft();
			else
				throw new NoSuchNodeException();
		}
		else if(option.equals("B"))
		{
			if(cursor.getMiddle() != null)
				cursor = cursor.getMiddle();
			else 
				throw new NoSuchNodeException();
		}
		else if(option.equals("C"))
		{
			if(cursor.getRight() != null)
				cursor = cursor.getRight();
			else
				throw new NoSuchNodeException();
		}
	}
	
	/**
	 * A method that creates a new SceneNode object as a child of the current node 
	 * with specified values
	 * @param title
	 * 		  the specified title
	 * @param sceneDescription
	 * 		  the specified scene description
	 * @throws FullSceneException
	 * 		   when the current node doesn't have any available child positions
	 */
	public void addNewNode(String title, String sceneDescription) throws FullSceneException
	{
		if(root == null)
		{
			SceneNode newNode = new SceneNode(title, sceneDescription);
			root = newNode;
			cursor = root;
		}
		else if(cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null)
			throw new FullSceneException();
		else
		{
			SceneNode newNode = new SceneNode(title, sceneDescription);
			newNode.setParent(cursor);
			cursor.addSceneNode(newNode);
		}
		
	}
	
	/**
	 * A method that removes the specified child node 
	 * @param option
	 * 		  the specified scene description
	 * @throws NoSuchNodeException
	 * 		   when the current node does't have any such child
	 */
	public void removeScene(String option) throws NoSuchNodeException
	{
		if(cursor.getLeft() == null && cursor.getMiddle() == null && cursor.getRight() == null)
			throw new NoSuchNodeException();
		if(option.equals("A"))
		{
			System.out.println(cursor.getLeft().getTitle() + " removed.");
			cursor.setLeft(null);
			cursor.setLeft(cursor.getMiddle());
			cursor.setMiddle(cursor.getRight());
			cursor.setRight(null);
		}
		else if(option.equals("B"))
		{
			System.out.println(cursor.getMiddle().getTitle() + " removed.");
			cursor.setMiddle(null);
			cursor.setMiddle(cursor.getRight());
			cursor.setRight(null);
		}
		else if(option.equals("C"))
		{
			System.out.println(cursor.getRight().getTitle() + " removed.");
			cursor.setRight(null);
		}
		cursor.setNumScene(cursor.getNumScene() + 1);
	}
	
	/**
	 * A helper method that moves the node at cursor to be a child of the node with the specified ID
	 * @param SceneIDToMoveTo
	 *   	  the specified ID to move to
	 * @throws NoSuchNodeException
	 * 		   when there doesn't exist a SceneNode with the specified ID
	 * @throws FullSceneException
	 * 		   when the SceneNode specified by the specified ID doesn't have any available
	 * 		   child positions
	 */
	public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException
	{
		if(sceneIDToMoveTo < 0 || sceneIDToMoveTo > cursor.getNumScene() + 1)
			throw new NoSuchNodeException();
		SceneNode node = findNode(root,sceneIDToMoveTo);
		if(node.getLeft() != null && node.getMiddle() != null && node.getRight() != null)
			throw new FullSceneException();
		if(cursor.getParent().getLeft() == cursor)
			cursor.getParent().setLeft(null);
		else if(cursor.getParent().getMiddle() == cursor)
			cursor.getParent().setMiddle(null);
		else if(cursor.getParent().getRight() == cursor)
			cursor.getParent().setRight(null);
		if(node.getLeft() == null)
			node.setLeft(cursor);
		else if(node.getMiddle() == null)
			node.setMiddle(cursor);
		else if(node.getRight() == null)
			node.setRight(cursor);
		cursor.setParent(node);
	}
	
	/**
	 * A method to find the node using a given id
	 * @param node
	 * 		  the node to call the recursive method on
	 * @param id
	 * 		  the given id
	 * @return
	 * 		  the node that matches the id
	 */
	private SceneNode findNode(SceneNode node, int id)
	{
		if(node.getSceneID() != id)
		{
			if(node.getLeft() != null)
				findNode(node.getLeft(),id);
			else if(node.getMiddle() != null)
				findNode(node.getMiddle(),id);
			else if(node.getRight() != null)
				findNode(node.getRight(),id);
		}
		else
			found = node;
		return found;
	}
	
	/**
	 * A method that constructs a String showing the path from the root of the tree to the 
	 * currently selected SceneNode
	 * @return
	 * 		  a String showing the path from the root of the tree to the currently selected SceneNode
	 */
	public String getPathFromRoot()
	{
		String path = "";
		String[] titles = new String[20];
		int index = 0;
		int count = 0;
		SceneNode temp = cursor;
		while(temp != root)
		{
			titles[index] = temp.getTitle();
			index++;
			temp = temp.getParent();
		}
		for(int i = index - 1; i >= 0; i--)
		{
			if(count == 0)
			{
				path += root.getTitle() + ", ";
				count++;
			}
			if(i == 0)
			   path += titles[i];
			else
			   path += titles[i] + ", ";
		}
		return path;
	}
	
	/**
	 * A helper method that constructs a string representation of the tree
	 * @returns
	 * 		   a string representation of the tree
	 */
	public String toString()
	{
		return toString(root,"",0);
	}
	
	/**
	 * A method that constructs a string representation of the tree
	 * @param node
	 * 		  the node to use the recursive method on
	 * @param option
	 * 		  which child the method should print out
	 * @param count
	 * 		  how many spaces the node should print out before the node
	 * @return
	 */
	private String toString(SceneNode node,String option, int count)
	{
		String subtree = "";
		if(root == node)
		{
			if(node == cursor)
				subtree += node.toString() + " *" + "\n";
			else
				subtree += node.toString() + "\n";
			count += 4;
			subtree += toString(node.getLeft(),"A)",count);
			subtree += toString(node.getMiddle(),"B)",count);
			subtree += toString(node.getRight(),"C)",count);
		}
		else if(node != null)
		{
			if(node == cursor)
				subtree += " ".repeat(count) + option +  " " + node.toString() + " *" + "\n";
			else
				subtree += " ".repeat(count) + option +  " " + node.toString() + "\n";
			count += 4;
			subtree += toString(node.getLeft(),"A)",count);
			subtree += toString(node.getMiddle(),"B)",count);
			subtree += toString(node.getRight(),"C)",count);
		}
		return subtree;
	}
	
}
