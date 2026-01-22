import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E>{

	private int size;
	private Node<E> front;
	private Node<E> rear;
	
	public LinkedQueue(){
		front = null;
		rear = null;
		size = 0;
	}
		
	@Override
	public void enqueue(E element) {
		Node<E> temp = rear;
		rear = new Node<E>(element, null);
		
		if(isEmpty()) {
			front = rear;
		}
		else {
			temp.next = rear;
		}
		
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		
		E element = front.element;
		front = front.next;
		size--;
		
		if(isEmpty()) {
			rear = null;
		}
		
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		return front.element;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString(){
		Iterator<E> it = iterator();
		
		if (!it.hasNext()){
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[" + it.next());
		while (it.hasNext()){
			sb.append(", " + it.next());
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	@Override
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private static class Node<E>{
		E element;
		Node<E> next;
		
		Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
	}
	
	private class QueueIterator implements Iterator<E>{
		
		private Node<E> current;
		
		public QueueIterator() {
			current = front;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			
			E element = current.element;
			current = current.next;
			
			return element;
		}
		
        public void remove() {
            throw new UnsupportedOperationException();
        }
	}
}