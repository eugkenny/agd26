import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayStack<E> implements Stack<E> {
	
	private E [] stack;
	private int size;
	private final int DEFAULT_CAPACITY = 10; // Good default?
	
	public ArrayStack(){
		stack = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public void push(E element) {
		if (size == stack.length) {
			resize(size * 2);		// Growth strategy?
		}
		stack[size] = element;
		size++;
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		E element = stack[size - 1];
		stack[size - 1] = null;
		size--;
		
		// Could consider shrinking array if too empty  
		
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		
		return stack[size - 1];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	private void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = stack[i];
        }
       stack = temp;
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
	
    private class StackIterator implements Iterator<E> {
        private int i;

        public StackIterator() {
            i = size - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            
            E e = stack[i];
            i--;
            
            return e;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}