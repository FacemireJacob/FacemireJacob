package project;
import structure.*;

/**
 * This is the testing class for the Set project in Module 1
 * 
 * The tasks completed in this code are, in *rough* order (Copied from the Handout assignment):
 * 
 * i.create two instances of MySet: primeNumSet and fibonacciNumSet
 * ii.add the first 30 Fibonacci numbers into the fibonacciNumSet 
 * iii.print out the fibonacciNumSet
 * iv.add the first 30 prime numbers into the primeNumSet  
 * v.print out the primeNumSet vi.get the intersection set of the above two sets  
 * vii.print out the intersection set  
 * viii.get the symmetric difference set of the above two sets  
 * ix.print out the symmetric difference set  
 * x.get the union set of the above two sets 
 * xi.print out the union set  
 * 
 * 
 * 
 * @author Jacob Facemire
 * @version 1.0.1 @ 7/5/2021
 *  
 * 
 */
public class COSC602_P1_Set {

	public static void test() {
		
		//Sets size of test Sets
		final int SIZE = 30;
		
		
		System.out.println("Begin COSC602 Project Set testing\n");
		
		//Initialize two int arrays to be used to make Sets
		int[] p = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113};
		int[] f = new int[30];
		
		//First two elements of fibonacci sequence, needed to generate all further numbers
		f[0] = 0;
		f[1] = 1;
		
		//For loop generates all needed fibonacci numbers
		for (int i = 2; i <SIZE; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		
		//Prints each element in array f for posterity
		System.out.println("Testing Fibonacci generator: \n");
		
		/**
		 * Adds each element to the Set. 
		 * 
		 * NOTE: A set in mathmatics can not have duplicates (Which the Fibonacci sequence has 1)
		 * As such the Set of Fibonacci will only have 29 elements as 1 element isn't added
		 */
		
		for (int i = 0; i < SIZE; i++) 
		{
			System.out.print(f[i] + ", ");
		}
		
		System.out.println("\n");
		
		//Initilizes both MySet objects and fills them with a for loop
		MySet primeNumSet = new MySet();
		MySet fibonacciNumSet = new MySet();
		
		for(int i = 0; i < 30; i++) {
			primeNumSet.add(p[i]);
			fibonacciNumSet.add(f[i]);
		}
		
		//Prints Fibonacci MySet using toString method 
		System.out.println("Adding generated Fibonacci sequence to Fibonacci set: \n" + fibonacciNumSet.toString());
		
		//Prints Prime MySet using toString method 
		System.out.println("\nHere is the prime number set: \n" + primeNumSet.toString());
		
		//Prints intersection of both sets 
		System.out.println("\nHere is the intersection of both sets: \n" + fibonacciNumSet.intersection(primeNumSet).toString());
		
		//Prints the Symmetric Difference of both sets
		System.out.println("\nHere is the Symmetric Difference: \n" + fibonacciNumSet.symmetricDifference(primeNumSet).toString());
		
		//Prints the Union of Both Sets
		System.out.println("\nHere is the Union set: \n" + fibonacciNumSet.union(primeNumSet).toString());
		
		
		
		
		
		
		
		
	}

}
