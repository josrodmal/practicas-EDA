import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

	
	public class CGraph {
		protected int numV;  
		protected int[][] distances; 
		final protected static int Infinity = Integer.MAX_VALUE; 
 
	
		public CGraph () {
			numV=0;
			distances = null;
		}
 

	 
		public CGraph (int n) throws Exception {
			resize(n);
		}
 

  
		public void set (int i, int j, int d) throws Exception {
			
			if ((i<numV) && (i>=0) && (j<numV) && (j>=0))
				distances[i][j] = d;
			else  throw new Exception ("Illegal node labels in set: " + i + ", " + j);
		}


 
		public int get (int i, int j) throws Exception {
			
			if ((i<numV) && (i>=0) && (j<numV) && (j>=0)) 
				return distances[i][j];
			else  throw new Exception ("Illegal node labels in get: " + i + ", " + j);
		}
 

  
		public int numVertices () {
			return numV;
		}
 
 

  
		protected void resize (int n) throws Exception {
			
			if (n <= 0) 
				throw new Exception ("The number of vertices must be strictly positive (" + n + ")");
			
			numV = n;
			distances = new int[numV][numV];
			for (int i=0; i<numV; i++) {
				for (int j=0; j<numV; j++)
					distances[i][j] = Infinity; 
				distances[i][i]=0;
			}
		}			

  
		@SuppressWarnings("resource")
		public void read (String filename) throws Exception {
			
			Scanner inputFile = new Scanner (new File(filename)); 
  
			try {
				int n = inputFile.nextInt(); 
				resize(n);
    
				int i = inputFile.nextInt();
				while (i>=0) {
					int j = inputFile.nextInt(); 
					set(i, j, inputFile.nextInt());
					i = inputFile.nextInt();
				}
			} 
   
			catch (InputMismatchException ime) { 
				throw new Exception("Input file is wrongly formatted: " + ime.getMessage());
			}
			catch (NoSuchElementException nse) {
				throw new Exception("Input file misses entries: " + nse.getMessage());
			}
  
			inputFile.close();  
		}
 

  
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

		public void print() throws Exception {
			
			for (int i=0; i<numV; i++) {
				for (int j=0; j<numV; j++){
					if (get(i,j)<Infinity)  {
						if (j<numV-1) 
							System.out.print(get(i,j) + " ");
						else  System.out.print(get(i,j));
						} 
					else System.out.print("INF" + " ");
				}
				if (i < numV - 1)  System.out.println();
			}
		}
 
		public void printPath(int i, int j) throws Exception {
			
			int k;
			k = get(i,j);
			if(k==j)  {
				System.out.print(j);
			}
			else {
				printPath(i,k);
				System.out.print(" -> ");
				printPath(k,j);
			} 
		}
		
		  public int longPath(int i, int j) throws Exception {
		   
		   int k, longitud = 0;
		   k = get(i,j);
		   if(k==j)  { 
		     if (i != j) longitud++; 
		   }
		   else {
		     longitud += longPath(i,k) + longPath(k,j);
		   }
		   return longitud;
		  }
 
}