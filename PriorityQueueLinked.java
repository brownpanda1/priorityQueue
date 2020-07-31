import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

@SuppressWarnings("hiding")
public class PriorityQueueLinked<Object> implements PriorityQueue<Object> {
	
	private Link<Object> front;
	/**
	 * This is a subclass to help implement with the linking function
	 *
	 * @param <Object>
	 */
	public class Link<Object> {
		public Object element;
		public int priority;
		public Link<Object> next;
		
		public Link(Object e, int p, Link<Object> n) {
			this.element = e;
			this.priority = p;
			this.next = n;
		}
	}
	/**
	 * 
	 * This is the subclass to help with the iterator of the main class
	 *
	 */
	public class PQueueIterator implements Iterator<Object>{
		public Link<Object> tempLink;
		public Object item;

		/**
		 * This is the constructor which does not require a parameter
		 */
		public PQueueIterator() {
			if(front != null) {
				tempLink = new Link<Object>(null,0,front);
			}
		}
		
		/**
		 * This is function to check if there is an object after the current object
		 * 
		 * @return true if there is an object after and false otherwise
		 */
		@Override
		public boolean hasNext() {
			return tempLink.next != null;
		}

		/**
		 * This is a function that will return the next object in the list and moves the index to the next position
		 * @return Object of the current index 
		 * @throws OutOfBounds exception if it is on the last index
		 */
		@Override
		public Object next() throws OutOfBounds {
			if(!hasNext()) {
				throw new OutOfBounds("Queue is empty");
			}
					
			else {
				tempLink = tempLink.next;
				item = tempLink.element;
				return item;
			}
		}
	}

	
	/**
	 * Constructor for the priorityQueues class
	 */
	public PriorityQueueLinked() {
		this.front = null;
	}
	
	
	@Override
	/**
	 * remove the item at the front of the queue (the element with 
	 * the highest priority that has been there the longest)
	 * 
	 * @returns the removed item
	 * @throws Underflow - if the queue is empty
	 */
	public Object dequeue() throws Underflow {
		if (!isEmpty()) {
			Object temp = (Object) front.element;
			front = front.next;
			return temp;
		}
		
		else {
			throw new Underflow("Empty Queue");
		}
	}

	@Override
	/**
	 * examine the item at the front of the queue 
	 * (the element with the highest priority that has been in the 
	 * queue the longest)
	 * 
	 * @return the first item
	 * @throws Underflow - if the queue is empty
	 */
	public Object examine() throws Underflow {
		if (!isEmpty()) {
			return (Object) front.element;
			} else throw new Underflow("Empty Queue");
	}

	@Override
	/**
	 * test whether the queue is empty
	 * 
	 * @return true if the priority queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	/**
	 * return a DAT.Iterator to examine all the elements in the PriorityQueue
	 * 
	 * @return an Iterator pointing to before the first item
	 */
	public Iterator<Object> iterator() {
		PQueueIterator iterator = new PQueueIterator();
		return iterator;
	}

	@Override
	/**
	 * insert an item at the back into the queue with a given priority
	 * 
	 * @param a - the item to insert
	 * @param b - the priority of the element
	 * @throws IllegalValue - if the priority is not in a valid range
	 */
	public void enqueue(Object a, int b) throws IllegalValue {
		if (isEmpty() || b > front.priority) {
			front = new Link<Object>(a, b, front);
		}
		
		else {
			Link<Object> l = front;
			while (l.next != null && l.next.priority >= b) {
				l = l.next;
			}
			l.next = new Link<Object>(a, b, l.next);
		}
	}

}
