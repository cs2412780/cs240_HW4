package cs240_HW4;


/**
 * A sorted list that keeps everything in order.
 * @author liang dong
 */
public class LinkedDataSortedList<T extends Comparable<? super T>>
			extends LinkedDataList<T>
			implements SortedListInterface<T> {
		
	
	/** Adds a new entry to this sorted list in its proper order.
	The list's size is increased by 1.
	@param newEntry  The object to be added as a new entry. */
	@Override
	public void add(T newEntry) {
		Node temp = new Node(newEntry);
		if(isEmpty()) {
			addFirst(temp);
		}
		else {
			Node traverser = getFirst();
			if(getFirst().getData().compareTo(newEntry) > 0) {
				addFirst(temp);
			}
			else {
				for(int i = 1; i < getLength(); i++) {
					if(traverser.getNext().getData().compareTo(newEntry) <= 0) {
						traverser = traverser.getNext();
					}
				}
				addAfter(traverser,temp);
			}
		}
	}

	/** Removes the first or only occurrence of a specified entry
	from this sorted list.
	@param anEntry  The object to be removed.
	@return  True if anEntry was located and removed; 
         otherwise returns false. 
     */
	public boolean remove(T anEntry) {
		Node traverser = getFirst();
		if(getFirst().getData().equals(anEntry)) {
			removeFirst();
			return true;
		}
		else {
			int position = 1;
			while(position < getLength() && traverser.getNext().getData().compareTo(anEntry) <= 0) {
				if(traverser.getNext().getData().equals(anEntry)) {
					removeAfter(traverser);
					return true;
				}
				traverser = traverser.getNext();
				position++;
			}
		}
		return false;
	}

	/** Gets the position of an entry in this sorted list.
	@param anEntry  The object to be found.
	@return  The position of the first or only occurrence of anEntry
      if it occurs in the list; otherwise returns the position
      where anEntry would occur in the list, but as a negative
      integer. 
      */
	public int getPosition(T anEntry) {
		Node traverser = getFirst();
		for(int i = 1; i <= getLength(); i++) {
			if(traverser.getData().compareTo(anEntry) > 0) {
				break;
			}
			if(traverser.getData().equals(anEntry)) {
				return i;
			}
			traverser = traverser.getNext();
		}
		return -1;
	}
}
