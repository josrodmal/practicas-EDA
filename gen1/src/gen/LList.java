package gen;

public class LList<E> implements List<E> {

	
	private Link <E > head ;
	private Link <E > tail ;
	protected Link <E > curr ;
	private int cnt ;
	//Creamos aquí un tipo para saber si es ADn o ARN
	private tipoSeq tipADN;
	
	/** Constructors */
	LList (int size ) {
		this (); 
	}
	LList (){
		curr = tail = head = new Link <E >( null );
		cnt = 0;
		tipADN=null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(E it) {
		// TODO Auto-generated method stub
		curr . setNext (new Link <E >( it , curr . getNext ()));
		if ( tail == curr )
			tail = curr . getNext (); // New tail
			cnt ++;


	}
	
	public void insertFinal(E nucl){
		
		Link<E> nuevo=new Link<E>(nucl, null);
		
		if(cnt==0){
			head=curr=tail=nuevo;
		}
		else{
			tail.setNext(nuevo);
			tail=nuevo;
		}
		cnt++;
	}
	//Insertar en una posición
		public void insertarEnPos(int pos, E dato){
			if(pos>cnt){
				insertFinal(dato);
			}
			else{
				Link<E> nuevo= new Link<E>(dato,null);
				curr=head;
				for(int i=0;i<pos;i++){
					curr=curr.getNext();
				}
				nuevo.setNext(curr.getNext());
				curr.setNext(nuevo);
				cnt++;				
			}
		}
			
		public void verLista (){
		
			if(cnt!=0){
				for(int i=0; i<cnt; i++){
					moveToPos(i);
					System.out.println(getValue());
				}
			}
			
		}
		
		public void eliminarPos(int pos){
			Link<E> aux= new Link<E>(null,null);
			if(pos<0||pos>cnt){
				System.out.println("Posición no válida");
			}
			else{
				if(cnt!=0){
					moveToPos(pos);
					if(head==curr){
						head=head.getNext();
						curr=head;cnt--;
					}
					else{
						aux=head;
						while(aux.getNext()!=curr){
							aux=aux.getNext();
							if(curr==tail){
								aux.setNext(null);
								curr=tail=aux;
							}
							else{
								aux.setNext(curr.getNext());
								curr.setNext(null);
								curr=aux;
							}
							cnt--;
						}
					}
				}
			}
			
		}
			
			//Función: Devuelve el contenido guardado en una posicion dada
			
			public E consultarPos(int pos){
				if(pos<0||pos>cnt){
					throw new IllegalArgumentException("Posicion a consultar incorrecta");
				}
				if(cnt==0){
					throw new IllegalArgumentException("La lista esta vacia");
				}
				moveToPos(pos);
				return(curr.getElement());
			}
					
			public void  setADNARN(tipoSeq tip){
				tipADN=tip;				
			}
			public tipoSeq getADNARN(){
				return (tipADN);
			}


	@Override
	public void append(E it) {
		// TODO Auto-generated method stub
		tail = tail . setNext (new Link <E >( it , null ));
		cnt ++;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		if ( curr . getNext () == null )
			return null ; // Nothing to remove
			E it = curr . getNext (). getElement ();
			// Remember value
			if ( tail == curr . getNext ()) tail = curr ;
			// Removed last
			curr . setNext ( curr . getNext (). getNext ());
			// Remove from list
			cnt --; // Decrement count
			return it ; // Return value
	}

	@Override
	public void moveToStart() {
		// TODO Auto-generated method stub
		{ curr = head ; }

	}

	@Override
	public void moveToEnd() {
		// TODO Auto-generated method stub
		{ curr = tail ; }
	}

	@Override
	public void prev() {
	
		// TODO Auto-generated method stub
		if ( curr == head ) return ; // No previous element
		Link <E > temp = head ;
	
		// Traverse list until previous element found
		while ( temp.getNext()!= curr ) temp = temp.getNext ();
		curr = temp ;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		{ if ( curr != tail ) curr = curr . getNext (); }
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return cnt;
	}

	@Override
	public int currPos() {
		// TODO Auto-generated method stub
		int i;
		Link <E > aux = head ;
		for (i=0; aux != curr ; i ++)
			aux = aux . getNext ();
		return i;
	}

	@Override
	public void moveToPos(int pos) {
		// TODO Auto-generated method stub
		assert ( pos >=0) && ( pos <= cnt ) : "Out of range ";
		curr = head ;
		for (int i =0; i < pos ; i ++) curr = curr . getNext ();

	}

	@Override
	public E getValue() {
		// TODO Auto-generated method stub
		if ( curr . getNext () == null ) return null ;
		return curr . getNext (). getElement ();

	}
	public int size() {
		// TODO Auto-generated method stub
		return cnt;
	}

}
