package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}
	
	public void append(FifoQueue<E> q){
		if (this != q){
			if(q.last == null){
				// inget händer
			}
			if(this.last == null && q.last != null){
				this.last = q.last;
				this.size = q.size;
				q.size = 0;
				q.last = null;
			}
			else if(this.last != null && q.last != null){
				QueueNode<E> temp = this.last.next;
				this.last.next = q.last.next;
				this.last = q.last;
				this.last.next = temp;
				q.last = null;
				this.size = this.size + q.size;
				q.size = 0;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if(last == null){
			last = n;
			n.next = n;
			size = 1;
			return true;
		}
		n.next = last.next;
		last.next = n;
		last = n;
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size != 0){
			return last.next.element;
		}
		return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		QueueNode<E> n = last;
		if(size == 0){
			return null;
		}
		if(size == 1){
			last = null;
			size = size - 1;
			return n.element;
		}
		if(size > 1){
			QueueNode<E> p = last.next;
			last.next = p.next;
			size = size - 1;
			return p.element;
		}
		return null;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		
		private QueueIterator(){
		
			if(last == null){
			pos = null;
			} else {
				pos = last.next;
				}
			}
		
	        @Override
	        public boolean hasNext(){
	        	return pos != null;
	        }
	        
	        @Override
	        public E next() {
	        	if(hasNext()) {
	        		QueueNode<E> temp = pos;
	        		pos = pos.next;
	        		if(temp == last){
	        			pos = null;
	        		}
	        		return temp.element;	
	        	} else {
	        		throw new NoSuchElementException();
	        	}
	        }
	        
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
