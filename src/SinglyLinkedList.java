import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(E element) {
        Node<E> node = new Node<>(element, null);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == size) { // append
            add(element);
            return;
        }
        if (index == 0) {
            head = new Node<>(element, head);
            if (tail == null){
                tail = head;

            }
        } else {
            Node<E> prev = getNode(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).element;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> n = getNode(index);
        E old = n.element;
        n.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> removed;
        if (index == 0) {
            removed = head;
            head = head.next;
            if (head == null){
                tail = null; // list is empty
            }
        } else {
            Node<E> prev = getNode(index - 1);
            removed = prev.next;
            prev.next = removed.next;
            if (removed == tail) {
                tail = prev; // removed last node
            }
        }
        size--;
        return removed.element;
    }

    @Override
    public boolean remove(E element) {
        Node<E> prev = null, current = head;
        while (current != null) {
            if ((current.element == null && element == null) ||
                    (current.element != null && current.element.equals(element))) {
                if (prev == null){
                    head = current.next;
                }
                else{
                    prev.next = current.next;
                }
                if (current == tail){
                    tail = prev; // removed last node
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = head;
        int i = 0;
        while (current != null) {
            if (o == null) {
                if (current.element == null) {
                    return i;
                }
            } else {
                if (o.equals(current.element)) {
                    return i;
                }
            }
            current = current.next;
            i++;
        }
        return -1;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
            public boolean hasNext() { return current != null; }
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E e = current.element;
                current = current.next;
                return e;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.element);
            current = current.next;
            if (current != null){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}