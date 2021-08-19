package structure;

public class MyExpressionTree {
public MyBinaryTreeNode root;

	public MyExpressionTree() {
		
	}

	/**
	 * this is a static method to evaluate any expression tree by passing in its root
	 * 
	 * This code is taken from the lecture "Lecture = Project 4 - Expression Tree" and written by
	 * Professor Xheng for use in this project
	 * 
	 * @param rt	root of tree
	 * @return		the evaluated expression
	 */
	
	public int evaluate(MyBinaryTreeNode rt) {
		if(rt == null)
			return -1;
		
		if(rt.left == null && rt.right == null)
			return Character.getNumericValue((Character)rt.data);
		
		switch((Character)rt.data) {
			case '-':
				return evaluate(rt.left) - evaluate(rt.right);
			case '+':
				return evaluate(rt.left) + evaluate(rt.right);
			case '*':
				return evaluate(rt.left) * evaluate(rt.right);
			case '/':
				return evaluate(rt.left) / evaluate(rt.right);
			case '%':
				return evaluate(rt.left) % evaluate(rt.right);
		}
		
		return -1;
	}
	
	public String preorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.

		return preorderHelper(root) + "\n";
	}

	/*
	 * The private helper method for preorderTraversal().
	 */
	private String preorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return "";		
		return "\t" + rt.data + preorderHelper(rt.left) + preorderHelper(rt.right);
	}

	/**
	 * Inorder traverse the tree and print out each node.
	 */
	public String inorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.
		return inorderHelper(root) + "\n";
	}

	/*
	 * The private helper method for inorderTraversal().
	 */
	private String inorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return "";
		return inorderHelper(rt.left) + "\t" + rt.data + inorderHelper(rt.right);
	}

	/**
	 * Postorder traverse the tree and print out each node.
	 */
	public String postorderTraversal()
	{//return type of a String is for subclass to override this method and return a String.
		return postorderHelper(root) + "\n";
	}

	/*
	 * The private helper method for postorderTraversal().
	 */
	private String postorderHelper(MyBinaryTreeNode rt)
	{
		if(rt == null)	return "";

		return postorderHelper(rt.left) + postorderHelper(rt.right) + "\t" + rt.data;
	}
}
