package ru.astolbov;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private ArrayContainer<E> container = new ArrayContainer<>();

    public void add(E value) {
        if (!container.contains(value)) {
            container.add(value);
        }
    }

    public int getSize() {
        return container.getSize();
    }

    public E get(int index) {
        return container.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }


}