import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;

 public class FloydWarshall {
  CGraph dist;
  CGraph trace;
 
 public FloydWarshall(String archivo_grafo) {
  
  dist = new CGraph();
  trace = new CGraph();
  CGraph myGraph = new CGraph();
  
  try {
   myGraph.read(archivo_grafo);
   FloydWarshallAlgorithm ashp = new FloydWarshallAlgorithm();
   ashp.run(myGraph, dist, trace); 
  }
  catch (Exception e) { e.printStackTrace(); }
 }
 
 
 public void procesar(String linea) {
  
  StringTokenizer st = new StringTokenizer(linea);
  
  if (st.hasMoreTokens()) {
    String primera_palabra = st.nextToken();
    
    if (primera_palabra.equals("distance")) {
      try { 
        dist.print();
        System.out.println("\n");
      } 
      catch(Exception e) { e.printStackTrace();}
    }
    
    
    else if (primera_palabra.equals("trace")){
      try { 
        trace.print(); 
        System.out.println("\n");
      } 
      catch(Exception e) { e.printStackTrace(); }
    }
    
    
    else if (primera_palabra.equals("path")) {
      int node1 = Integer.valueOf(st.nextToken());
      int node2 = Integer.valueOf(st.nextToken());
      int n = dist.numVertices();
      if (node1 >= 0 && node2 >= 0 && node1 < n && node2 < n) {
        try {
          System.out.print(node1 + " -> ");
          trace.printPath(node1, node2);
          System.out.println("\n");
        }
        catch (Exception e) {  e.printStackTrace();} 
      } 
      else
        System.out.println("error\n");
    }
    
    
    else if (primera_palabra.equals("max_path")){
        
        int n = dist.numVertices();
        int max = 0;
        int longitud, node1 = 0, node2 =0;
        
        try{
          for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
              longitud = trace.longPath(i, j);
              if(longitud > max) {
                max = longitud;
                node1=i;
                node2=j;
              } 
            } 
          }
          System.out.print(node1 + " -> ");
          trace.printPath(node1, node2);
          System.out.println("\n");
        } 
        catch (Exception e) {  e.printStackTrace();} 
    }
  }
 }

 public void leerFichero(String nombreFichero) {
  try {
   BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
   String linea;
   while ((linea = br.readLine()) != null)   {
    procesar(linea);
   }
  br.close();
  } 
  catch (FileNotFoundException e) { e.printStackTrace();}
  catch (IOException e) {  e.printStackTrace(); }
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