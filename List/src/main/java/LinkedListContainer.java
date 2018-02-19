import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListContainer<E> implements SimpleContainer<E>, Iterable<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private int modCount = 0;
    private int size = 0;

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
        return getNode(index).element;
    }

    public Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> res = null;
        int pos = 0;
        for (Node<E> current = this.first; pos <= index & current != null; current = current.next, pos++) {
            res = current;
        }
        return res;
    }

    public E delete(int index) {
        Node<E> deletedNode = getNode(index);
        if (deletedNode.prev != null) {
            deletedNode.prev.next = deletedNode.next;
        } else {
            this.first = deletedNode.next;
        }
        if (deletedNode.next != null) {
            deletedNode.next.prev = deletedNode.prev;
        } else {
            last = deletedNode.prev;
        }
        size--;
        return deletedNode.element;
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
