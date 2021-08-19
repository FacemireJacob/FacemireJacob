package project;

import java.io.*;
import structure.*;

/**
 * 
 * This class imports Infix statements from a provided text file and organizes them into preorder, postorder, and inorder expressions, then show the evaluation.
 *  It takes the original infix, each iteration of the expression in each traversal order, and the
 * result, then writes it in a formatted way to an output file. If there is an error that makes an inputted
 * expression invalid, the output file will reflect this, and the code will output in the java console
 * what error it experienced, as well as the particular expression that caused it
 * 
 * 
 * @author Jacob Facemire, 8/13/2021
 * @version 1.0.1
 * 
 * 
 */

public class COSC602_P4_ExpressionTree {
	//Relative path locations for input and output files
	private static String inputFile = "../COSC602_P4_ExpressionInput.txt";
	private static String outputFile = "../COSC602_P4_ExpressionOutput.txt";
	
	public static void test()
	{
		MyDeque deque = new MyDeque();
		MyStack signStack = new MyStack();
				
		//Boolean Used to track if certain logic errors occur
		try
		{
			//Buffered reader which reads text
			BufferedReader textIn = new BufferedReader( new FileReader(inputFile));
			String line = textIn.readLine();
			FileWriter textOut = new FileWriter(outputFile);

			//While loop parses input file line by line, and finds all needed chars to add to either the signstack or postfix Queue
			while(line != null) 
			{
				int parenthesesCheck = 0;
				String infixLine = "";
				
				//I need to initialize this char as a random ascii character 
				char temp2 = '§';
				boolean invalid = false;
				
				for(int i = 0; i < line.length(); i++) 
					{
						char temp = line.charAt(i);
						
						//Integer counter should be 0 at the end if there is an equal amount of '(' and ')'
						if(temp == '(')
							parenthesesCheck++;
						if(temp == ')')
							parenthesesCheck--;
						//This if statement activates when both the current and last character are both digits
						if(Character.isDigit(temp) && Character.isDigit(temp2))
							invalid = true;
						//Activates if character in line is alphabetic
						if(Character.isAlphabetic(temp))
							invalid = true;
						//Activates if multiple operands in a row
						if(temp == '+'||temp == '-'||temp == '*'||temp == '/')
							if(temp2 == '+'||temp2 == '-'||temp2 == '*'||temp2 == '/')
								invalid = true;
						
						
						if(temp != ' ')
							infixLine = infixLine + temp + " ";
						
						if (Character.isDigit(temp)) {
							MyBinaryTreeNode tempNode = new MyBinaryTreeNode(temp);
							deque.insertBack(tempNode);
						
						}
						else
						{
							/**If the operand to be added is a lower precedence operand, this if statement
							* will push higher precedence operands to the deque 
							*/
							if((temp == '+' || temp == '-') && signStack.top() != null && (char) signStack.top() != '('){
								//Calls method to make appropriate Node
								MyBinaryTreeNode tempNode = newNode(signStack.pop(), deque.removeBack(), deque.removeBack());
								//Inserts node to back of deque
								deque.insertBack(tempNode);
								signStack.push(temp);
								
							}
							else 
							{
								if(temp !=')' && temp != ' ' ) {
									signStack.push(temp);								
							}
								
							else 
								{
									if(temp == ')' ) 
									{
										while((char) signStack.top() != '(' ) 
										{
											temp = (char) signStack.pop();
											MyBinaryTreeNode tempNode = newNode(temp, deque.removeBack(), deque.removeBack());
											deque.insertBack(tempNode);
										}
										signStack.pop();								
									}	
								}
							}
						}
						
					if (invalid == true) {
						textOut.write(blockWrite(infixLine));
						break;
						}
					temp2 = temp;	
					}
				
				
				//This while loop takes whatever operands are leftover from the original reading of the infix and finishes the process
				while(!signStack.isEmpty()) {
					MyBinaryTreeNode tempNode = newNode(signStack.pop(), deque.removeBack(), deque.removeBack());
					deque.insertBack(tempNode);
				}

				//Makes a new BinarySearchTree with finished tree
				MyExpressionTree tree = new MyExpressionTree();				
				tree.root = (MyBinaryTreeNode) (deque.removeBack());
				
				if(invalid == false)
					textOut.write(blockWrite(infixLine, tree.preorderTraversal(), tree.inorderTraversal(), tree.postorderTraversal(), tree.evaluate(tree.root)));

				line = textIn.readLine();
				textIn.close();
				textOut.close();
			}
		}
	
	//Catch Statements catch basic errors
	catch(FileNotFoundException fnfe)
	{
		System.err.println(" Input file " + inputFile + " not found.");
		System.exit(0);
	}
		//Catch block for IO exception
	
		catch(IOException ioe)
	{
		System.err.println("Input File Reading Error");
		System.exit(0);
	}
}


	

	/**
	 *  This method takes all data needed to make a BinaryTreeNode with two leaves and performs the code
	 *  necessary to turn the leaves into nodes themselves and makes the MyBinaryTree Node
	 *  
	 *  This mostly exists to clean up the code in the test() method to make it easier to read by taking repeated sections of code
	 *  and making a method for it, and removing unnecessary variables from the test method that make it harder to read
	 *  
	 * @param data	data to be made into a node
	 * @param l		data to be left node
	 * @param r		data to be right node
	 * 
	 * @return		Finished MyBinaryTreeNode
	 */

	public static MyBinaryTreeNode newNode(Object data, Object r, Object l) {
		MyBinaryTreeNode nodeL = (MyBinaryTreeNode) l;
		MyBinaryTreeNode nodeR = (MyBinaryTreeNode) r;
		MyBinaryTreeNode temp = new MyBinaryTreeNode(data, nodeL, nodeR);
		return temp;
	}
	
	/**
	 * 
	 * This string takes the original infix statement, the postfix statement, and the result and formats it into one 
	 * 5 line string to be written to the output file
	 * 
	 * @param infix		The infix expression as a string
	 * @param postfix	The postfix expression as a String
	 * @param r			The Result
	 * @return			One string which holds ann the inputs formatted properly
	 */
		public static String blockWrite(String infix, String preorder, String inorder, String postorder, int r) {
			return 		"Origianl Infix:\t\t" + infix + "\n" + "Preorder Traversal:\t" + preorder + "Inorder Traversal:\t" + 
						inorder + "Postorder Traversal:\t" + postorder + "Evaluation:\t" + r;
		
		
		}
		
	/**
	 * @Overload
	 * 
	 * This Overloaded method takes just the infix to format the data if there is an invalid expression
	 * @param infix		The found invalid infix expression
	 * @return			String containing Infix, and an Invalid Expression Statement
	 */
		public static String blockWrite(String infix) {
			return 		"Origianl Infix:\t\t" + infix +  "\n" +
						"**Invalid Expression**\n\n";
		}

	}



