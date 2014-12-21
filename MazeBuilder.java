//Brett Yamada, CSE 373 Winter 2014, Homework #4 Part II
//uses the created MyDisjSets ADT from Part I to create a maze
//of user inputed dimensions. There is only one solution, all 
//cells are reachable, and there are no cycles. The maze is 
//output to a file of the given name.

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MazeBuilder {
	public static void main(String[] args) {
		if (args.length != 3) {
            System.err.println(" Incorrect number of arguments");
            System.err.println(" Usage: ");
            System.err.
            println("\tjava MazeBuilder <height> <width> <output file>");
            System.exit(1);
		}
		try {
			int height = Integer.parseInt(args[0]);
			int width = Integer.parseInt(args[1]);
			String outputFileName = (args[2]);
			//make sure the dimensions are valid
			if (height < 1 || width < 1) {
				throw new NumberFormatException();
			}
            
			//set up the output file to write to
            PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName)));
            
            //set up the maze, capture all internal edges
            MyDisjSets mazeManager = new MyDisjSets(height * width);
            ArrayList<ArrayList<Integer>> edgesLeft = new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Integer>> edgesKept = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < height * width; i++) {
            	if ((i + 1) % width != 0) {										//if the cell is not in the right-most column
            		ArrayList<Integer> edgeRight = new ArrayList<Integer>();	//add the adjacent edge to the right
            		edgeRight.add(0, i);
            		edgeRight.add(1, i + 1);
            		edgesLeft.add(edgeRight);				
            	}
            	if (i < width * (height - 1)) {									//if the cell is not in the bottom-most row
            		ArrayList<Integer> edgeBelow = new ArrayList<Integer>();	//add the adjacent edge below
            		edgeBelow.add(0 , i);
            		edgeBelow.add(1, i + width);
            		edgesLeft.add(edgeBelow);		
            	}
            }
            
            //make the path by removing internal edges at random
            Random rn = new Random();
            while (mazeManager.numSets() > 1) {
            	int randIdx = rn.nextInt(edgesLeft.size());
            	ArrayList<Integer> randEdge = edgesLeft.remove(randIdx);
            	int set1 = mazeManager.find(randEdge.get(0));
            	int set2 = mazeManager.find(randEdge.get(1));
            	
            	if (set1 == set2) {
            		edgesKept.add(randEdge);
            	} else {
            		mazeManager.union(set1, set2);
            	}
            }
            int numEdgesLeft = edgesLeft.size();
            for (int i = 0; i < numEdgesLeft; i++) {
            	edgesKept.add(edgesLeft.remove(0));
            }
                        
            //print the maze
            printMaze(edgesKept, fileOut, height, width);
          
            fileOut.close();
            
		} catch(IOException ioe) {
            System.err.println("Error opening/reading/writing input or output file.");
            System.exit(1);
		} catch(NumberFormatException nfe) {
            System.err.println(nfe.toString());
            System.err.println("The height and width must be a non-negative, non-zero integer.");
            System.exit(1);
		}
		
	}	
	
	//prints the maze 
	public static void printMaze(ArrayList<ArrayList<Integer>> edgesKept, 
								PrintWriter fileOut, int height, int width) {
		//print the top of the maze
        for (int i = 0; i < width; i++) {
        	fileOut.print("+-");
        }
        fileOut.println("+");
        
        //print the borders of the maze and internal walls
        for (int i = 0; i < height * width; i += width) {
        	if (i == 0) {
        		fileOut.print(" ");
        	} else {
        		fileOut.print("|");
        	}
        	for (int j = 0; j < width - 1; j++) {
        		ArrayList<Integer> edge = new ArrayList<Integer>();
        		edge.add(0, i + j);
        		edge.add(1, i + j + 1);
        		if (edgesKept.contains(edge)) {
        			fileOut.print(" |");
        		} else {
        			fileOut.print("  ");
        		}
        	}
        	
        	if (i < width * (height - 1)) {
        		fileOut.println(" |");
            	for (int k = 0; k < width; k++) {
            		ArrayList<Integer> edge = new ArrayList<Integer>();
            		edge.add(0, i + k);
            		edge.add(1, i + k + width);
            		if (edgesKept.contains(edge)) {
            			fileOut.print("+-");
            		} else {
            			fileOut.print("+ ");
            		}
            	}
            	fileOut.println("+");
           	} else {
           		fileOut.println("  ");
           	}
        }
        
        //print the bottom of the maze
        for (int i = 0; i < width; i++) {
        	fileOut.print("+-");
        }
        fileOut.println("+");
	}
}
