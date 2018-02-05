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
        if (posForAdd + 1 >= storage.length) {
            extendStorage();
        }
        set(posForAdd++, model);
    }

    public void set(int index, T model) {
        checkIndex(index);
        storage[index] = model;
    }

    public void delete(int index) {
        checkIndex(index);
        Object[] oldStorage = storage;
        storage = new Object[oldStorage.length - 1];
        System.arraycopy(oldStorage, 0, storage, 0, index);
        System.arraycopy(oldStorage, index + 1, storage, index, oldStorage.length - index - 1);
    }

    public T get(int index) {
        checkIndex(index);
        return (T) storage[index];
    }

    public int size() {
        return storage.length;
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
        if (index >= storage.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Increases the size of the array by one.
     */
    private void extendStorage() {
        Object[] oldStorage = storage;
        storage = new Object[oldStorage.length + 1];
        System.arraycopy(oldStorage, 0, storage, 0, oldStorage.length);
    }

    private class IteratorSimpleArray implements Iterator<T> {
        int pos = 0;

        public IteratorSimpleArray() {
        }

        @Override
        public boolean hasNext() {
            return pos < storage.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) storage[pos++];
        }
    }

}