package ru.astolbov;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class ArrayContainer<E> implements SimpleContainer<E>, Iterable<E> {

    @GuardedBy("this")
    private Object[] container;
    @GuardedBy("this")
    private int size;
    @GuardedBy("this")
    private int modCount;
    private static final int DELTA_CHANGE_SIZE = 100;

    public ArrayContainer() {
        this.container = new Object[DELTA_CHANGE_SIZE];
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    @GuardedBy("this")
    public synchronized void add(E value) {
        if (this.size == this.container.length) {
            growContainer();
        }
        this.modCount++;
        this.container[this.size++] = value;
    }

    @GuardedBy("this")
    public synchronized void add(E value, int index) {
        while (this.container.length - 1 < index) {
            growContainer();
        }
        this.modCount++;
        this.container[index] = value;
        this.size++;
    }

    @GuardedBy("this")
    public synchronized void remove(int index) {
        checkIndex(index);
        this.modCount++;
        this.container[index] = null;
        this.size--;
    }

    @Override
    @GuardedBy("this")
    public synchronized E get(int index) {
        checkIndex(index);
        return (E) container[index];
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @GuardedBy("this")
    public synchronized boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    @GuardedBy("this")
    public synchronized int indexOf(Object o) {
        int res = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (container[i] == null) {
                    res = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(container[i])) {
                  res = i;
                  break;
                }
            }
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @GuardedBy("this")
    public synchronized int getSize() {
        return size;
    }

    private void growContainer() {
        this.container = Arrays.copyOf(this.container, this.container.length + DELTA_CHANGE_SIZE);
    }

    /**
     * Checks that the index is within the size of the array.
     * @param index
     */
    private synchronized void checkIndex(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class Iter implements Iterator<E> {

        int cursor = 0;
        int fixModCount = ArrayContainer.this.modCount;

        @Override
        @GuardedBy("this")
        public synchronized boolean hasNext() {
            checkModification();
            return cursor < ArrayContainer.this.size;
        }

        @Override
        @GuardedBy("this")
        public synchronized E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) ArrayContainer.this.container[cursor++];
        }

        private void checkModification() {
            if (fixModCount != ArrayContainer.this.modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
