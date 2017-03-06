package cs240_HW4;

/**
 * A list can manage entries.
 * @author liang dong
 */
public class LinkedDataList<T> implements ListInterface<T> {
	
	private Node first;
	private Node last;
	private int size = 0;
	private static final int MAX_CAPACITY = 100000;
	
	// Node contains data field and a reference to another node.
	protected class Node {
		private T data;
		private Node next;
		
		/**
		 * constructor that initiates the member fields.
		 * @param data the data
		 * @param next the next node
		 */
		protected Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * constructor that initiates the member fields.
		 * @param data the data
		 */
		protected Node(T data) {
			this(data, null);
		}
		
		/**
		 * getData is a accessor method
		 * @return the data field
		 */
		protected T getData() {
			return data;
		}
		
		/**
		 * a mutator method
		 * @param data the data field
		 */
		protected void setData(T data) {
			this.data = data;
		}
		
		/**
		 * a accessor method
		 * @return the next node.
		 */
		protected Node getNext() {
			return next;
		}
		
		/**
		 * a mutator method
		 * @param next the next node.
		 */
		protected void setNext(Node next) {
			 this.next = next;
		}
		
	}
	
	// return the first node
		protected final Node getFirst() {
			return first;
		}
		
		//add node at the beginning
		protected final void addFirst(Node node) {
			node.setNext(first);
			first = node;
			size++;
		}
		
		//add node after a given node
		protected final void addAfter(Node nodeBefore, Node newNode) {
			newNode.setNext(nodeBefore.getNext());
			nodeBefore.setNext(newNode);
			size++;
		}
		
		//remove the first node
		protected final void removeFirst() {
			first.setData(null);
			first = first.getNext();
			size--;
		}
		
		//remove node after a given node
		protected final void removeAfter(Node nodeBefore) {
			nodeBefore.getNext().setData(null);
			nodeBefore.setNext(nodeBefore.getNext().getNext());
			size--;
		}

	/** Adds a new entry to the end of this list.
	   Entries currently in the list are unaffected.
	   The list's size is increased by 1.
	   @param newEntry  The object to be added as a new entry. */
	public void add(T newEntry) {
		Node temp = new Node(newEntry);
		if(size < MAX_CAPACITY) {
			if(size == 0) {
				first = temp;
				last = temp;
			}
			else {
				last.setNext(temp);
				last = temp;
			}
			size++;
		}
	}

	/** Adds a new entry at a specified position within this list.
	   Entries originally at and above the specified position
	   are at the next higher position within the list.
	   The list's size is increased by 1.
	   @param newPosition  An integer that specifies the desired
	                       position of the new entry.
	   @param newEntry     The object to be added as a new entry.
	   @throws  IndexOutOfBoundsException if either
	            newPosition < 1 or newPosition > getLength() + 1. */
	public void add(int newPosition, T newEntry) {
		if(newPosition < 1 || newPosition > getLength() + 1) {
			throw new IndexOutOfBoundsException();
		}
		Node newNode = new Node(newEntry);
		if(size < MAX_CAPACITY) {
			if(newPosition == 1) {
				newNode.setNext(first);
				first = newNode;
				if(isEmpty()) {
					last = first;
				}
			}
			else {
				Node traverser = first;
				for(int i = 1; i < newPosition - 1; i++) {
					traverser = traverser.getNext();
				}
				Node pointer = traverser.getNext();// index of pointer is equal to location
				newNode.setNext(pointer);
				traverser.setNext(newNode);
				if(newPosition == size + 1) {
					last = newNode;
				}	
			}
			size++;
		}
	}

	/** Removes the entry at a given position from this list.
	   Entries originally at positions higher than the given
	   position are at the next lower position within the list,
	   and the list's size is decreased by 1.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be removed.
	   @return  A reference to the removed entry.
	   @throws  IndexOutOfBoundsException if either 
	            givenPosition < 1 or givenPosition > getLength(). */
	public T remove(int givenPosition) {
		if(givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException();
		}
		T temp;
		if(givenPosition == 1) {
			temp = first.getData();
			first.setData(null);
			first = first.getNext();
		}
		else {
			Node traverser = first;
			for(int i = 1; i < givenPosition - 1; i++) {
				traverser = traverser.getNext();
			}
			Node pointer = traverser.getNext();// index of pointer is equal to location
			temp = pointer.getData();
			pointer.setData(null);
			traverser.setNext(pointer.getNext());
			if(givenPosition == size) {
				last = traverser;
			}
		}
		size--;
		return temp;
	}

	/** Removes all entries from this list. */
	public void clear() {
		while(size > 0) {
			remove(1);
		}
		first = null;
		last = null;
	}

	/** Replaces the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the entry to be replaced.
	   @param newEntry  The object that will replace the entry at the
	                    position givenPosition.
	   @return  The original entry that was replaced.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T replace(int givenPosition, T newEntry) {
		if(givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException();
		}

		Node traverser = first;
		for(int i = 1; i < givenPosition; i++) {
			traverser = traverser.getNext();
		}
		T temp = traverser.getData();
		traverser.setData(newEntry);
		return temp;
		
	}

	/** Retrieves the entry at a given position in this list.
	   @param givenPosition  An integer that indicates the position of
	                         the desired entry.
	   @return  A reference to the indicated entry.
	   @throws  IndexOutOfBoundsException if either
	            givenPosition < 1 or givenPosition > getLength(). */
	public T getEntry(int givenPosition) {
		if(givenPosition < 1 || givenPosition > getLength()) {
			throw new IndexOutOfBoundsException();
		}
		Node traverser = first;
		for(int i = 1; i < givenPosition; i++) {
			traverser = traverser.getNext();
		}
		T temp = traverser.getData();
		return temp;
		
	}

	/** Retrieves all entries that are in this list in the order in which
	   they occur in the list.
	   @return  A newly allocated array of all the entries in the list.
	            If the list is empty, the returned array is empty. */
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[size];
		Node pointer = first;
		for(int i = 0; i < size; i++) {
			arr[i] = pointer.getData();
			pointer = pointer.getNext();
		}
		return arr;
	}

	/** Sees whether this list contains a given entry.
	   @param anEntry  The object that is the desired entry.
	   @return  True if the list contains anEntry, or false if not. */
	public boolean contains(T anEntry) {
		Node traverser = first;
		for(int i = 1; i <= size; i++) {
			if(traverser.getData().equals(anEntry)) {
				return true;
			}
			traverser = traverser.getNext();
		}
		return false;
	}

	/** Gets the length of this list.
	   @return  The integer number of entries currently in the list. */
	public int getLength() {
		return size;
		
	}
	   
	/** Sees whether this list is empty.
	   @return  True if the list is empty, or false if not. */
	public boolean isEmpty() {
		return size == 0;
		
	}
	

}
