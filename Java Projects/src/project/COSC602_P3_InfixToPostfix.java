package project;
import java.io.*;
import structure.*;

/**
 * 
 * This class imports Infix statements from a provided text file and organizes them into postfix order as well
 * as properly evaluating the mathematical expression.  It takes the original infix, the corresponding postfix and the
 * result and writes it in a formatted way to an output file. If there is an error that makes an inputted
 * expression invalid, the output file will reflect this, and the code will output in the java console
 * what error it experienced, as well as the particular expression that caused it
 * 
 * 
 * @author Jacob Facemire, 7/27/2021
 * @version 1.0.0 
 * 
 */

public class COSC602_P3_InfixToPostfix {
	
	//Relative path locations for input and output files
	private static String inputFile = "../COSC602_P3_InfixInput.txt";
	private static String outputFile = "../COSC602_P4_InfixOutput.txt";


	public static void test() {
		
		MyQueue postfix = new MyQueue();
		MyStack signStack = new MyStack();
		
		String postfixClone;
		
		//Setting up reader and writer objects
		BufferedReader textIn;
		boolean invalid = false;
		
		String infixLine;

		int result;

		
		
		
		try
		{
			//Buffered reader which reads text
			textIn = new BufferedReader( new FileReader(inputFile));
			String line = textIn.readLine();
			FileWriter textOut = new FileWriter(outputFile);

			//While loop parses input file line by line, and finds all needed chars to add to either the signstack or postfix Queue
			while(line != null) {
				
				//String used to build formatted infix for the output file
				infixLine = "";
				//Boolean which is made true if the code flags an expression as invalid. 
				invalid = false;

				//String used to make a formatted version of the finalized postfix statement
				postfixClone = "";
				int counter = 0;
				
				for(int i = 0; i < line.length(); i++) {
					char temp = line.charAt(i);
					if(temp != ' ') {
						infixLine = infixLine + temp + " ";
						//Checks if the current character is a valid integer, if true, adds it to the Queue
						if(Character.isDigit(temp)){
							//Checks to see if it is a single digit integer, flags the invalid boolean if so
							if(i > 0 && (isInt(Character.toString(line.charAt(i-1))))) {
								invalid = true;
								System.out.println("Non-single digit on line:\n" + line);
								}
							postfix.insertBack(temp);counter++;
							
						}
						//Pushes operand to stack assuming it isn't a close parenthesis
						else {
								/**If the operand to be added is a lower precedence operand, this if statement
								* will push higher precedence operands to to postfix queue
								*/
								if((temp == '+' || temp == '-') && signStack.top() != null && (char) signStack.top() != '(')
									{postfix.insertBack(signStack.pop());
									signStack.push(temp);
									counter++;}
								else {
								if(temp !=')' && temp != ' ' ) {
									signStack.push(temp);
									if(temp != '(')
										counter++;
								}
						

								
						else {
								if(temp == ')' ) {
									while((char) signStack.top() != '(' ) {
										temp = (char) signStack.pop();
										postfix.insertBack(temp);
										}
									
									signStack.pop();								
								}
							}
						}
					}
				}}
				
				//Catches if the expression has a non-single digit integer and breaks the loop, throwing an invalid expression
				if (invalid == true) {
					textOut.write(blockWrite(infixLine));
					break;
				}
				
				//pops all signs from postfix stack to the end of the queue	
				while(!signStack.isEmpty()) {
					postfix.insertBack(signStack.pop());
				}

				
				//Creates the Postfix String for the output file by removing each element from the queue then copying it back
				for(int i = 0; i < counter; i++)
					postfixClone = postfixClone + postfix.removeFront() + " ";

				for(int i = 0; i < postfixClone.length(); i++) {
					postfix.insertBack((postfixClone.charAt(i)));
					i++;
				}

				
			//Moves numbers from the postfix queue to the empty stack, finds operands and evaluates them
				while(!postfix.isEmpty()) {

					//integers used to make sure non additive operations are performed in the correct order
					
					int t1 = 0;
					int t2 = 0;
					try {
						if(isInt(postfix.front().toString())) {
							signStack.push(Character.getNumericValue((char) postfix.removeFront()));
							
						}
						else
							if((char) postfix.front() == '*') {
								signStack.push( (int) signStack.pop() * (int) signStack.pop() ) ;
								postfix.removeFront();

							}
						else							
							if((char) postfix.front() == '+') {
								signStack.push( (int) signStack.pop() + (int) signStack.pop() ) ;
								postfix.removeFront();
							} 
						else
							if((char) postfix.front() == '-') {
								t1 = (int) signStack.pop();
								t2 = (int) signStack.pop();
								
								
								signStack.push(t2 - t1) ;
								postfix.removeFront();
							}
						else
							if((char) postfix.front() == '/') {
								t1 = (int) signStack.pop();
								t2 = (int) signStack.pop();
								
								
								signStack.push(t2 / t1) ;
								postfix.removeFront();
							}
						else
							if((char) postfix.front() == '%') {
								t1 = (int) signStack.pop();
								t2 = (int) signStack.pop();
								
								
								signStack.push(t2 % t1) ;
								postfix.removeFront();
							}
						else
							/*	Catches the Parentheses mismatch error and flags the invalid boolean
							 *	to ensure the later if statement doesn't write to the output file again
							 */
							if((char) postfix.front() == '(') {
								textOut.write(blockWrite(infixLine));
								invalid = true;
								System.out.println("Parentheses mismatch on line:\n" + line);
								break;
							}

					/*Catch Statement catches null pointer exceptions, and calls the Overloaded blockWrite function
					*to write all relevant data to the Output file	
					*/
					} catch (NullPointerException npe) {
						textOut.write(blockWrite(infixLine));
						System.out.println("Null pointer Exception on line:\n" + line);
						break;
					}
				}
				
				//If the invalid bool is not flagged and the stack is not empty to math is complete
				if (!invalid && signStack.top() != null) {
					result = (int) signStack.pop();
					textOut.write(blockWrite(infixLine, postfixClone, result));
				}

				//Clears both the queue and stack to prepare for next iteration of the loop
				signStack.clear();
				postfix.clear();
				
				//Read the next line to start the next iteration
				line = textIn.readLine();

				}
			//Close text in and out
			textIn.close();
			textOut.close();
		}	
			
		//Catch block for File not found
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
 * This method takes an inputed String and determines if it is a valid integer
 * 
 * @param input		String to be evaluated
 * @return			True if it is a valid Integer, false otherwise
 */
	public static boolean isInt(String input) {
		 try { 
			 Integer.parseInt(input);
		     return true;
		     } 
		 catch(NumberFormatException e) { 
		     return false; 
		 	 }
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
	public static String blockWrite(String infix, String postfix, int r) {
		return 		"Origianl Infix:\t\t" + infix +  "\n" +
					"Corresponding Postfix:\t" + postfix + "\n" + 
					"Evaluated Result:\t" + r + "\n\n";
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

