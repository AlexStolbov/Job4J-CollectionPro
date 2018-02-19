import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> implements SimpleContainer<E>, Iterable<E> {

    Node<E> first = null;
    Node<E> last = null;
    int modCount = 0;
    int size = 0;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, this.last, null);
        if (this.last != null) {
            this.last.next = newNode;
        } else {
            this.first = newNode;
        }
        this.last = newNode;
        this.modCount++;
        this.size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        E res = null;
        int pos = 0;
        for (Node<E> current = this.first; pos <= index & current != null; current = current.next, pos++) {
            res = current.element;
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Checks that the index is within the size of the array.
     * @param index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public int getSize() {
        return size;
    }

    private class Iter implements Iterator<E> {
        Node<E> cursor = LinkedListContainer.this.first;
        int fixModCount = LinkedListContainer.this.modCount;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            checkModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E res = cursor.element;
            cursor = cursor.next;
            return res;
        }

        private void checkModification() {
            if (this.fixModCount != LinkedListContainer.this.modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
