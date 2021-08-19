package project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import structure.MyArrayList;


/**
 * 
 * This is the test method for the Phone Number to Word project. It inputs a user defined phone number, verify it meets specifications,
 * then searches an imported list of corresponding words and finds it within 20 seconds
 * 
 * @author Jacob Facemire
 * 
 */
public class COSC602_P2_PhoneNumberToWord {
	
	/**
	 * 
	 * This is the method that initializes needed variables and begins the loop which runs the whole program
	 * 
	 * For the methodology I used to solve the problem I first initialize necessary variables which include
	 * 
	 * MyArrayList dict : 		List of all English 7 letter words imported from provided text file
	 * MyArrayList keyList :	List of all English words converted to numerical form for cross referencing
	 * 
	 */
	public static void test(){

		MyArrayList dict, keyList = new MyArrayList();
		dict = getDictionary();
		keyList = convertWord(dict);		
		
		//This repeats the phoneToWord method until the user inputs "-1" which exits the program via System.exit(0)
		while(true)
			phoneToWord(dict, keyList);
		}
	
	/**
	 * This function takes all the vars we prepared in the test() function and performs all tasks necessary to convert user inputted
	 * phone numbers into words. 
	 * 
	 * 
	 * @param dict		Dictionary of 7 letter words in order made using getDictionary()
	 * @param keyList	List of all corresponding phone numbers to words in dict made using convertWord(MyArrayList dict)
	 * @return
	 */
	public static void phoneToWord(MyArrayList dict, MyArrayList keyList) {
			
			Scanner sc = new Scanner(System.in);
			int num = 0;
			System.out.println("\nPlease enter a 7 digit phone number (Do NOT include '-' characters, the Numbers '1', or '0'):\nOr enter -1 to end the program\n");
			
			
			while(!(num > 999999 && num <10000000))
			{
			num = getNum(sc);
			}	
			
			System.out.print("Thank You, looking up the number now.\n\n");
			
			MyArrayList finalWords = getList(Integer.toString(num), dict, keyList);
			
			if(finalWords.size() == 0) {
				System.out.println("Were sorry, no words could be found that match your phone number. Try Again");
			}
			else {
				System.out.println("We found " + finalWords.size() + " word(s) that match your phone number, they are as follows:");
				for(int i = 0; i < finalWords.size(); i++) {
					System.out.println((i+1) + ": " + finalWords.elementAt(i));
					}
				
				}
		}
		
	/**
	 * This method attempts to take a phone number from the user, and verify that it is a valid number
	 * 
	 * @param sc	Scanner Object to get user input
	 * @return		Possible phone number, or -1 if it fails to pass the check
	 */
	
	public static int getNum(Scanner sc) {
		String num = "";
		
		num = sc.nextLine();
		int intNum;
		
		while(true)
		{
			
			try	{
				intNum = Integer.parseInt(num);
				//This is the programs "kill switch" for when the user is finished
				if(intNum == -1) {
					System.exit(0);
				}
				if(intNum > 999999 && intNum < 10000000 && onesAndZeros(num)) {
					return(intNum);
				}
				System.out.print("Sorry, that is not a valid number, try again:\n");
				return -1;
			}
		
			catch (NumberFormatException A) {
				System.out.print("Sorry, that is not a valid number, try again\n");
				return -1;
			}
		}
	}

	/**
	 * This method checks if the passed string contains either the '0' char or '1' char
	 * 
	 * @param phone		String passed in
	 * @return			true if the string DOES NOT contain either '0' or '1', false otherwise
	 */
	public static boolean onesAndZeros(String phone) {
		if(phone.contains("0")) return false;
		if(phone.contains("1")) return false;
		
		return true;
	}
	
	/**
	 * This method generates a list of all 7 letter English words based on the text file of all English words provided
	 * @return	dict, MyArrayList object with all 7 letter English words as elements
	 * @throws Exception possible errors if text file not found
	 */
	public static MyArrayList getDictionary(){
		
		MyArrayList temp = new MyArrayList();
		
		String inputFile = "../COSC602_P2_EnglishWordList.txt";
		
		BufferedReader textIn;
		
		try {
			textIn = new BufferedReader( new FileReader(inputFile));
			String line = textIn.readLine();
			
			while(line!= null) {
				if(line.length() == 7) {
					temp.append(line);
				}
				line = textIn.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("error, dictionary not found");
		} catch (IOException e) {
			System.err.println("Input File Reading Error");
		}
		return temp;

	}
	
	/**This method takes a MyArrayList of words, converts them to numerical form, then makes a new MyArrayList
	 * of the converted words with the same index as the word originally has
	 * For Example
	 * input : "windows" -> 9463697
	 * 
	 * @param dict				dictionary to make converted copy of
	 * @return	key				Number form of words in own MyArrayList
	 */
	private static MyArrayList convertWord(MyArrayList dict) {
		String temp = "";
		String line = "";
		MyArrayList key = new MyArrayList();
		
		for(int j = 0; j < dict.size(); j++) {
			line = (String) dict.elementAt(j);
			for (int i = 0; i < 7; i++) {
				if(line.charAt(i) == 'a' || line.charAt(i) == 'b' || line.charAt(i) == 'c' ) {
					temp += '2';
					continue; }
				if(line.charAt(i) == 'd' || line.charAt(i) == 'e' || line.charAt(i) == 'f' ) {
					temp += '3';
					continue; }
				if(line.charAt(i) == 'g' || line.charAt(i) == 'h' || line.charAt(i) == 'i' ) {
					temp += '4';
					continue; }
				if(line.charAt(i) == 'j' || line.charAt(i) == 'k' || line.charAt(i) == 'l' ) {
					temp += '5';
					continue; }
				if(line.charAt(i) == 'm' || line.charAt(i) == 'n' || line.charAt(i) == 'o' ) {
					temp += '6';
					continue; }
				if(line.charAt(i) == 'p' || line.charAt(i) == 'q' || line.charAt(i) == 'r'  || line.charAt(i) == 's'  ) {
					temp += '7';
					continue; }
				if(line.charAt(i) == 't' || line.charAt(i) == 'u' || line.charAt(i) == 'v' ) {
					temp += '8';
					continue; }
				if(line.charAt(i) == 'w' || line.charAt(i) == 'x' || line.charAt(i) == 'y'  || line.charAt(i) == 'z' ) {
					temp += '9';
					continue; }
			}
			key.append(temp);
			temp = "";
		}

		return key;
	}

	/**
	 * This method generates a MyArrayList object with all words in the language which the passed phone number refers to
	 * it.
	 * 
	 * It accomplishes this by cloning the dict and keyList MyArrayLists so we can search and modify them while keeping the integrity of both those
	 * MyArrayLists in tact. It finds the index of a corresponding word using indexOf, appends the MyArrayList list with it, then removes that
	 * entry from both the dictTemp and listTemp MyArrayLists. Because corresponding strings share an index and they are both removed at the smae time, this
	 * keeps the both MyArrayLists usable as the new indexes should both match as well
	 * 
	 * @param phone		String version the phone number used to iteratively generate all possible words
	 * @param dict		MyArrayList that serves as a dictionary of possible words
	 * @param keyList	MyArrayList that holds the numerical equivalent (as a string) of each word in dict which shares its index
	 * @return	list	MyArrayList of all possible words
	 */
	public static MyArrayList getList(String phone, MyArrayList dict, MyArrayList keyList) {
		MyArrayList list = new MyArrayList();
		
		//Clones input MyArrayLists to not modify those lists so they can be reused
		MyArrayList dictTemp = dict.clone();
		MyArrayList listTemp = keyList.clone();
		int temp;
		while ( (temp = listTemp.indexOf(phone)) >= 0) {
			list.append(dictTemp.elementAt(temp));
			dictTemp.removeAt(temp);
			listTemp.removeAt(temp);
		}
		return list;
	}
	
}