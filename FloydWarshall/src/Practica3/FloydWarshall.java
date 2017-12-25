package Practica3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FloydWarshall {
	CGraph dist;
	CGraph trace;
	
	public FloydWarshall(String grafo) {
		 dist = new CGraph();
		 trace = new CGraph();
		CGraph myGraph = new CGraph();
		try {
			myGraph.read(grafo);
			FloydWarshallAlgorithm ashp = new FloydWarshallAlgorithm();
			ashp.run (myGraph, dist, trace);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void procesar(String linea) {
	
	}
	
	public void leerFichero(String nombreFichero) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
			String linea;
			while ((linea = br.readLine()) != null)   {
				  procesar(linea);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) throws Exception {
				
		if (args.length < 2) {
    	    System.err.println("The program requires two input parameters");
    	    System.exit(1);
    	}

		FloydWarshall fw = new FloydWarshall(args[1]); 
		fw.leerFichero(args[0]);	
		 
    }
}
