package gen;

public class ArrayList<E> implements List<E> {
	
	
	private static final int defaultSize = 10;
	private int maxSize ;
	private int listSize ;
	private int curr ;
	private E [] listArray ;
	private LList<Character>[] LArray;
	
	
	
	/** Constructors */
	
	
	/** Create a list with the default capacity .*/
	ArrayList () {
		this ( defaultSize );
	}
	
	/** Create a new list object .
	@param size Max # of elements list can contain .
	*/
	ArrayList (int size ) {
		maxSize = size ;
		listSize = curr = 0;
		listArray = ( E []) new Object [ size ];
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		listSize = curr = 0;

	}
	
	
	@Override
	public void insert(E it) {
		// TODO Auto-generated method stub
		assert listSize < maxSize : "Too many elements ";
		for (int i = listSize -1; i >= curr ; i --)
		listArray [ i +1] = listArray [ i ];
		listArray [ curr ++] = it ;
		listSize ++;
	}

	@Override
	public void append(E it) {
		// TODO Auto-generated method stub
		assert listSize < maxSize : "Too many elements ";
		listArray [ listSize ++] = it ;


	}
	
	
	public E remove() {
		// TODO Auto-generated method stub
		if ( curr >= listSize ) { return null ;}
		E it = listArray [ curr ];
			for (int i = curr ; i < listSize -1; i ++)
				listArray [ i ] = listArray [ i +1];
				listSize --;
		return it ;
	}

	@Override
	public void moveToStart() {
		// TODO Auto-generated method stub
		curr = 0;
	}
	public int size(){
		return listSize;
	}

	@Override
	public void moveToEnd() {
		// TODO Auto-generated method stub
		curr = listSize ;
	}

	@Override
	public void prev() {
		// TODO Auto-generated method stub
		if ( curr != 0) curr --; 
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		if ( curr < listSize -1) curr ++;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return listSize ; 
	}

	@Override
	public int currPos() {
		// TODO Auto-generated method stub
		return curr ; 
	}

	@Override
	public void moveToPos(int pos) {
		// TODO Auto-generated method stub
		assert ( pos >=0) && ( pos <= listSize ) : "Out of range ";
		curr = pos ;
	}

	@Override
	public E getValue() {
		// TODO Auto-generated method stub
		assert ( curr >=0) && ( curr < listSize ) : "No element ";
		return listArray [ curr ];

	}	

}
