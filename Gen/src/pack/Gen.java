package pack;

/* vamos a tener un array donde cada fila hay bases hidrogenadas y en cada pos 
 * de la col tenemos el tipo
 * 
 * se implementa--> lista enlazada en fila, copiar transparencias List.java(interfaz)
 * ////LList.java///Link.java(las clases)
 * 
 * una vez tenemos eso falta la parte de la col
 * 
 * si lo defino como un array tenemos que decir que va a llevar dentro
 *  ¿Cómo se define que lleva dentro un array?
 * 
 * 	podemos tener una clase secuenceia que tendra dentro la lista enlazada
 *  y el tipo y el pos se guarda ya en el array
 * 
 * en el fichero bio in estan las sentencias que con el leefichero que con el metodo procesar
 * Procesar: recibe una linea, trocea los elementos
 * 
 * hayq ue comparar si insert es del método insert, entonces si es igual,
 *  ejecuta el metodo insert y le pasamos los argumentos que tenga
 * 
 * ADN puede ser un tipo enumerado
 * 
 * El string si se puede usar pero no se puede usar solo para la secuencia
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Scanner;
import java.util.StringTokenizer;

// Esqueleto básico para el ejercicio "Secuencias Genéticas con listas"

//arrastramos bio.in aq la carpeta practica 1 y abrimos gen, si no compila y da un error 38 o algo asi lo que hay que hacer es en trar en run/runConfigurations y en arguments ponemos 10 bio.in
public class Gen {

	private LList<Character>[] tabla;
		
	public static void main(String[] args) {
		Gen gen = new Gen(Integer.parseInt(args[0]));
		gen.leerFichero(args[1]);	
	}
	
	public Gen (int size){
		ArrayList[] tabla = new ArrayList[size];
		for(int i=0;i<tabla.length;i++){
			tabla[i]=new ArrayList<Character>();
		}
	}
	//FUNCION 1
	
	public void insert(int pos, tipoSeq tipo, String seq){
		boolean correcto=true;
		for(int i=0;i<seq.length()||correcto==false;i++){
			if(seq.charAt(i)!='G'&& seq.charAt(i)!='A'&& seq.charAt(i)!='C'){
				if(seq.charAt(i)!='T' && tipo==tipo.ADN){correcto=false;}
				if(seq.charAt(i)!='U' && tipo==tipo.ARN){correcto=false;}
			}
		}if(correcto){
			LList<Character> sec = new LList();
			
			sec.setADNARN(tipo.ADN);
			for(int i=0; i<seq.length();i++){
				sec.insertFinal(seq.charAt(i));
			}
			tabla[pos]= sec;
		}
	}
	
	
	//FUNCION 2-->Borrar la secuencia en la posición pos
	
	public void remove (int pos){
		if(pos<0 || pos>tabla.length){
			throw new IllegalArgumentException(" NO VALIDO");
		}
		tabla[pos]= new LList<Character>();
	}
	
	
	//FUNCION 3 -->Imprimir toda la secuencia genética indicando las secuencias y de que tipo son.
	
	public void print(){
		//Aquí esta cambiado para que siempre sea ADN menos cuando entra en el else que entonces se convertiria en ARN
				String tipo= tipoSeq.ADN.toString();
				for(int i=0;i<tabla.length;i++){
					if(tabla[i].size()!=0){
						if(tabla[i].getADNARN()==tipoSeq.ADN){
							System.out.println(i+ tipo);
						}
						else{

							tipo= tipoSeq.ARN.toString();
							System.out.println(i+ tipo);
						}
						tabla[i].verLista();
					}
				}
			}
			
	//FUNCION 4--> Imprimir solo la secuencia en la posición pos.
	

	public void print(int pos){
		if(pos<0||pos>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		//Aquí tenemos que el syso debe tener el tipo de adn que sea
		
		System.out.println(pos+" "+tabla[pos].getADNARN().toString()+" ");//ver si esto vale en vez de poneer el tipo
		tabla[pos].verLista();
	}
	
	
	//FUNCION 5 -->Reemplazar la secuencia en la posición pos por la sub-secuencia que se obtiene con
	//todas las letras desde la letra en la posición start hasta la letra en posición end.
	
	public void clip(int pos, int start, int end){
		int i,n;
		if(pos<0||pos>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		if(tabla[pos].size()==0){System.out.println("sec vacía");}
		else if(start<=end){
			n= tabla[pos].size();
			if(end<(n-1)){
				for(i=(n-1);i<end;i++){
					tabla[pos].eliminarPos(i);
				}
			}
			if(start>0){
				for(i=(start-1);i>=0;i++){
					tabla[pos].eliminarPos(i);
				}
			}
		}
	}
		
	//FUNCION 6-->Copia la secuencia de la posición pos1 a la posición pos2
	public void copy(int pos1, int pos2){
		//Primero comprobamos que las dos posiciones que nos dan sean válidas
		if(pos1<0||pos1>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		if(pos2<0||pos2>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		//Ahora, tenemos que coger la secuencia que queremos y meterla
		for(int i=0;i<tabla[pos1].length();i++){
			tabla[pos2].insertFinal(tabla[pos1].consultarPos(i));;
		}
		Integer tam1 = tabla[pos1].length();
		Integer tam2 = tabla[pos2].length();
		clip(pos2, tam2, tam1+tam2);	
		
		//IDEA: concatenar la secuencia 1 a la dos y hacerle un clip para que coja la 1 asi eliminamos la 2 y se queda en esa pos
	}
	
	//FUNCION 7-->Intercambia la cola de la secuencia en pos2 a partir de la letra en la
	//posición start2, hasta el final; por la cola de la secuencia desde en la posición pos1 
	//desde la letra start1 hasta el final.
	
	public void swap(int pos1, int start1, int pos2, int start2){
		int n1, n2, i;
		if(pos1<0||pos1>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		if(pos2<0||pos2>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		n1=tabla[pos1].size();
		n2=tabla[pos2].size();
		if(start1<(n1-1)){
			if(start2<(n2-1)){
				for(i=n2-1; i>=start2;i--){
					tabla[pos2].eliminarPos(i);
				}
			}
			for(i=start1; i<n1;i++){
				tabla[pos2].insertFinal(tabla[pos1].consultarPos(i));
			}
		}
		
		
	}
	
	public void transcribe(int pos){
		//Primero tenemos que coger un secuenca
		if(pos<0||pos>tabla.length){
			throw new IllegalArgumentException("POSICION NO VALIDA");
		}
		
		if(tabla[pos].getADNARN()==tipoSeq.ADN){
			
			tabla[pos].setADNARN(tipoSeq.ARN);
			for(int i=0; i<tabla[pos].length();i++){
				if(tabla[pos].consultarPos(i)=='T'){
					tabla[pos].insertarEnPos(i, 'U');
				}
			}
			//Hasta aquí ya hemos cambiado ahora toca cambiar por sus complementarias
			
			for(int i=0; i<tabla[pos].length();i++){
				if(tabla[pos].consultarPos(i)=='A'){
					tabla[pos].insertarEnPos(i, 'U');
				}
				if(tabla[pos].consultarPos(i)=='C'){
					tabla[pos].insertarEnPos(i, 'G');
				}
				if(tabla[pos].consultarPos(i)=='G'){
					tabla[pos].insertarEnPos(i, 'C');
				}
				if(tabla[pos].consultarPos(i)=='U'){
				tabla[pos].insertarEnPos(i, 'A');

				}
			}
			
			//Por ultimo nos falta invertir la cadena	
		Integer n=tabla[pos].length();
			for(int j=tabla[pos].length(); j>=0;j--){
				tabla[pos].insertFinal(tabla[pos].consultarPos(j));	
				clip(pos, n-1, 2*n-2 );
			}

		}else{
			throw new IllegalArgumentException("NO SE PUEDE TRANSCRIBIR EL ARN");
		}
	}
		
				
	protected void procesar(String linea) {
		// A rellenar
	String[] lin = linea.split(" ");
	String instruccion = lin[0].trim();	
	
	if(instruccion=="insert"){
		insert(new Integer(lin[1].trim()), tipoSeq.valueOf(lin[2].trim()), lin[3].trim());
	}
	if(instruccion=="remove"){
		remove(new Integer(lin[1].trim()));
	}
	if(instruccion == "print"){
		if(new Integer(lin[1].trim())!=null){
			print(new Integer(lin[1].trim()));
		}else{
			print();
		}
	
	}
	if(instruccion=="clip"){
		clip(new Integer(lin[1].trim()), new Integer(lin[2].trim()), new Integer(lin[3].trim()));
	}
	
	if(instruccion=="copy"){
		copy(new Integer(lin[1].trim()), new Integer(lin[2].trim()));
	}
	
	
	if(instruccion =="swap"){
		swap(new Integer(lin[1].trim()), new Integer(lin[2].trim()), new Integer(lin[3].trim()), new Integer(lin[4].trim()));
	}

	if(instruccion=="transcribe"){
		transcribe(new Integer(lin[1].trim()));
	}

	
	
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	

	

}

