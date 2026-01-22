import java.util.Iterator;

public interface List<E> extends Iterable<E> {

    void add(E element);           // Append to end
    void add(int index, E element);// Insert at index
    E get(int index);
    E set(int index, E element);   // Replace and return old element
    E remove(int index);
    boolean remove(E element);     // Remove first matching element
    boolean contains(Object o);
    int indexOf(Object o);
    int size();
    boolean isEmpty();
    void clear();
}
