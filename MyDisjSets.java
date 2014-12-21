//Brett Yamada, CSE 373 Winter 2014, Homework #4 Part I
//This class implements a disjoint set ADT as an up-tree. The up-tree is represented
//as an array and assumes that all elements are unique, ranging from 0 to numElements - 1.
//This constraint is given by the user upon constructing the disjoint set ADT. Set names
//will also be in this range and are defined as the roots of the up-trees.

public class MyDisjSets implements DisjointSets {
	private int[] up;		//up-tree that keeps track of the set(s)
	private int numSets;	//the current number of sets
	
	//constructs the disjoint set ADT
	//each element begins as its own set
	public MyDisjSets(int numElements) {
		this.up = new int[numElements];
		for (int i = 0; i < numElements; i++) {
			up[i] = -1;
		}
		this.numSets = numElements;
	}
	
	//parameters set1 and set2 must be valid elements and set names
	//combines two disjoint sets into one
	public void union(int set1, int set2) {
		checkValidity(set1);
		checkValidity(set2);
		checkSetName(set1);
		checkSetName(set2);
		if (Math.abs(up[set1]) < Math.abs(up[set2])) {
			up[set2] += up[set1];
			up[set1] = set2;
		} else {
			up[set1] += up[set2];
			up[set2] = set1;
		}
		this.numSets--;
	}

	//finds which element the given parameter x belongs to
	public int find(int x) {
		checkValidity(x);
		int belongsTo = x;
		while (up[belongsTo] > -1) {
			belongsTo = up[belongsTo];	
		}
		//compress path
		if (x == belongsTo) {
			return belongsTo;
		}
		int oldParent = up[x];
		while (oldParent != belongsTo) {
			up[x] = belongsTo;
			x = oldParent;
			oldParent = up[x];
		}
		return belongsTo;
	}

	//returns the current number of sets
	public int numSets() {
		return this.numSets;
	}

	//returns true if the given parameter x is the name of a set
	public boolean isSetName(int x) {
		checkValidity(x);
		return (up[x] < 0);
	}

	//returns the number of elements in a given set
	public int numElements(int setNum) {
		checkValidity(setNum);
		checkSetName(setNum);
		return Math.abs(up[setNum]);
	}

	//prints out the elements in a given set
	public void printSet(int setNum) {
		int[] set = this.getElements(setNum);
		String result = "{" + set[0];
		for (int i = 1; i < set.length; i++) {
			result += ", " + set[i];
		}
		System.out.println(result + "}");
	}

	//returns array containing the elements of a given set
	public int[] getElements(int setNum) {
		checkValidity(setNum);
		checkSetName(setNum);
		int[] elements = new int[this.numElements(setNum)];
		int index = 0;
		for (int i = 0; i < up.length; i++) {
			if (setNum == this.find(i)) {
				elements[index] = i;
				index++;
			}
		}
		return elements;
	}	
	
	//helper method that throws error if the given parameter 
	//is not a valid element
	private void checkValidity(int x) { 
		if (x < 0 || x > up.length - 1) {
			throw new InvalidElementException();
		}
	}
	
	//helper method that throws error if the given parameter
	//is not the name of a set
	private void checkSetName(int x) {
		if (!this.isSetName(x)) {
			throw new InvalidSetNameException();
		}
	}
}