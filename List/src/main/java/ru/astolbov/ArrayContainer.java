package ru.astolbov;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayContainer<E> implements SimpleContainer<E> {

    private Object[] container;
    private int size;
    private int modCount;
    private static final int DELTA_CHANGE_SIZE = 100;

    public ArrayContainer() {
        this.container = new Object[DELTA_CHANGE_SIZE];
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    public void add(E value) {
        if (this.size == this.container.length) {
            growContainer();
        }
        this.modCount++;
        this.container[this.size++] = value;
    }

    public void add(E value, int index) {
        while (this.container.length - 1 < index) {
            growContainer();
        }
        this.modCount++;
        this.container[index] = value;
        this.size++;
    }

    public void remove(int index) {
        checkIndex(index);
        this.modCount++;
        this.container[index] = null;
        this.size--;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) container[index];
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public synchronized boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(Object o) {
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

    public int getSize() {
        return size;
    }

    private void growContainer() {
        this.container = Arrays.copyOf(this.container, this.container.length + DELTA_CHANGE_SIZE);
    }

    /**
     * Checks that the index is within the size of the array.
     * @param index
     */
    private void checkIndex(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class Iter implements Iterator<E> {

        int cursor = 0;
        int fixModCount = ArrayContainer.this.modCount;

        @Override
        public boolean hasNext() {
            checkModification();
            return cursor < ArrayContainer.this.size;
        }

        @Override
        public E next() {
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
