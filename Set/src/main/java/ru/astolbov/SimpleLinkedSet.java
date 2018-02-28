package ru.astolbov;

import java.util.Iterator;

public class SimpleLinkedSet<E> implements Iterable<E> {

    LinkedListContainer<E> container = new LinkedListContainer<>();

    public void add(E value) {
        if (!container.contains(value)) {
            container.add(value);
        }
    }

    public int getSize() {
        return container.getSize();
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param value element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(Object value) {
        return container.contains(value);
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
