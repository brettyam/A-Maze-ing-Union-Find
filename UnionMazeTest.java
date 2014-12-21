//Brett Yamada, CSE 373 Winter 2014, Homework #4
//Test program for MyDisjSets.

import java.util.*;

public class UnionMazeTest {
	public static void main(String[] args) {
		/* MyDisjSets test
		MyDisjSets testSet = new MyDisjSets(10);
		System.out.println(testSet.numSets());			//return 10 - check
		testSet.union(0, 6);
		System.out.println(testSet.numSets());			//return 9 - check
		System.out.println(testSet.isSetName(6));		//return false - check
		System.out.println(testSet.isSetName(0));		//return true - check
		System.out.println(testSet.find(6));			//return 0 - check
		System.out.println(testSet.find(8));			//return 8 - check
		testSet.union(3, 8);	
		System.out.println(testSet.numSets());			//return 8 - check
		System.out.println(testSet.isSetName(8));		//return false - check
		System.out.println(testSet.isSetName(3));		//return true - check
		testSet.union(3, 9);	
		System.out.println(testSet.numSets());			//return 7 - check
		System.out.println(testSet.isSetName(0));		//return true - check
		testSet.union(0, 3);
		System.out.println(testSet.isSetName(0));		//return false - check
		System.out.println(testSet.numSets());			//return 6 - check
		System.out.println(testSet.find(6));			//return 3 - check
		System.out.println(testSet.find(8));			//return 3 - check
		testSet.union(2, 7);
		System.out.println(testSet.numSets());			//return 5 - check
		testSet.printSet(2);							//return {2, 7} - check
		testSet.printSet(3);							//return {0, 3, 6, 8, 9} - check
		testSet.union(1, 2);
		System.out.println(testSet.numSets());			//return 4 - check
		testSet.union(3, 4);
		testSet.union(3, 5);
		System.out.println(testSet.numSets());			//return 2 - check
		System.out.println(testSet.isSetName(5));		//return false - check
		System.out.println(testSet.find(1));			//return 2 - check
		System.out.println(testSet.find(5));			//return 3 - check
		testSet.printSet(2);							//return {1, 2, 7} - check
		testSet.printSet(3);							//return {0, 3, 4, 5, 6, 8, 9} - check
		testSet.union(2, 3);							
		System.out.println(testSet.numSets());			//return 1 - check
		testSet.printSet(3);							//return {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} - check	
		*/
		
		MyDisjSets test2 = new MyDisjSets(7);
		test2.union(6, 5);
		
	}
}