package structure;

/**
 * This class creates the MySet object, which contains an MyArrayList object and adds to its functionality
 * through the addition of many Set specific functions
 * 
 * 
 * 
 * @author Jacob Facemire
 * @version 1.0.1 @ 7/5/2021
 *  
 * 
 */



public class MySet implements Cloneable {
	
	//Instance Variables
	private MyArrayList setContainer;

	/**
	 * default constructor
	 */
	public MySet() {
		setContainer = new MyArrayList();
	}
	
	/**
	 * returns the number of elements in a set
	 */
	public int cardinality() {
		return setContainer.size();
	}
	
	/**
	 * Adds a new element to the set but excludes duplicates
	 * @param element	element to add
	 */
	public void add(Object element) {
		if(setContainer.contain(element))
			return;
		setContainer.append(element);
	}

	/**
	 * Checks to see if the set contains passed element
	 * @param elementAt element to check if the set contains it
	 * @return true if it contains the element, false Otherwise
	 */
	public boolean contains(Object elementAt) {
		return setContainer.contain(elementAt);	
	}


	/**
	 * Find the set equal to phrase "this set - B"
	 * @param B				The B set to be subtracted
	 * @return	result		The resulting set as a new set
	 */
	public MySet complement(MySet B) {
		MySet result = new MySet();
		
		
		result.setContainer = this.setContainer.clone();
		
		
		for(int i = 0; i < this.cardinality(); ++i)
		{
			if (B.contains(this.setContainer.elementAt(i)))
				result.setContainer.remove(this.setContainer.elementAt(i));
		}
		return result;
	}

	/**
	 *  Finds the intersection by iterating through the passed MySet B and checks if it is in *this* MySet
	 *  Appends a new Set if it is indeed in both sets
	 * @param B 				Set passed in
	 * @return intersection		the set that is the Intersection of *this* and B
	 */
	public MySet intersection(MySet B) {
		MySet intersection = new MySet();
		for (int i = 0; i < B.setContainer.size(); ++i)
		{
			if(this.contains(B.setContainer.elementAt(i)))
				intersection.setContainer.append(B.setContainer.elementAt(i));
		}
		return intersection;
	}
	
	/**
	 *  Runs a for loop through each element in *this* set, and returns True if only every element appears in the set B
	 * @param B 	Set passed in
	 * @return		True if *this* set is a subset of B, false otherwise
	 */
	public boolean subsetOf(MySet B) {
		for (int i = 0; i < this.setContainer.size(); ++i)
		{
			if(! B.contains(this.setContainer.elementAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 *  finds the Union of two sets, this and B
	 * @param B				Set passed into method
	 * @return union		Set resulting from union method
	 */
	public MySet union(MySet B) {
		MySet union = new MySet();
		
		union.setContainer = this.setContainer.clone();
		
		for (int i = 0; i < B.setContainer.size(); ++i)
		{
			union.add(B.setContainer.elementAt(i));
		}
		return union;
	}
	
	/**
	 * Returns the set that is the union of
	 * (this - B) U (B - this)
	 * Otherwise the set containing *ALL NUMBERS NOT IN THE INTERSECTION OF BOTH SETS*
	 * 
	 * We accomplish this by finding the sets that are each part of the equation separately and assigning them their own MySet object
	 * then pass both into the union function
	 * 
	 * making a third set, and passing both into complement and union set
	 * 
	 * @param B			Set passed in
	 * @return sym		The resulting set 
	 */
	
	public MySet symmetricDifference(MySet B) {
		MySet sym = new MySet();
		MySet complement1 = new MySet();
		MySet complement2 = new MySet();
			
		//This line sets complement1 to the resulting set of the function (this - B)
		complement1 = this.complement(B);

		//This line sets complement2 to the resulting set of the function (B - this)
		complement2 = B.complement(this);
		
		//This line finds the union of complement1 and complement2 and sets sym to that union
		sym = complement1.union(complement2);
			
		return sym;
	}
	
	/**
	 * @Override
	 *
	 *	Overrides toString function to give a complete list of all elements in the set
	 *
	 */
	public String toString(){
		String str = "{\n";
		
		for (int i = 0; i < this.setContainer.size(); ++i){
			str += this.setContainer.elementAt(i) + "," + "\t";
			if((i+1)%5 == 0)
				str += "\n";
		}
		str += "}" ;
		return str;
	}
}

