package Practica3;

/**
 * @author ccottap
 *
 */
public class FloydWarshallAlgorithm {
	/**
	 * Runs Floyd-Warshall algorithm on a given graph, and creates distance and trace graphs
	 * 
	 */
	static int distance(int n, int [][] grafo, int p1, int p2){
		//n es el numero de vértices.
		 int i,j,k;
		 int [ ][ ] MatrizCoste = new int [n][n];
		 int [ ][ ] Traza = new int [n][n];
		 /*Copiamos la matriz de adyacencia en nuestra matriz de coste mínimo*/
		 for(i=0;i<n;i++){
			 for(j=0;j<n;j++){
				 MatrizCoste[i][j]=grafo[i][j];
				 Traza[i][j]=j+1;
			 }
		 }
		 //Caso general
		 for (k=0; k<n; k++){
			 for (i=0; i<n; i++) {
				 for(j=0;j<n;j++){
					 if(MatrizCoste[i][k]+MatrizCoste[k][j] < MatrizCoste[i][j]){
						 MatrizCoste[i][j] = MatrizCoste[i][k]+MatrizCoste[k][j];
						 Traza[i][j]=k+1;
					 }
				 }
			 }
		 }
		//Mostramos en pantalla la matriz de coste mínimo MatrizCoste[][]
		 for (i=0; i<n; i++) {/*recorremos las filas*/
			 for (j=0; j<n; j++) {/*recorremos las columnas*/
				 System.out.print(MatrizCoste[i][j]+" - ");
			 }
		 System.out.println(" ");
		 }
		 return MatrizCoste.length;

		
	}
	
	static int traza( int n, int [][] grafo, int p1, int p2  ){
		//n es el numero de vértices.
		 int i,j,k;
		 int [ ][ ] MatrizCoste = new int [n][n];
		 int [ ][ ] Traza = new int [n][n];
		 /*Copiamos la matriz de adyacencia en nuestra matriz de coste mínimo*/
		 for(i=0;i<n;i++){
			 for(j=0;j<n;j++){
				 MatrizCoste[i][j]=grafo[i][j];
				 Traza[i][j]=j+1;
			 }
		 }
		 //Caso general
		 for (k=0; k<n; k++){
			 for (i=0; i<n; i++) {
				 for(j=0;j<n;j++){
					 if(MatrizCoste[i][k]+MatrizCoste[k][j] < MatrizCoste[i][j]){
						 MatrizCoste[i][j] = MatrizCoste[i][k]+MatrizCoste[k][j];
						 Traza[i][j]=k+1;
					 }
				 }
			 }
		 }
		//Mostramos en pantalla la matriz de Traza[][]
		  for (i=0; i<n; i++) {/*recorremos las filas*/
			  for (j=0; j<n; j++) {/*recorremos las columnas*/
				  System.out.print(Traza[i][j]+" - ");
			  }
			  System.out.println(" ");
		  	}
		return Traza.length;

				
	}
	static int path (int n, int[][]adyac, int p1, int p2){
		int i,j,k;
		int[][]CosteMin = new int[n][n];
		int[][] traza = new int[n][n];
		
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				CosteMin[i][j] = adyac[i][j];
				traza[i][j]= j+1;
			}
		}
		
		for(k=0;k<n;k++){
			for(i=0;i<n;i++){
				for(j=0;j<n;j++){
					if(CosteMin[i][j]>CosteMin[i][k]+CosteMin[k][j]){
						CosteMin[i][j]=CosteMin[i][k]+CosteMin[k][j];
						traza[i][k]=k+1;
					}
				}
			}
		}
		i=p1-1;
		j=p2-1;
		if(CosteMin[i][j]<Integer.MAX_VALUE){
			System.out.println(p1+" -->");
			do{
				System.out.println(traza[i][j]+" -->");
				i=traza[i][j]-1;
			}while(traza[i][j]!=j+1);
				System.out.println(p2);
				return(CosteMin[p1-1][p2-1]);
			}
		return traza.length;
		} 
	static int max_path (int n, int[][]adyac, int p1, int p2){
		int i,j,k;
		int[][]CosteMin = new int[n][n];
		int[][] traza = new int[n][n];
		
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				CosteMin[i][j] = adyac[i][j];
				traza[i][j]= j+1;
			}
		}
		
		for(k=0;k<n;k++){
			for(i=0;i<n;i++){
				for(j=0;j<n;j++){
					if(CosteMin[i][j]>=CosteMin[i][k]+CosteMin[k][j]){
						CosteMin[i][j]=CosteMin[i][k]+CosteMin[k][j];
						traza[i][k]=k+1;
					}
				}
			}
		}
		i=p1-1;
		j=p2-1;
		if(CosteMin[i][j]<Integer.MAX_VALUE){
			System.out.println(p1+" -->");
			do{
				System.out.println(traza[i][j]+" -->");
				i=traza[i][j]-1;
			}while(traza[i][j]!=j+1);
				System.out.println(p2);
				return(CosteMin[p1-1][p2-1]);
			}
		return k;
		} 
	
	public void run(CGraph myGraph, CGraph dist, CGraph trace) throws Exception {
		//TODO Complete this method		
	}
}
