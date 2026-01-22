public interface Queue<E> extends Iterable<E>{

	public void enqueue(E element);

	public E dequeue();

	public E peek();

	public boolean isEmpty();

	public int size();
}