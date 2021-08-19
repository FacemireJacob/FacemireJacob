package structure;

public class MyArrayList implements Cloneable {

/**
 * Contains a collection of random access elements (Can access any element with constant time)
 * 
 * @author Jacob Facemire
 * 
 */
	private static final int INITIAL_CAPACITY = 30;
	private Object [] data;
	private int size; //keeps track of elements in it
	

	/**
	 * Default Constructor
	 */
	public MyArrayList() {
		data = new Object[INITIAL_CAPACITY]; //makes a new array of 100 null elements
		size = 0;							 //stores size of array
	}

	/**
	 * Appends the element at the end of the ArrayList.
	 * @param element The element to append
	 */
	
	public void append(Object element) {
		if(size == data.length) expand(); //doubles array size if it is full
		data[size++] = element; //uses size then iterates
	}

	/*
	 * Doubles current capacity
	 */
	
	private void expand() {
		Object [] temp = new Object[data.length*2];
		
		for(int i= 0; i < size; i++) {
			temp[i] = data[i];			
		}
		
		data = temp;
	}
	
	/**
	 * Makes the Array list Empty
	 */
	public void clear() {
		for(int i=0; i<size;i++)
			data[i] = null;
		size=0;
	}
	
	/**
	 * Check whether the ArrayList contains the element.
	 * @param element The element to be checked upon.
	 * @return true if the element is in the ArrayList.
	 */
	
	public boolean contain(Object element) {
		for(int i = 0; i < size; ++i)
			if(element.equals(data[i]))
				return true;
		return false;
	}
	
	/**
	 * Access the element at the given index.
	 * @param index the index of the element to access
	 * @return 		The Object at the index
	 */
	public Object elementAt(int index) {
		if(index < 0 || index >= size)
			return null;
		return data[index];
	}
	
	/**
	 * Find the index of the element
	 */
	
	public int indexOf(Object element) {
		for(int i = 0; i<size; ++i)
			if (element.equals(data[i]))
				return i;
		return -1;
	}
	
	/**
	 * Insert the element at the given index.
	 * @param index The index of the Location to be inserted
	 * @param element The element to be inserted
	 * @return	true if succeeds, false otherwise
	 */
	public boolean insertAt(int index, Object element) {
		if (index<0 || index >= size) return false; //returns false if index out of range
		
		if (size == data.length) expand(); //expands if index is at capacity
		
		for(int i = size; i > index; --i) //iterates backwards
			data[i] = data[i - 1];
		data[index] = element;
		++size;
		return true;
	}
	/**
	 * Check whether the Array is empty
	 * @return true if it is empty, false otherwise
	 */	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 
	 * @param index The index of element to remove
	 * @return	The element being removed
	 */
	public Object removeAt(int index) {
		if (index < 0 || index >= size)
			return null;
		
		Object temp = data[index];
		while (index <size - 1) {			//sets the to be removed element to the next one and iterates th eprocess through the MyarrayList
			data[index] = data[index + 1];
			index++;
		}
		data[--size] = null;
		return temp;
	}
	
	/**
	 * Remove the element from the ArrayList
	 * @param element The element to be removed.
	 * @return true if succeeds, false otherwise
	 */
	public boolean remove(Object element) {
		return removeAt(indexOf(element)) != null; //This is a statement that returns true or false
	}
	
	/**
	 * replace the current element at the given index with another element
	 * @param index		The index of the element to replace
	 * @param element	The element used to replace the current element at the index
	 * @return			True if succeeds, False otherwise.
	 */
	public boolean replace(int index, Object element) {
		if (index < 0 || index >= size)
			return false;
		data[index] = element;
		return true;
	}
	
	/**
	 * get the number of elements in the current ArrayList
	 * @return		size of the current ArrayList
	 */
	public int size() {return size;}
	
	/**
	 * makes sure the ArrayList gets at least the given capacity
	 * @param minCapacity
	 */
	public void ensureCapacity(int minCapacity) {
		if(minCapacity <= data.length)
			return;
		Object [] newData = new Object[minCapacity];
		for(int i = 0; i < size; ++i) {
			newData[i] = data[i];
		}
		data = newData;
	}
	/**
	 * Clone a copy of the current ArrayList.
	 * @return The cloned copy of the current ArrayList
	 */
	public MyArrayList clone() {
		MyArrayList cal = new MyArrayList();
		cal.ensureCapacity(this.size);
		for (int i=0; i < size; ++i) {
			cal.data[i] = this.data[i];
		}
		cal.size = this.size;
		return cal;
	}
	
	/**
	 * Merge the elements from the pal into this MyArrayList.
	 * @param	pal		The MyArrayList to copy elements from.
	 */
	public void merge(MyArrayList pal){
		for (int i = 0; i < pal.size(); ++i){
			this.append(pal.elementAt(i));
		}
	}
	
	/**
	 * Removes a range of elements from this ArrayList.
	 * @param	fromIndex	The starting index, inclusive.
	 * @param	toIndex		The ending index, exclusive.
	 */
	public void removeRange(int from, int to) {
		if (from >= to)
			return;
		if (from < 0)
			from = 0;
		if (to >= size)
			to = size;
		
		int num = to - from;
		for (int i = from; i < size - num; ++i){
			data[i] = data[i+num];
		}
		for (int j = size - num; j < size; ++j) {
			data[j] = null;
		}
		size = size - num;
	}
	
	/**
	 * Reverse the order of the elements in the ArrayList.
	 */
	public void reverse(){
		Object temp;
		for (int i = 0; i < size/2; ++i){
			temp = data[i];
			data[i] = data[size - i - 1];
			data[size - i - 1] = temp;
		}
	}
	
	/**
	 * Get a String representation of the current ArrayList.
	 * @return			The String representation.
	 */
	public String toString(){
		String str = "++++++++++++++++++++++++++++++++++++++++++\n" +
			"The current ArrayList contains the following: \n";
		str += "size = " + size + "\n";
		str += "capacity = " + data.length + "\n";
		for (int i = 0; i < size; ++i){
			str += i + ": " + data[i] + "\t";
			if((i+1)%5 == 0)
				str += "\n";
		}
		str += "\n+++++++++++++++++++++++++++++++++++++++++++++\n";
		return str;
	}
}
