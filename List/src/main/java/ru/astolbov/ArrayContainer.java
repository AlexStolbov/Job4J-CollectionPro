package ru.astolbov;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayContainer<E> implements SimpleContainer<E>, Iterable<E> {

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

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) container[index];
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
            return cursor < ArrayContainer.this.size;
        }

        @Override
        public E next() {
            checkModification();
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
