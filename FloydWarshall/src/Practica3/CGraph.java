package Practica3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author ccottap
 *
 */
public class CGraph {
	protected int numV;           // number of vertices
	protected int[][] distances;  // distance among vertices
	final protected static int Infinity = Integer.MAX_VALUE; // used to mark non-existing edges (=infinite distance)
	
	/**
	 * Creates an empty graph
	 * 
	 */
	public CGraph () {
		numV=0;
		distances = null;
	}
	
	/**
	 * Creates a graph with n vertices and no edges
	 * 
	 */
	public CGraph (int n) throws Exception {
		resize(n);
	}
	
	//
	
	/**
	 * Sets an entry of the graph
	 * 
	 */
	public void set (int i, int j, int d) throws Exception {
		if ((i<numV) && (i>=0) && (j<numV) && (j>=0)) {
			distances[i][j] = d;
		}
		else {
			throw new Exception ("Illegal node labels in set: " + i + ", " + j);
		}
	}

	/**
	 * Gets an entry of the graph
	 * 
	 */
	public int get (int i, int j) throws Exception {
		if ((i<numV) && (i>=0) && (j<numV) && (j>=0)) {
			return distances[i][j];
		}
		else {
			throw new Exception ("Illegal node labels in get: " + i + ", " + j);
		}
	}
	
	/**
	 * Returns the number of vertices of the graph
	 * 
	 */
	public int numVertices () {
		return numV;
	}
	
	/**
	 * Resizes the internal members of the graph. 
	 * Any information previously stored is lost.
	 * 
	 */
	protected void resize (int n) throws Exception {
		if (n <= 0) {
			throw new Exception ("The number of vertices must be strictly positive (" + n + ")");
		}
		numV = n;
		distances = new int[numV][numV];
		for (int i=0; i<numV; i++) {
			for (int j=0; j<numV; j++)
				distances[i][j] = Infinity; // Initializes all distances to infinity
			distances[i][i]=0;
		}
	}
	
	/**
	 * Reads the graph from a file
	 * 
	 */
	public void read (String filename) throws Exception {
		Scanner inputFile = new Scanner (new File(filename)); 
		
		try {
			int n = inputFile.nextInt(); // gets number of vertices
			resize(n);
				
			int i = inputFile.nextInt();
			while (i>=0) {
				int j = inputFile.nextInt();  // reads actual edges
				set(i, j, inputFile.nextInt());
				i = inputFile.nextInt();
			}
			
		} catch (InputMismatchException ime) {
			throw new Exception("Input file is wrongly formatted: " + ime.getMessage());
		} catch (NoSuchElementException nse) {
			throw new Exception("Input file misses entries: " + nse.getMessage());
		}
		
		inputFile.close();		
	}
	
	/**
	 * Writes the graph to a file
	 * 
	 */
	public void save (String filename) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		out.println(numVertices());
		for (int i=0; i<numV; i++) {
			for (int j=0; j<numV; j++)
				if (get(i,j)<Infinity)
					out.println(i + " " + j + " " + get(i,j));
		}
		out.println(-1);
		out.close();
	}

	public void print() {
		// TO DO
	}
	
}
