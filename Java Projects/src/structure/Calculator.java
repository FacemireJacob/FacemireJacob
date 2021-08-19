package structure;
import java.util.Scanner;

public class Calculator {

	/**
	 * This method takes two integers form the user and adds them together
	 */
	public static void addition() {
		
		Scanner sc = new Scanner(System.in);
		int num1;
		int num2;
		
		System.out.println("Please enter two Integers to add");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + " + " + Integer.toString(num2) + " = " + num1 + num2); 
		sc.close();
	}
	
	/**
	 * This method takes two integers form the user and subtracts the 2nd from the first
	 */
	public static void subtraction() {
		
		Scanner sc = new Scanner(System.in);
		int num1;
		int num2;
		
		System.out.println("Please enter two Integers to subtract");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + " - " + Integer.toString(num2) + " = " + (num1 - num2)); 
		sc.close();
	}
	
	/**
	 * This method takes two integers form the user and multiplies them together
	 */
	public static void multiplication() {
		
		Scanner sc = new Scanner(System.in);
		int num1;
		int num2;
		
		System.out.println("Please enter two Integers to multiply");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + " * " + Integer.toString(num2) + " = " + (num1 * num2)); 
		sc.close();
	}
	
	/**
	 * This method takes two integers form the user and divides
	 */
	public static void division() {
		Scanner sc = new Scanner(System.in);
		int num1;
		int num2;
		
		System.out.println("Please enter two Integers to divide");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + " / " + Integer.toString(num2) + " = " + (num1 / num2)); 
		sc.close();
	}
	
	/**
	 * This method takes two integers form the user and performs the modulo function
	 */
	public static void modulo() {
		Scanner sc = new Scanner(System.in);
		int num1;
		int num2;
		
		System.out.println("Please enter two Integers to perform the modulo function");
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + " % " + Integer.toString(num2) + " = " + (num1 % num2));
		sc.close();
	}
	
	/**
	 * This method takes two integers form the user and finds the square
	 */
	public static void square() {
		Scanner sc = new Scanner(System.in);
		int num1;
		
		System.out.println("Please enter an integer to square");
		num1 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + Integer.toString(num1) + "^" + "2" + " = " + (num1*num1)); 
		sc.close();
	}
	/**
	 * This method takes two integers form the user and finds the square root
	 */
	public static void squareRoot() {
		Scanner sc = new Scanner(System.in);
		int num1;
		
		System.out.println("Please enter an integer to find the squareroot of");
		num1 = sc.nextInt();
		
		System.out.println("\nThe result:\n" + "sqrt(" + Integer.toString(num1) + ")" + " = " + (Math.sqrt(num1))); 
		sc.close();
	}
	
	/**
	 * This function uses a do/while loop and a switch case to repeat the calculator function until the user inputs a -1 to kill the program
	 */
	
	public static void run() {
		
		int num = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to my Calculator");
		
		
		do {
			System.out.println("\n+++++++++++++++++++++++++++++++\n");
			System.out.println("To use each function, please type the corresponding code (or -1 to exit):");
			System.out.println("Addition\t: 1\nSubtraction\t: 2\nMultiplication\t: 3\nDivision\t: 4\nModulo\t\t: 5\nSquare\t\t: 6\nSquare Root\t: 7\n");
			num = sc.nextInt();
			
			switch(num)
			{
			case 1: 
				addition();
				break;
			case 2:
				subtraction();
				break;
			case 3:
				multiplication();
				break;
			case 4:
				division();
				break;
			case 5:
				modulo();
				break;
			case 6:
				square();
				break;
			case 7:
				squareRoot();
				break;
			}
		} while (num != -1);
		
		sc.close();
		System.out.println("Thank you for using my calculator");
	}
}
