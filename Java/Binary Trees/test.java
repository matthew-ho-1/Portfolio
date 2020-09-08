
public class test 
{
	public static void main(String[] args) throws FullSceneException, NoSuchNodeException
	{
		SceneTree tree = new SceneTree();
		tree.addNewNode("Intro", "123");
		tree.addNewNode("a", "123");
		tree.addNewNode("b", "123");
		tree.moveCursorForward("A");
		tree.addNewNode("1", "123");
		tree.addNewNode("2", "123");
		tree.addNewNode("3", "123");
		tree.moveCursorForward("A");
		System.out.println(tree.toString());
		tree.moveScene(1);
		System.out.println(tree.toString());
	}
}
