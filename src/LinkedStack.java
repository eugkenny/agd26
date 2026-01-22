import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E>{
	
	private int size;
	private Node<E> top;
	
	public LinkedStack(){
		top = null;
		size = 0;
	}

	@Override
	public void push(E element) {
		Node<E> temp = top;
		top = new Node<E>(element, temp);
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		E element = top.element;
		top = top.next;
		size--;
		
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		return top.element;
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
		return new StackIterator();
	}

	private static class Node<E>{
		E element;
		Node<E> next;
		
		Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
	}
	
	private class StackIterator implements Iterator<E>{
		
		private Node<E> current;
		
		public StackIterator() {
			current = top;
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