import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayQueue<E> implements Queue<E> {
	
	private E[] queue;
	private int size;
	private int front;
	private int rear;
	
	private final int DEFAULT_CAPACITY = 10; // Good default?
	
	public ArrayQueue(){
		queue = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public void enqueue(E element) {
		if(size == queue.length) {
			resize(size * 2);
		}
		
		queue[rear % queue.length] = element;
		rear++;
		
		size++;
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue empty");
		}
		
		E element = queue[front];
		
		queue[front] = null;
		front = (front + 1) % queue.length;
		
		size--;
		
		// Could consider shrinking array if too empty
		
		return element;
	}

	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Stack empty");
		}
		
		return queue[front];
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
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(front + i) % queue.length];
        }
       
       front = 0;
       rear = size;
       queue = temp;
	}
	
	private class QueueIterator implements Iterator<E>{
		private int i;
		
		public QueueIterator() {
			i = front;
		}

		@Override
		public boolean hasNext() {
			return i <= rear ;
		}

		@Override
		public E next() {
			E e = queue[i];
			i = (i + 1) % queue.length;
			
			return e;
		}

        public void remove() {
            throw new UnsupportedOperationException();
        }
	}
}