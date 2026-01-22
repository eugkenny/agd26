public interface Stack<E> extends Iterable<E> {

	public void push(E element);

	public E pop();

	public E peek();

	public boolean isEmpty();

	public int size();
}
