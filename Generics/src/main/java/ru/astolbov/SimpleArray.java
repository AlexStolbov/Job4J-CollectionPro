package ru.astolbov;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] storage;
    int posForAdd = 0;

    public SimpleArray() {
        this.storage = new Object[0];
    }

    public void add(T model) {
        if (this.posForAdd + 1 >= this.storage.length) {
            this.extendStorage();
        }
        this.storage[this.posForAdd++] = model;
    }

    public void set(int index, T model) {
        this.checkIndex(index);
        this.storage[index] = model;
    }

    public void delete(int index) {
        this.checkIndex(index);
        Object[] oldStorage = this.storage;
        this.storage = new Object[oldStorage.length - 1];
        System.arraycopy(oldStorage, 0, this.storage, 0, index);
        System.arraycopy(oldStorage, index + 1, this.storage, index, oldStorage.length - index - 1);
    }

    public T get(int index) {
        this.checkIndex(index);
        return (T) this.storage[index];
    }

    public int size() {
        return this.storage.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorSimpleArray();
    }

    /**
     * Checks that the index is within the size of the array.
     * @param index
     */
    private void checkIndex(int index) {
        if (index >= this.storage.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Increases the size of the array by one.
     */
    private void extendStorage() {
        Object[] oldStorage = this.storage;
        this.storage = new Object[oldStorage.length + 1];
        System.arraycopy(oldStorage, 0, this.storage, 0, oldStorage.length);
    }

    private class IteratorSimpleArray implements Iterator<T> {
        int pos = 0;

        public IteratorSimpleArray() {
        }

        @Override
        public boolean hasNext() {
            return this.pos < storage.length;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) storage[pos++];
        }
    }

}