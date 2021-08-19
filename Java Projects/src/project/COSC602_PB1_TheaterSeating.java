package project;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

import structure.MyQueue;

/**
 * 
 * This class attempts to fill a Theater with requests for seats ensuring each group of attendees will be seating together. It generates a random party size and uses an
 * algorithm to find the "best seats" for each incoming party
 * 
 * 
 * @author Jacob Facemire, 8/14/2021
 * @version 1.0.0 
 * 
 * To Do:
 * 
 * Generate 16 x 11 Theater				done
 * Create Print statement for it		done
 * Generate random parties size 1-5		done
 * 
 * Write algorithm for determining what are the best seats
 * 
 * 	Step 1) Find the Midpoint or MidGroup of the theater			 Done
 * 	Step 2) Calculate the distance from the Middle for each seat	 Done
 * 	Step 3) Order all seats based on that distance
 *  Step 4) Find the closest possible seat
 *  Step 5) Determine Termination conditions
 * 
 * Define Termination condition
 * 	- Theater Full
 * 			if empty == 0 kill
 * 
 * Print Final statement				done	
 */
public class COSC602_PB1_TheaterSeating {
	
	//These Final ints set the size of the theater
	final static double WIDTH = 11;
	final static double HEIGHT = 16;
	
	public static void test() {
		

		
		/*Creates a 2d array based on theater dimensions, String for presentation purposes
		*int for computational purposes
		*/
		String[][] theater = new String[(int) HEIGHT][(int) WIDTH];
		Point theaterPoints[][] = new Point[(int) HEIGHT][(int) WIDTH];
		
		
		//Fill each seat with the placeholder "..."
		for (int i = 0; i < HEIGHT; i++) 
			for(int j = 0; j < WIDTH; j++) {
				theater[i][j] = "...";
				theaterPoints[i][j] = new Point(i + 1,j + 1);
			}

		//Because we are not counting "0" in our count, we find the midpoint by adding 0.5
		//to 
		Point2D.Double mid = new Point2D.Double(HEIGHT/2 + 0.5 , WIDTH/2 + 0.5);
		System.out.println(mid);
		
		System.out.println("Distance is: " + mid.distance(theaterPoints[0][0]));
		
		//We use this Object to assign each point a distance value to sort them by distance
		Object[][] distanceArray = new Object[(int) (HEIGHT*WIDTH)][2];
		//We Use this Queue as a helper to transfer all the points from the Point[][] Array to the distanceArray
		MyQueue pointQueue = new MyQueue();
		
		//Filling Queue with the Points
		for (int i = 0; i < HEIGHT; i++)
			for (int j = 0; j < WIDTH; j++) {
				pointQueue.insertBack(theaterPoints[i][j]);
			}
		
		/*Takes every point from the Queue, finds its distance from the closest midpoint, and adds it to the distanceArray
		*MyQueue doesn't know its size so I use a combination of an iterating variable and a while loop
		*/
		int i = 0;
		while(!pointQueue.isEmpty())
		{
			Point tempPoint = (Point) pointQueue.removeFront();
		
			distanceArray[i][0] = tempPoint;
			distanceArray[i][1] = mid.distance(tempPoint);
			i++;
		}
				
		//Helper Variables for Sorting Algorithm
		Point tempPoint = new Point();
		double tempDist;
		
		//Basic Bubble Sort Algorithm
		for(int a = 0; a < (int) HEIGHT*WIDTH -1; a++)
			for(int b = 1; b < HEIGHT*WIDTH - 1; b++) 
			{
				if(Double.compare((double) distanceArray[b-1][1], (double) distanceArray[b][1]) > 0 )
				{
					tempPoint = (Point) distanceArray[b][0];
					tempDist = (double) distanceArray[b][1];
					
					distanceArray[b][0] = distanceArray[b-1][0];
					distanceArray[b][1] = distanceArray[b-1][1];
					
					distanceArray[b-1][0] = tempPoint;
					distanceArray[b-1][1] = tempDist;
				}
			}
		
		/*
		 * Next, I write a while loop that keeps filling seats until either the theater is empty or we fail to find a possible seat 5 times in a row
		 */
		
		//This int keeps track of how many parties have been generated
		int partyCount = 0;
		//This int keeps track of how many requests have been fulfilled
		int partyFilled = 0;
		//Setting up Random number generation
		Random rand = new Random(System.currentTimeMillis());
		//This int keeps track of empty seats
		int empty = (int) (HEIGHT*WIDTH);
		int fail = 0;
		
		while(empty > 0) {

			partyCount++;
			
			for(int a = 0; a < HEIGHT*WIDTH; a++) {
				int p = randGen(rand.nextInt());
				System.out.println("Party Size: " + p);
				System.out.println("Empty seats left: " + empty);
				switch(p) { 
				case 1:
				{		
					if(distanceArray[a][1] != null) {
						fail = 0;
						distanceArray[a][1] = null;
						Point px = new Point((Point) distanceArray[a][0]);
						int x = px.x;
						int y = px.y;
						
					
						theater[x-1][y-1] = toPartyString(partyCount);
						printTheater(theater);
						empty--;
						partyCount++;
						break;
					}
					fail++;
					if (fail==5)
						report(partyCount, partyFilled, empty, "5 Parties failed to be sat in a row");
					
				}
				case 2:
				{
					if(distanceArray[a][1] != null) 
					{
						Point px = new Point((Point) distanceArray[a][0]);
						int x = px.x;
						int y = px.y;	

						if(y < WIDTH)
						{
							if(theater[x-1][y].equals("...") && theater[x-1][y-1].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-2;
								partyCount++;
								break;
							}

						}
						
						if(y > 1)
						{
							if(theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-2;
								partyCount++;
								break;
							}
						}
						
						fail++;
						if (fail==5)
							report(partyCount, partyFilled, empty, "5 Parties failed to be sat in a row");}
						
				}
				case 3:
				{
					if(distanceArray[a][1] != null) 
					{
						Point px = new Point((Point) distanceArray[a][0]);
						int x = px.x;
						int y = px.y;	

						if(y < WIDTH - 1 && y > 2)
						{
							
							if(theater[x-1][y].equals("...") && theater[x-1][y-1].equals("...")&& theater[x-1][y+1].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-3;
								partyCount++;
								break;
							}
						
							if(theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...") && theater[x-1][y-3].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;

								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-3;
								partyCount++;
								break;
							}
							if(theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...") && theater[x-1][y-2].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-3;
								partyCount++;
								break;
							
							}else
								fail++;
						}				
						if (fail==5)
							report(partyCount, partyFilled, empty, "5 Parties failed to be sat in a row");}	
				}
				
				
				case 4:
				{
					if(distanceArray[a][1] != null) 
					{
						Point px = new Point((Point) distanceArray[a][0]);
						int x = px.x;
						int y = px.y;	

						if(y < WIDTH - 2 && y > 3)
						{
							
							if(theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...") && theater[x-1][y+1].equals("...")&& theater[x-1][y+2].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								theater[x-1][y+2] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-4;
								partyCount++;
								break;
							}
						
							if(theater[x-1][y-4].equals("...") && theater[x-1][y-3].equals("...") && theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;

								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								theater[x-1][y-4] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-4;
								partyCount++;
								break;
							}
							if(theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...")&& theater[x-1][y+1].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								theater[x-1][y] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-4;
								partyCount++;
								break;		
							}	
							if(theater[x-1][y-3].equals("...") && theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...")&& theater[x-1][y].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								theater[x-1][y-2] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-4;
								partyCount++;
								break;
							}else
								fail++;
						}				

						if (fail==5)
							report(partyCount, partyFilled, empty, "5 Parties failed to be sat in a row");
						}	
				}
				case 5:
				{
					if(distanceArray[a][1] != null) 
					{
						Point px = new Point((Point) distanceArray[a][0]);
						int x = px.x;
						int y = px.y;	

						if(y < WIDTH - 3 && y > 4)
						{
							
							if(theater[x-1][y-3].equals("...") && theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...")&& theater[x-1][y].equals("...") && theater[x-1][y+1].equals("..."))		
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-3] = toPartyString(partyCount);							
								theater[x-1][y-2] = toPartyString(partyCount);
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-5;
								partyCount++;
								break;
							}
						
							if(theater[x-1][y-4].equals("...") && theater[x-1][y-3].equals("...") && theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...") && theater[x-1][y].equals("..."))	
							{
								fail = 0;
								distanceArray[a][1] = null;

								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								theater[x-1][y-4] = toPartyString(partyCount);
								theater[x-1][y] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-5;
								partyCount++;
								break;
							}
							if(theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...")&& theater[x-1][y+1].equals("...") && theater[x-1][y+2].equals("..."))	
							{
								fail = 0;
								System.out.println("bad");
								distanceArray[a][1] = null;
								theater[x-1][y-2] = toPartyString(partyCount);							
								theater[x-1][y-1] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								
								printTheater(theater);
								empty = empty-5;
								partyCount++;
								break;		
							}	

							
							if(theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...") && theater[x-1][y+1].equals("...")&& theater[x-1][y+2].equals("...")&& theater[x-1][y+3].equals("..."))	
							{
								fail = 0;
								System.out.println("bad");
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y] = toPartyString(partyCount);
								theater[x-1][y+1] = toPartyString(partyCount);
								theater[x-1][y+2] = toPartyString(partyCount);
								theater[x-1][y+3] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-5;
								partyCount++;
								break;
							}
							
							if(theater[x-1][y-4].equals("...") && theater[x-1][y-3].equals("...") && theater[x-1][y-2].equals("...") && theater[x-1][y-1].equals("...") && theater[x-1][y].equals("...") && theater[x-1][y-5].equals("..."))		
							{
								fail = 0;
								distanceArray[a][1] = null;
								theater[x-1][y-1] = toPartyString(partyCount);							
								theater[x-1][y-2] = toPartyString(partyCount);
								theater[x-1][y-3] = toPartyString(partyCount);
								theater[x-1][y-4] = toPartyString(partyCount);
								theater[x-1][y-5] = toPartyString(partyCount);
								printTheater(theater);
								empty = empty-5;
								partyCount++;
								break;
							}else
								fail++;
						
							}				
						
						if (fail==9)
							report(partyCount, partyFilled, empty, "5 Parties failed to be sat in a row");}	
				}
				
				
				
				
			}}}}
	
	
		
	
	
	public static String toPartyString(int partyCount) {

		if (partyCount < 10)
			return "00" + Integer.toString(partyCount);
		if(partyCount < 100)
			return "0" + Integer.toString(partyCount);
		
		return Integer.toString(partyCount);
		
	}

/**
 * This theater takes the 2d array representation of the theater seating and prints it in a user friendly format
 * 
 * 
 * @param theater	2d array to print out
 */
	public static void printTheater(String[][] theater) {
		for(int i = 0; i < WIDTH ; i++)
			System.out.print("\t" + "  " + (i+1));
	
		for(int i = 0; i < HEIGHT; i++) {
			System.out.print("\n" + (i+1));
			for(int j = 0; j < WIDTH; j++)
				System.out.print("\t" + theater[i][j]);
		}
		
		System.out.println("\n\n");
	}
	
/**
 * 	This method generates random digits used to generate party size and performs various 
 *  operations to ensure it is within the 1-5 range
 * 
 * @param r 	randomly generated 10digit integer inputted by Random.nextInt()
 * @return r	Modified integer within range 1-5 inclusive
 */
	public static int randGen(int r) {
		//Convert to 1 digit int
		r = r % 10;
		//Ensure Positive int
		if(r<0)
			r = r * -1;
		//Ensures r is <=5
		if(r > 5)
			r = r - 5;
		//Ensures r isnt 0
		if (r == 0)
			r++;
		return r;
	}
	
/**
 * 	This method takes all information bout the theater and formats into a user friendly output
 * 	
 * @param partyCount	Number of Parties generated thus far
 * @param partyFilled	Number of Parties successfully sat
 * @param empty			Number of Empty seats
 * @param condition		Why did we terminate
 */
	public static void report (int partyCount, int partyFilled, int empty, String condition) {

		System.out.println("Number of seats in theater: \t" + (WIDTH*HEIGHT));
		System.out.println("Number of seats filled: \t" + ((WIDTH*HEIGHT) - empty));
		System.out.println("Number of seats unfilled: \t" + empty);
		System.out.println("Number of requests: \t\t" + partyCount);
		System.out.println("Number of requests satisfied: \t" + partyFilled);
		System.out.println("Number of requests denied: \t" + (partyCount-partyFilled));
		System.out.println("Termination condition: \t\t" + condition);
		System.exit(0);
	}
}
	


