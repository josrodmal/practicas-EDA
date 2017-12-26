/*@cottap*/
public class FloydWarshallAlgorithm {

 public void run(CGraph myGraph, CGraph dist, CGraph trace) throws Exception {
	 int i,j,k;
	 int n = myGraph.numVertices();
  

 
	 dist.resize(n);
	 trace.resize(n);
  
  	for (i = 0; i < n; i++){
  		for (j = 0; j < n; j++){
  			int d = myGraph.get(i,j);
  			dist.set(i,j,d);
  			trace.set(i,j,j);
    	}
  	
  	for (k = 0; k < n; k++) {
  		for (i = 0; i < n; i++) {
  			for (j = 0; j < n; j++) {
  				
  				int sdist, sdist2;
  				int min;
   
  				if ((dist.get(i,k) == CGraph.Infinity) || (dist.get(k,j) == CGraph.Infinity))
  					sdist = CGraph.Infinity;
  				

  				else sdist = dist.get(i, k) + dist.get(k, j);  
  				sdist2 = dist.get(i, j); 
  				
  				if (sdist < sdist2)  min = sdist;
  				else  min = sdist2; 
  				dist.set(i,j,min); 
      
  				if (min == sdist2)  trace.set(i,j,trace.get(i,j));  
 
  				else trace.set(i,j,k);
  					
  			} 
  		} 
  	} 
 } 
}
	
