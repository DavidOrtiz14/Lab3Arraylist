package arrayIndexList;



import java.lang.reflect.Array;

import indexList.IndexList;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	

	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(size()==element.length){
			changeCapacity(size+1);
		}
		
		if(index>=capacity()||index<0){
			throw new IndexOutOfBoundsException("index:"+index+" is not a valid index.");
		}
		
		else{
			moveDataOnePositionTR(index,  size-1);
			element[index]=e;
			size++;
		}
	}


	public void add(E e) {
		if(size==element.length){
			changeCapacity(size+1);
		}
		
		element[size]=e;
		size++;
		
	}


	public E get(int index) throws IndexOutOfBoundsException {
		if((index<size() && index>=0)){
			
			return element[index];
		}
		else{
			throw new IndexOutOfBoundsException("index:"+index+" is not a valid index.");

		}
		
	}


	public boolean isEmpty() {
		return size == 0;
	}

	
	public E remove(int index) throws IndexOutOfBoundsException {
		E[] element1 = (E[]) new Object[INITCAP];
		
		if(capacity()-size()>MAXEMPTYPOS ){
			changeCapacity(-INITCAP);
		}
		if(index>size()||index<0){
			throw new IndexOutOfBoundsException("index:"+index+" is not a valid index.");
		}
		
		
		else{
			E c=element[index];
			moveDataOnePositionTL(index+1,size-1);
			element[size-1]=null;
			size--;
			if(element.length - size > MAXEMPTYPOS){ this.changeCapacity(-CAPTOAR); }
			return c;
			
		}
	}


	public E set(int index, E e) throws IndexOutOfBoundsException {
		
	
		if((index<size() && index>=0)){
			E c=element[index];
			element[index]=e;
			return c;
			
		}
		else{
			throw new IndexOutOfBoundsException("index:"+index+" is not a valid index.");
			
		}
	
	}


	public int size() {
		return size;
	}	
	
	
	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}


	// The following two methods are to be implemented as part of an exercise
	public Object[] toArray() {
		Object[] a = new Object[size()]; 
		for(int x =0; x<size;x++) {
			a[x]=element[x];
		}
		return a;
	}


	@Override
	public <T1> T1[] toArray(T1[] array) {
		if(array.length < size()) {
			array = (T1[]) Array.newInstance(array.getClass().getComponentType(), size());
		}
		if(array.length < size()) {
			for(int y=size();y<array.length;y++) 
				array[y]= null;
			
			for(int z=0;z<size();z++) {
				if(z>=array.length) {
					break;
				}
				array[z]=(T1) element[z];
			}
		}
		return array;
	}
	
	
	
	
	public  int capacity(){
		return element.length;
		
	}

}
