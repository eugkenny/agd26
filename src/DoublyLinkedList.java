import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E element;
        Node<E> prev, next;
        Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == size) { // append
            Node<E> newNode = new Node<>(element, tail, null);
            if (tail != null) tail.next = newNode;
            tail = newNode;
            if (head == null) head = newNode;
        } else {
            Node<E> next = getNode(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(element, prev, next);
            if (prev != null) prev.next = newNode;
            next.prev = newNode;
            if (index == 0) head = newNode;
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
        Node<E> n = getNode(index);
        E removed = n.element;
        Node<E> prev = n.prev;
        Node<E> next = n.next;
        if (prev != null) prev.next = next;
        else head = next;
        if (next != null) next.prev = prev;
        else tail = prev;
        size--;
        return removed;
    }

    @Override
    public boolean remove(E element) {
        Node<E> cur = head;
        while (cur != null) {
            if ((cur.element == null && element == null) ||
                    (cur.element != null && cur.element.equals(element))) {
                Node<E> prev = cur.prev;
                Node<E> next = cur.next;
                if (prev != null) prev.next = next;
                else head = next;
                if (next != null) next.prev = prev;
                else tail = prev;
                size--;
                return true;
            }
            cur = cur.next;
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
        head = tail = null;
        size = 0;
    }

    private Node<E> getNode(int index) {
        if (index < (size / 2)) {
            Node<E> cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
            return cur;
        } else {
            Node<E> cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
            return cur;
        }
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
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> cur = head;
        while (cur != null) {
            sb.append(cur.element);
            cur = cur.next;
            if (cur != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
